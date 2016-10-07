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

public class RegCursoActivity extends AppCompatActivity {

    public DBHelper db;
    private EditText descripcion, cantHoras;
    private Spinner universidad, facultad;
    private String codigoU, codigoF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_curso);

        db = new DBHelper(this);

        descripcion = (EditText)findViewById(R.id.etCursoDes);
        universidad = (Spinner)findViewById(R.id.spUniversidad);
        facultad = (Spinner)findViewById(R.id.spFacultad);
        cantHoras = (EditText)findViewById(R.id.etCantHoras);

        ArrayList<String> universidades;
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

        ArrayList<String> facultades;
        facultades = db.getAllFacultades();
        ArrayAdapter<String> facultad_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, facultades);
        facultad_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultad.setAdapter(facultad_adapter);

        facultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor rs = db.getFacultadCod(facultad.getSelectedItem().toString());
                rs.moveToFirst();
                codigoF = rs.getString(rs.getColumnIndex(DBHelper.FACULTAD_COLUMN_FACULTADCOD));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void registrarCur(View v){
        if (db.insertCurso(Integer.parseInt(codigoU), Integer.parseInt(codigoF), descripcion.getText().toString(), Integer.parseInt(cantHoras.getText().toString()))){
            Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
        }
    }
}
