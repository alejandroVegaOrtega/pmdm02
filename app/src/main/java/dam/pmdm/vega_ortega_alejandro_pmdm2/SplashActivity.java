package dam.pmdm.vega_ortega_alejandro_pmdm2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Redirige al MainActivity después de 3 segundos
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Cierra la actividad de Splash para que no vuelva al pulsar atrás
        }, 3000); // Duración de 3 segundos
    }

}
