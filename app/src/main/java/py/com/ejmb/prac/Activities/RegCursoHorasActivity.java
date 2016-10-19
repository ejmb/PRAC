package py.com.ejmb.prac.Activities;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import py.com.ejmb.prac.R;

public class RegCursoHorasActivity extends AppCompatActivity {

    private TextView horaEntrada, horaSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_curso_horas);

        horaEntrada = (TextView)findViewById(R.id.tvHoraEntrada);
        horaSalida = (TextView)findViewById(R.id.tvHoraSalida);
    }

    public void setHoraEntradaSalida(final View v){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String currTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                switch (v.getId()){
                    case R.id.bEntrada:{
                        horaEntrada.setText(currTime);
                        break;
                    }
                    case R.id.bSalida:{
                        horaSalida.setText(currTime);
                        break;
                    }
                }
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Seleccionar Hora");
        mTimePicker.show();
    }
}
