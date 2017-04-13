package dev.adenilson.deathcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    public void voltar(View view)
    {
        Intent i = new Intent(this, MainCalc.class);
        startActivity(i);
        finish();
    }
}
