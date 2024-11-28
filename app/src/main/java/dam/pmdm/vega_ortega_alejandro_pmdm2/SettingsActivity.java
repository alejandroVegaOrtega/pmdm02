package dam.pmdm.vega_ortega_alejandro_pmdm2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        switchLanguage = findViewById(R.id.switch_language);

        // Leer el idioma actual de SharedPreferences
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isEnglish = preferences.getBoolean("isEnglish", false);
        switchLanguage.setChecked(isEnglish);

        // Manejo del cambio de idioma
        switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isEnglish", isChecked);
            editor.apply();

            // Cambiar idioma
            setAppLocale(isChecked ? "en" : "es");
        });
    }

    /**
     * Cambia el idioma de la aplicación.
     * @param languageCode Código del idioma (ej. "en", "es").
     */
    private void setAppLocale(String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // Reiniciar la actividad actual para reflejar el cambio de idioma
        //recreate();
        // Reiniciar la aplicación (opcional: solo si tienes muchas pantallas)
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}