package dev.adenilson.deathcalc;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainCalc extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private int diaNascimento;
    private int mesNascimento;
    private int anoNascimento;
    private int qtdAnosJaFumou;
    private double idadeAtual;
    private double expecativaDeVida = 0;
    private double expecativaDeVidaNascimento = 0;

    private TextView diaNascimentoTextView;
    private TextView mesNascimentoTextView;
    private TextView anoNascimentoTextView;
    private TextView qtdAnosJaFumouTextView;
    private TextView textViewAnosFumou;

    private CheckBox jaFumou;

    private RadioButton radioAteu;
    private RadioButton radioCristao;
    private RadioButton radioJudeu;
    private RadioButton radioMuculmano;
    private RadioButton radioSatanista;

    private SeekBar diaSeekBar;
    private SeekBar mesSeekBar;
    private SeekBar anoSeekBar;
    private SeekBar qtdAnosFumouSeekbar;

    private Spinner spinnerHumor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);

        textViewAnosFumou = (TextView)findViewById(R.id.textViewAnosFumou);

        diaNascimentoTextView = (TextView)findViewById(R.id.textViewSeekBarDia);
        mesNascimentoTextView = (TextView)findViewById(R.id.textViewSeekBarMes);
        anoNascimentoTextView = (TextView)findViewById(R.id.textViewSeekBarAno);
        qtdAnosJaFumouTextView = (TextView)findViewById(R.id.textViewQtdAnosFumou);

        diaSeekBar = (SeekBar) findViewById(R.id.seekBarDia);
        mesSeekBar = (SeekBar) findViewById(R.id.seekBarMes);
        anoSeekBar = (SeekBar) findViewById(R.id.seekBarAno);
        qtdAnosFumouSeekbar = (SeekBar) findViewById(R.id.seekBarQtdFumou);

        jaFumou = (CheckBox)findViewById(R.id.checkBoxFumou);

        radioAteu = (RadioButton)findViewById(R.id.radioButtonAteu);
        radioCristao = (RadioButton)findViewById(R.id.radioButtonCristao);
        radioJudeu = (RadioButton)findViewById(R.id.radioButtonJudeu);
        radioMuculmano = (RadioButton)findViewById(R.id.radioButtonMuculmano);
        radioSatanista = (RadioButton)findViewById(R.id.radioButtonSatanista);

        spinnerHumor = (Spinner)findViewById(R.id.spinnerHumor);

        diaSeekBar.setOnSeekBarChangeListener(this);
        mesSeekBar.setOnSeekBarChangeListener(this);
        anoSeekBar.setOnSeekBarChangeListener(this);
        qtdAnosFumouSeekbar.setOnSeekBarChangeListener(this);
        jaFumou.setOnCheckedChangeListener(this);

        diaNascimento = diaSeekBar.getProgress();
        mesNascimento = mesSeekBar.getProgress();
        anoNascimento = anoSeekBar.getProgress();
        qtdAnosJaFumou = qtdAnosFumouSeekbar.getProgress();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.humor_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHumor.setAdapter(adapter);

        updateText();
    }

    private void updateText()
    {
        diaNascimentoTextView.setText(Integer.toString(diaNascimento)); // Por que o textview não aceita o inteiro?

        switch(mesNascimento){
            case 0:
                mesNascimentoTextView.setText("Jan");
                break;
            case 1:
                mesNascimentoTextView.setText("Jan");
                break;
            case 2:
                mesNascimentoTextView.setText("Fev");
                break;
            case 3:
                mesNascimentoTextView.setText("Mar");
                break;
            case 4:
                mesNascimentoTextView.setText("Abr");
                break;
            case 5:
                mesNascimentoTextView.setText("Mai");
                break;
            case 6:
                mesNascimentoTextView.setText("Jun");
                break;
            case 7:
                mesNascimentoTextView.setText("Jul");
                break;
            case 8:
                mesNascimentoTextView.setText("Ago");
                break;
            case 9:
                mesNascimentoTextView.setText("Set");
                break;
            case 10:
                mesNascimentoTextView.setText("Out");
                break;
            case 11:
                mesNascimentoTextView.setText("Nov");
                break;
            case 12:
                mesNascimentoTextView.setText("Dez");
                break;

            default:
                mesNascimentoTextView.setText("");
                break;
        }
        anoNascimentoTextView.setText(Integer.toString(anoNascimento + 1950));
        qtdAnosJaFumouTextView.setText(Integer.toString(qtdAnosJaFumou));
    }

    public void calcular(View view) {

        if(!validar()) {
            return;
        }

        Intent i = new Intent(this, result.class);

        expecativaDeVidaNascimento = -612.23228 + (0.34138*(anoNascimento + 1950));
        expecativaDeVida = -612.23228 + (0.34138*(anoNascimento + 1950));

        calcIdadeAtual();
        calcHumor();
        calcReligiao();

        i.putExtra("diaNascimento", diaNascimento);
        i.putExtra("mesNascimento", mesNascimento);
        i.putExtra("anoNascimento", anoNascimento);
        i.putExtra("religiao", GetReligiao());
        i.putExtra("humor", spinnerHumor.getSelectedItem().toString());
        i.putExtra("jaFumou", jaFumou.isChecked());
        if(jaFumou.isChecked()){
            i.putExtra("qtdAnosJaFumou", qtdAnosJaFumou);
        }
        i.putExtra("expectativaDeVida", expecativaDeVida);
        i.putExtra("idadeAtual", idadeAtual);

        startActivity(i);
    }

    public boolean validar(){
        if(GetReligiao() == "") {
            Toast.makeText(this, "Selecione uma religião", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void calcIdadeAtual(){
        idadeAtual = 2017 - (anoNascimento + 1950);
    }

    public void calcHumor(){
        String spinner = spinnerHumor.getSelectedItem().toString();
        switch (spinner){
            case "Otimista":
                expecativaDeVida = expecativaDeVida*1.1;
                break;
            case "Depressivo":
                expecativaDeVida = expecativaDeVida*0.9;
                break;
            case "Estressado":
                expecativaDeVida = expecativaDeVida*0.8;
                break;
        }
    }

    public void calcReligiao(){
        String religiao = GetReligiao();

        switch (religiao){
            case "JUDEU":
                break;
            case "CRISTÃO":
                break;
            case "MUÇULMANO":
                break;
            case "SATANISTA":
                expecativaDeVida = expecativaDeVida*0.7;
                break;
            case "ATEU":
                break;
        }
    }

    public String GetReligiao(){
        if(radioAteu.isChecked())
            return "ATEU";
        if(radioCristao.isChecked())
            return "CRISTAO";
        if(radioJudeu.isChecked())
            return "JUDEU";
        if(radioMuculmano.isChecked())
            return "MUCULMANO";
        if(radioSatanista.isChecked())
            return "SATANISTA";

        return "";
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getId() == R.id.seekBarDia)
            diaNascimento = progress + 1;
        if(seekBar.getId() == R.id.seekBarMes) {
            mesNascimento = progress + 1;
            ConfigureDate();
        }
        if(seekBar.getId() == R.id.seekBarAno){
            anoNascimento = progress;
            ConfigureDate();
        }
        if(seekBar.getId() == R.id.seekBarQtdFumou)
            qtdAnosJaFumou = progress;

        updateText();
    }

    public void ConfigureDate(){

        switch(mesNascimento){
            case 1:
                diaSeekBar.setMax(30);
                break;
            case 2:
                if(isAnoBissexto(anoNascimento + 1950)){
                    diaSeekBar.setMax(28);
                }
                else{
                    diaSeekBar.setMax(27);
                }
                break;
            case 3:
                diaSeekBar.setMax(30);
                break;
            case 4:
                diaSeekBar.setMax(29);
                break;
            case 5:
                diaSeekBar.setMax(30);
                break;
            case 6:
                diaSeekBar.setMax(29);
                break;
            case 7:
                diaSeekBar.setMax(30);
                break;
            case 8:
                diaSeekBar.setMax(30);
                break;
            case 9:
                diaSeekBar.setMax(29);
                break;
            case 10:
                diaSeekBar.setMax(30);
                break;
            case 11:
                diaSeekBar.setMax(29);
                break;
            case 12:
                diaSeekBar.setMax(30);
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch(buttonView.getId()){
            case R.id.checkBoxFumou:
                if(!isChecked){
                    qtdAnosFumouSeekbar.setVisibility(View.INVISIBLE);
                    textViewAnosFumou.setVisibility(View.INVISIBLE);
                    qtdAnosJaFumouTextView.setVisibility(View.INVISIBLE);
                }
                else{
                    qtdAnosFumouSeekbar.setVisibility(View.VISIBLE);
                    textViewAnosFumou.setVisibility(View.VISIBLE);
                    qtdAnosJaFumouTextView.setVisibility(View.VISIBLE);
                }
        }

    }

    public boolean isAnoBissexto(int ano) {
        if ( ( ano % 4 == 0 && ano % 100 != 0 ) || ( ano % 400 == 0 ) ){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
