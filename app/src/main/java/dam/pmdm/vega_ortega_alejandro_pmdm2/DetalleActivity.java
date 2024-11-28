package dam.pmdm.vega_ortega_alejandro_pmdm2;

// DetalleActivity.java
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad que muestra los detalles de un personaje en una interfaz de usuario, también maneja la configuración del ActionBar para permitir la navegación
 * a la actividad anterior.
 */
public class DetalleActivity extends AppCompatActivity {

    /** Método que se llama cuando la actividad se crea.
     * @param savedInstanceState El estado guardado de la actividad anterior, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        // Configura el ActionBar para mostrar el botón de "Atrás"
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView textViewNombre = findViewById(R.id.textViewNombre); //Busca el elemento con el ID textViewNombre en el diseño XML.
        String nombre = getIntent().getStringExtra("nombre"); //Obtiene el nombre
        textViewNombre.setText(nombre);//Setea el nombre al textView

        ImageView imageViewDetalle = findViewById(R.id.imageViewDetalle);
        int imagenId = getIntent().getIntExtra("imagenId", -1);
        imageViewDetalle.setImageResource(imagenId);

        TextView textViewDescripcion = findViewById(R.id.textViewDescripcion);
        String descripcion = getIntent().getStringExtra("descripcion");
        textViewDescripcion.setText(descripcion);

        TextView textViewHabilidad = findViewById(R.id.textViewHabilidad);
        String habilidad = getIntent().getStringExtra("habilidad");
        textViewHabilidad.setText(habilidad);

        // Mostrar el Toast con el nombre del personaje seleccionado
        Toast.makeText(this, getString(R.string.seleccion) + nombre, Toast.LENGTH_SHORT).show();

    }

    /** Maneja el evento de navegación hacia atrás en el ActionBar.
     * @return true si la navegación se manejó correctamente, false en caso contrario.
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Cierra la actividad actual y vuelve a la anterior
        return true;
    }
}
