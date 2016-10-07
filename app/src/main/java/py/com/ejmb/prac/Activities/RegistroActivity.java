package py.com.ejmb.prac.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import py.com.ejmb.prac.R;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void regUsuario(View v){
        Intent intent = new Intent(RegistroActivity.this, RegUsuarioActivity.class);
        startActivity(intent);
    }

    public void regUniversidad(View v){
        Intent intent = new Intent(RegistroActivity.this, RegUniversidadActivity.class);
        startActivity(intent);
    }

    public void regFacultad(View v){
        Intent intent = new Intent(RegistroActivity.this, RegFacultadActivity.class);
        startActivity(intent);
    }

    public void regCurso(View v){
        Intent intent = new Intent(RegistroActivity.this, RegCursoActivity.class);
        startActivity(intent);
    }
}
