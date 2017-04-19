package dev.adenilson.deathcalc;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class result extends AppCompatActivity {

    private int diaNascimento;
    private int mesNascimento;
    private int anoNascimento;
    private int qtdAnosJaFumou;
    private double idadeAtual;
    private double expectativaDeVida;
    private boolean jaFumou;
    private String religiao;
    private String humor;

    private TextView textViewResultNascimento;
    private TextView textViewExpectativaTitulo;
    private TextView textViewResultExpectativa;
    private TextView textViewResultIdade;
    private TextView textViewReligiao;
    private TextView textViewFumaTitulo;
    private TextView textViewResultFuma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResultNascimento = (TextView)findViewById(R.id.textViewResultNascimento);
        textViewExpectativaTitulo = (TextView)findViewById(R.id.textViewExpectativaTitulo);
        textViewResultExpectativa = (TextView)findViewById(R.id.textViewResultExpectativa);
        textViewResultIdade = (TextView)findViewById(R.id.textViewResultIdade);
        textViewReligiao = (TextView)findViewById(R.id.textViewReligiao);
        textViewFumaTitulo = (TextView)findViewById(R.id.textViewFumaTitulo);
        textViewResultFuma = (TextView)findViewById(R.id.textViewResultFuma);

        Intent i = getIntent();

        if(i != null){
            Bundle extras = i.getExtras();

            idadeAtual = extras.getDouble("idadeAtual");
            expectativaDeVida = extras.getDouble("expectativaDeVida");
            diaNascimento = extras.getInt("diaNascimento");
            mesNascimento = extras.getInt("mesNascimento");
            anoNascimento = extras.getInt("anoNascimento");
            religiao = extras.getString("religiao");
            humor = extras.getString("humor");
            jaFumou = extras.getBoolean("jaFumou");
            if(jaFumou){
                qtdAnosJaFumou = extras.getInt("qtdAnosJaFumou");
            }
        }

        setFields();
    }

    public void setFields(){

        textViewResultNascimento.setText(diaNascimento + "/" + mesNascimento + "/" + (anoNascimento + 1950));
        textViewExpectativaTitulo.setText("Expecativa de vida para " + (anoNascimento + 1950) + ":");
        textViewResultIdade.setText(idadeAtual + " anos");
        textViewFumaTitulo.setText("Fuma há " + qtdAnosJaFumou + " anos:");
        textViewResultFuma.setText("Menos " + qtdAnosJaFumou + " anos de expectativa de vida");
        textViewReligiao.setText(religiao);
        textViewResultExpectativa.setText(Double.toString(expectativaDeVida));

    }

    public String doCalc(){

        return "";

    }

    public void voltar(View view)
    {
        Intent i = new Intent(this, MainCalc.class);
        startActivity(i);
        finish();
    }
}
