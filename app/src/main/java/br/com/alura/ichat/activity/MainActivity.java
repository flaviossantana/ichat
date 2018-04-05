package br.com.alura.ichat.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.alura.ichat.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Olá IChat!");
    }
}
