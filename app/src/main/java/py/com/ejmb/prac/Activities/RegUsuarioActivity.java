package py.com.ejmb.prac.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import py.com.ejmb.prac.R;
import py.com.ejmb.prac.Utils.DBHelper;

public class RegUsuarioActivity extends AppCompatActivity {

    public DBHelper db;
    private EditText nombre, apellido, documento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        db = new DBHelper(this);

        nombre = (EditText)findViewById(R.id.etNombre);
        apellido = (EditText)findViewById(R.id.etApellido);
        documento = (EditText)findViewById(R.id.etDocumento);

    }

    public void registrarUsu(View v){
        if (db.insertUsuario(documento.getText().toString(), nombre.getText().toString(), apellido.getText().toString())){
            Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }
    }
}
