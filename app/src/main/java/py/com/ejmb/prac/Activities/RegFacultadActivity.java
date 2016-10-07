package py.com.ejmb.prac.Activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import py.com.ejmb.prac.R;
import py.com.ejmb.prac.Utils.DBHelper;

public class RegFacultadActivity extends AppCompatActivity {

    public DBHelper db;
    private EditText codigoF, descripcion;
    private Spinner universidad;
    private String codigoU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_facultad);

        db = new DBHelper(this);

        //codigoF = (EditText)findViewById(R.id.etCodFac);
        descripcion = (EditText)findViewById(R.id.etDesFac);
        universidad = (Spinner)findViewById(R.id.spDesUni);

        ArrayList<String> universidades = new ArrayList<>();
        universidades = db.getAllUniversidades();
        ArrayAdapter<String> universidad_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, universidades);
        universidad_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universidad.setAdapter(universidad_adapter);

        universidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor rs = db.getUniversidadCod(universidad.getSelectedItem().toString());
                rs.moveToFirst();
                codigoU = rs.getString(rs.getColumnIndex(DBHelper.UNIVERSIDAD_COLUMN_UNIVERSIDADCOD));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void registrarFac(View v){
        if (db.insertFacultad(Integer.parseInt(codigoU), descripcion.getText().toString())){
            Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }
    }
}
