package py.com.ejmb.prac.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import py.com.ejmb.prac.R;
import py.com.ejmb.prac.Utils.DBHelper;

public class RegUniversidadActivity extends AppCompatActivity {

    public DBHelper db;
    private EditText codigo, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_universidad);

        db = new DBHelper(this);

       // codigo = (EditText)findViewById(R.id.etCodigoUni);
        descripcion = (EditText)findViewById(R.id.etDescUni);

    }

    public void registrarUni(View v){
        if (db.insertUniversidad(descripcion.getText().toString())){
            Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }
    }
}
