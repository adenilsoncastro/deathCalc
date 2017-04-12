package dev.adenilson.deathcalc;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainCalc extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

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

    private Spinner spinnerHumor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);

        diaNascimentoTextView = (TextView)findViewById(R.id.textViewSeekBarDia);
        mesNascimentoTextView = (TextView)findViewById(R.id.textViewSeekBarMes);
        anoNascimentoTextView = (TextView)findViewById(R.id.textViewSeekBarAno);
        qtdAnosJaFumouTextView = (TextView)findViewById(R.id.textViewQtdAnosFumou);

        SeekBar diaSeekBar = (SeekBar) findViewById(R.id.seekBarDia);
        SeekBar mesSeekBar = (SeekBar) findViewById(R.id.seekBarMes);
        SeekBar anoSeekBar = (SeekBar) findViewById(R.id.seekBarAno);
        SeekBar qtdAnosFumouSeekbar = (SeekBar) findViewById(R.id.seekBarQtdFumou);

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
        diaNascimentoTextView.setText(diaNascimento);
        mesNascimentoTextView.setText(mesNascimento);
        anoNascimentoTextView.setText(anoNascimento);
        qtdAnosJaFumouTextView.setText(qtdAnosJaFumou);
    }
}
