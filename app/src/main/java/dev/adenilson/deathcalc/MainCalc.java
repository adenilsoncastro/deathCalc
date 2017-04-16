package dev.adenilson.deathcalc;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainCalc extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, CompoundButton.OnCheckedChangeListener {

    private int diaNascimento;
    private int mesNascimento;
    private int anoNascimento;
    private int qtdAnosJaFumou;

    private TextView diaNascimentoTextView;
    private TextView mesNascimentoTextView;
    private TextView anoNascimentoTextView;
    private TextView qtdAnosJaFumouTextView;

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

    private TextView textViewAnosFumou;

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
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getId() == R.id.seekBarDia)
            diaNascimento = progress;
        if(seekBar.getId() == R.id.seekBarMes)
            mesNascimento = progress;
        if(seekBar.getId() == R.id.seekBarAno)
            anoNascimento = progress;
        if(seekBar.getId() == R.id.seekBarQtdFumou)
            qtdAnosJaFumou = progress;

        updateText();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    private void updateText()
    {
        diaNascimentoTextView.setText(Integer.toString(diaNascimento)); // Por que o textview não aceita o inteiro?

        switch(mesNascimento){
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
        Intent i = new Intent(this, result.class);

        i.putExtra("diaNascimento", diaNascimento);
        i.putExtra("mesNascimento", mesNascimento);
        i.putExtra("anoNascimento", anoNascimento);
        i.putExtra("religiao", GetReligiao());
        i.putExtra("humor", spinnerHumor.getSelectedItem().toString());
        i.putExtra("jaFumou", jaFumou.isChecked());
        if(jaFumou.isChecked()){
            i.putExtra("qtdAnosJaFumou", qtdAnosJaFumou);
        }

        startActivity(i);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch(buttonView.getId()){
            case R.id.checkBoxFumou:
                if(!isChecked){
                    qtdAnosFumouSeekbar.setVisibility(View.INVISIBLE);
                    textViewAnosFumou.setVisibility(View.INVISIBLE);
                }
                else{
                    qtdAnosFumouSeekbar.setVisibility(View.VISIBLE);
                    textViewAnosFumou.setVisibility(View.VISIBLE);
                }
        }

    }
}
