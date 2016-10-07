package py.com.ejmb.prac.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import py.com.ejmb.prac.R;
import py.com.ejmb.prac.Utils.AdaptadorListView;
import py.com.ejmb.prac.Utils.DBHelper;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    public ListView cursos;
    public DBHelper db;
    private  AdaptadorListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        cursos = (ListView)findViewById(R.id.listViewCursos);
        HashMap<String, Integer> hashCursos = db.getAllCursos();
        listarCursos(hashCursos);
        cursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Position: "+i+" Id: "+l,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void listarCursos(HashMap<String, Integer> listaCursos) {
        adapter = new AdaptadorListView(listaCursos);
        cursos.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_usuario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.accion_registrar_usu:{
                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.accion_cambiar_usu:{
                //Accion
                break;
            }
            case R.id.accion_eliminar_usu:{
                //Accion
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
