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
    private double expectativaDeVidaNascimento;
    private double resultExpectativa;
    private boolean jaFumou;
    private String religiao;
    private boolean isReligionPositive = false;
    private String humor;

    private TextView textViewResultNascimento;
    private TextView textViewExpectativaTitulo;
    private TextView textViewResultExpectativa;
    private TextView textViewResultIdade;
    private TextView textViewReligiao;
    private TextView textViewResultReligiao;
    private TextView textViewFumaTitulo;
    private TextView textViewResultFuma;
    private TextView textViewHumorTitulo;
    private TextView textViewHumorResultado;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResultNascimento = (TextView)findViewById(R.id.textViewResultNascimento);
        textViewExpectativaTitulo = (TextView)findViewById(R.id.textViewExpectativaTitulo);
        textViewResultExpectativa = (TextView)findViewById(R.id.textViewResultExpectativa);
        textViewResultIdade = (TextView)findViewById(R.id.textViewResultIdade);
        textViewReligiao = (TextView)findViewById(R.id.textViewReligiao);
        textViewResultReligiao = (TextView)findViewById(R.id.textViewResultReligiao);
        textViewFumaTitulo = (TextView)findViewById(R.id.textViewFumaTitulo);
        textViewResultFuma = (TextView)findViewById(R.id.textViewResultFuma);
        textViewHumorTitulo = (TextView)findViewById(R.id.textViewHumor);
        textViewHumorResultado = (TextView)findViewById(R.id.textViewResultHumor);
        textViewResult = (TextView)findViewById(R.id.textViewResult);

        Intent i = getIntent();

        if(i != null){
            Bundle extras = i.getExtras();

            idadeAtual = extras.getDouble("idadeAtual");
            expectativaDeVidaNascimento = extras.getDouble("expectativaDeVidaNascimento");
            diaNascimento = extras.getInt("diaNascimento");
            mesNascimento = extras.getInt("mesNascimento");
            anoNascimento = extras.getInt("anoNascimento");
            religiao = extras.getString("religiao");
            isReligionPositive = extras.getBoolean("isReligionPositive");
            humor = extras.getString("humor");
            jaFumou = extras.getBoolean("jaFumou");
            if(jaFumou){
                qtdAnosJaFumou = extras.getInt("qtdAnosJaFumou");
            }
            resultExpectativa = extras.getDouble("resultExpectativa");
        }

        setFields();
    }

    public void setFields(){
        textViewResultNascimento.setText(diaNascimento + " de " + getMes(mesNascimento) + " de " + (anoNascimento + 1950));
        textViewExpectativaTitulo.setText("Expecativa de vida para " + (anoNascimento + 1950) + ":");
        textViewResultIdade.setText(idadeAtual + " anos");
        textViewFumaTitulo.setText("Fuma há " + qtdAnosJaFumou + " anos:");
        textViewResultFuma.setText("Menos " + qtdAnosJaFumou + " anos de expectativa de vida");
        textViewReligiao.setText(religiao);
        textViewResultReligiao.setText(getReligiao(religiao));
        textViewResultExpectativa.setText(String.format("%1$.2f", expectativaDeVidaNascimento) + " anos");
        textViewHumorTitulo.setText(humor);
        textViewHumorResultado.setText(getDescricaoHumor(humor));
        if(resultExpectativa > 0)
            textViewResult.setText(String.format("%1$.2f", resultExpectativa) + " anos de vida");
        else
            textViewResult.setText("Já passaram " + String.format("%1$.2f", resultExpectativa*(-1)) + " anos da sua morte!");

    }

    public String getMes(int mes) {

        String nomeMes;

        switch (mesNascimento) {
            case 1:
                nomeMes = "janeiro";
                break;
            case 2:
                nomeMes = "fevereiro";
                break;
            case 3:
                nomeMes = "março";
                break;
            case 4:
                nomeMes = "abril";
                break;
            case 5:
                nomeMes = "maio";
                break;
            case 6:
                nomeMes = "junho";
                break;
            case 7:
                nomeMes = "julho";
                break;
            case 8:
                nomeMes = "agosto";
                break;
            case 9:
                nomeMes = "setembro";
                break;
            case 10:
                nomeMes = "outubro";
                break;
            case 11:
                nomeMes = "novembro";
                break;
            case 12:
                nomeMes = "dezembro";
                break;
            default:
                nomeMes = "";
                break;
        }
        return nomeMes;
    }

    public String getDescricaoHumor(String humor){

        String descricaoHumor = "";

        switch(humor) {
            case "Otimista":
                descricaoHumor = "Mais 10% da expectativa de vida";
                break;
            case "Depressivo":
                descricaoHumor = "Menos 10% da expectativa de vida";
                break;
            case "Estressado":
                descricaoHumor = "Menos 20% da expectativa de vida";
                break;
            default:
                break;
        }
        return descricaoHumor;
    }

    public String getReligiao(String religiao){
        if(isReligionPositive == true){
            switch (religiao) {
                case "Judeu":
                    return "Infere em 10% a mais de expectativa de vida";

                case "Cristão":
                    return "Infere em 10% a mais de expectativa de vida";

                case "Muçulmano":
                    return "Infere em 20% a mais de expectativa de vida";

                case "Satanista":
                    return "Infere em 30% a menos de expectativa de vida";

                case "Ateu":
                    return "Infere em 0% da expectativa de vida";
            }
        }
        else{
            switch (religiao) {
                case "Judeu":
                    return "Infere em 10% a menos de expectativa de vida";

                case "Cristão":
                    return "Infere em 10% a menos de expectativa de vida";

                case "Muçulmano":
                    return "Infere em 20% a menos de expectativa de vida";

                case "Satanista":
                    return "Infere em 30% a menos de expectativa de vida";

                case "Ateu":
                    return "Infere em 0% da expectativa de vida";
            }
        }
        return "";
    }

    public void voltar(View view)
    {
        Intent i = new Intent(this, MainCalc.class);
        startActivity(i);
        finish();
    }
}
