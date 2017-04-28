package dev.adenilson.deathcalc;

import android.content.Intent;
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
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Random;

public class MainCalc extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    private int diaNascimento;
    private int mesNascimento;
    private int anoNascimento;
    private int qtdAnosJaFumou;
    private double idadeAtual;
    private double expectativaDeVida = 0;
    private double expectativaDeVidaNascimento = 0;
    private double resultExpectativa = 0;

    private boolean isReligionPositive = false;

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


    Random randomReligion = new Random();

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

        qtdAnosFumouSeekbar.setVisibility(View.INVISIBLE);
        textViewAnosFumou.setVisibility(View.INVISIBLE);
        qtdAnosJaFumouTextView.setVisibility(View.INVISIBLE);

        updateText();
    }

    private void updateText()
    {
        diaNascimentoTextView.setText(Integer.toString(diaNascimento)); //

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

        expectativaDeVidaNascimento = -612.23228 + (0.34138*(anoNascimento + 1950));
        expectativaDeVida = -612.23228 + (0.34138*(anoNascimento + 1950));

        calcIdadeAtual();
        calcFumou();
        calcHumor();
        calcReligiao();

        resultExpectativa = expectativaDeVida - idadeAtual;

        i.putExtra("diaNascimento", diaNascimento);
        i.putExtra("mesNascimento", mesNascimento);
        i.putExtra("anoNascimento", anoNascimento);
        i.putExtra("religiao", GetReligiao());
        i.putExtra("isReligionPositive", isReligionPositive);
        i.putExtra("humor", spinnerHumor.getSelectedItem().toString());
        i.putExtra("jaFumou", jaFumou.isChecked());
        if(jaFumou.isChecked()){
            i.putExtra("qtdAnosJaFumou", qtdAnosJaFumou);
        }
        i.putExtra("expectativaDeVidaNascimento", expectativaDeVidaNascimento);
        i.putExtra("idadeAtual", idadeAtual);
        i.putExtra("resultExpectativa", resultExpectativa);

        startActivity(i);
        finish();
    }

    public boolean validar(){

        if((diaNascimento == 0)){
            Toast.makeText(this, "Informe uma data de nascimento válida", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(GetReligiao() == "") {
            Toast.makeText(this, "Selecione uma religião", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void calcIdadeAtual(){

        DateTime dNow = new DateTime();
        String dateBday = Integer.toString(anoNascimento+1950) + "/" + Integer.toString(mesNascimento) + "/" + Integer.toString(diaNascimento);

        DateTimeFormatter dft = DateTimeFormat.forPattern("yyyy/MM/dd");
        DateTime dateBirthday = dft.parseDateTime(dateBday);

        Years age = Years.yearsBetween(dateBirthday, dNow);

        idadeAtual = age.getYears();
    }

    public void calcFumou(){
        expectativaDeVida = expectativaDeVida - qtdAnosJaFumou;
    }

    public void calcHumor(){
        String spinner = spinnerHumor.getSelectedItem().toString();
        switch (spinner){
            case "Otimista":
                expectativaDeVida = expectativaDeVida*1.1;
                break;
            case "Depressivo":
                expectativaDeVida = expectativaDeVida*0.9;
                break;
            case "Estressado":
                expectativaDeVida = expectativaDeVida*0.8;
                break;
        }
    }

    public String GetReligiao(){
        if(radioAteu.isChecked())
            return "Ateu";
        if(radioCristao.isChecked())
            return "Cristão";
        if(radioJudeu.isChecked())
            return "Judeu";
        if(radioMuculmano.isChecked())
            return "Muçulmano";
        if(radioSatanista.isChecked())
            return "Satanista";

        return "";
    }

    public void calcReligiao(){
        String religiao = GetReligiao();
        int random = randomReligion.nextInt(2);//Random: 1 = + / 0 = -
        switch (religiao){
            case "Judeu":
                if(random == 1){
                    expectativaDeVida = expectativaDeVida*1.1;
                    isReligionPositive = true;
                }
                else if(random == 0 ){
                    expectativaDeVida = expectativaDeVida*0.9;
                    isReligionPositive = false;
                }
                break;
            case "Cristão":
                if(random == 1){
                    expectativaDeVida = expectativaDeVida*1.1;
                    isReligionPositive = true;
                }
                else if(random == 0 ){
                    expectativaDeVida = expectativaDeVida*0.9;
                    isReligionPositive = false;
                }
                break;
            case "Muçulmano":
                if(random == 1){
                    expectativaDeVida = expectativaDeVida*1.2;
                    isReligionPositive = true;
                }
                else if(random == 0 ){
                    expectativaDeVida = expectativaDeVida*0.8;
                    isReligionPositive = false;
                }
                break;
            case "Satanista":
                expectativaDeVida = expectativaDeVida*0.7;
                isReligionPositive = false;
                break;
            case "Ateu":
                isReligionPositive = false;
                break;
        }
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

    public boolean isAnoBissexto(int ano) {
        if ( ( ano % 4 == 0 && ano % 100 != 0 ) || ( ano % 400 == 0 ) ){
            return true;
        }
        else{
            return false;
        }
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
            qtdAnosFumouSeekbar.setMax(67 - anoNascimento);
        }
        if(seekBar.getId() == R.id.seekBarQtdFumou)
            qtdAnosJaFumou = progress;

        updateText();
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
