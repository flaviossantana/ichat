package br.com.alura.ichat.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.ichat.R;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.modelo.Mensagem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Olá IChat!");

        ListView mensagensListView = this.findViewById(R.id.main_lista_menagens);

        mensagensListView.setAdapter(new MensagemAdapter(this, Arrays.asList(new Mensagem(1, "Olá!"),new Mensagem(2, "Blz?")), 1));

    }
}
