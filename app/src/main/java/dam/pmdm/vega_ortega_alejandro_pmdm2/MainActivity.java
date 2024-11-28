package dam.pmdm.vega_ortega_alejandro_pmdm2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Actividad principal de la aplicación que muestra una lista de personajes en un RecyclerView. */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PersonajeAdapter personajeAdapter;
    private List<Personaje> listaPersonajes;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    /** Método que se llama cuando la actividad se crea.
      * @param savedInstanceState El estado guardado de la actividad anterior, si existe. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadAppLocale();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        // Actualizar título de la Toolbar con el nombre de la app en el idioma cargado
        if (toolbar != null) {
            toolbar.setTitle(getString(R.string.app_name));  // Establecer el título dinámicamente
        }
        setSupportActionBar(toolbar);

        // Inicializar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        // Configurar el toggle del Navigation Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Manejar clics en el NavigationView
        navigationView.setNavigationItemSelectedListener(item -> {
                    if (item.getItemId() == R.id.nav_home) {
                        drawerLayout.closeDrawer(GravityCompat.START);
                    } else if (item.getItemId() == R.id.nav_settings) {
                        Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(settingsIntent);
                    }
                    return true;
                });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listaPersonajes = new ArrayList<>();
        // Añadir personajes a la lista de personajes, se usa getString(R.string. ) para el multiidioma
        listaPersonajes.add(new Personaje(getString(R.string.nombre_mario), getString(R.string.descripcion_mario), R.drawable.mario, getString(R.string.habilidad_mario))); // Asegúrate de tener esta imagen en res/drawable
        listaPersonajes.add(new Personaje(getString(R.string.nombre_luigi), getString(R.string.descripcion_luigi), R.drawable.luigi, getString(R.string.habilidad_luigi)));
        listaPersonajes.add(new Personaje(getString(R.string.nombre_peach), getString(R.string.descripcion_peach), R.drawable.peach, getString(R.string.habilidad_peach)));
        listaPersonajes.add(new Personaje(getString(R.string.nombre_toad), getString(R.string.descripcion_toad), R.drawable.toad, getString(R.string.habilidad_toad)));

        personajeAdapter = new PersonajeAdapter(this, listaPersonajes, personaje -> {
            // Manejo del clic en el personaje
            Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
            intent.putExtra("nombre", personaje.getNombre());
            intent.putExtra("imagenId", personaje.getImagenId());
            intent.putExtra("descripcion", personaje.getDescripcion());
            intent.putExtra("habilidad", personaje.getHabilidad());
            startActivity(intent);

        });

        recyclerView.setAdapter(personajeAdapter);
        // Mostrar el Snackbar con el mensaje de bienvenida
        Snackbar.make(recyclerView, getString(R.string.bienvenidos), Snackbar.LENGTH_LONG).show();

    }

    /**
     * Crea el menú de opciones de la aplicación.
     * Se crea un menú desde `menu.xml` y se agrega a la barra de la aplicación.
     *
     * @param menu El menú donde se crean las opciones.
     * @return true si el menú se creó correctamente, false en caso contrario.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Maneja las opciones seleccionadas en el menú.
     * @param item El ítem del menú que el usuario ha seleccionado.
     * @return true si el ítem ha sido manejado correctamente, false en caso contrario.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_acerca_de) { // Si el click se hace en el boton de informacion
            mostrarDialogAcercaDe(); //Llama al método para mostrar el dialogo
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Muestra un cuadro de diálogo con información acerca de la aplicación.*/
    private void mostrarDialogAcercaDe() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.acerca_de))
                .setMessage(getString(R.string.descripcion_acerca_de))
                .setIcon(R.mipmap.ic_launcher) // Icono de la app
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Carga el idioma configurado en SharedPreferences.
     */
    private void loadAppLocale() {
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isEnglish = preferences.getBoolean("isEnglish", false);
        Locale locale = new Locale(isEnglish ? "en" : "es");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}