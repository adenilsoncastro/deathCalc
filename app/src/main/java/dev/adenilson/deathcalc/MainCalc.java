package dev.adenilson.deathcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainCalc extends AppCompatActivity {

    private TextView diaNascimento;
    private TextView mesNascimento;
    private TextView anoNascimento;
    private TextView anosJaFumou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);

        diaNascimento = (TextView)findViewById(R.id.textViewSeekBarDia);
        mesNascimento = (TextView)findViewById(R.id.textViewSeekBarMes);
        anoNascimento = (TextView)findViewById(R.id.textViewSeekBarAno);



    }


}
