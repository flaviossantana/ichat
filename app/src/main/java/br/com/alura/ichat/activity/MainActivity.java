package br.com.alura.ichat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Arrays;

import br.com.alura.ichat.R;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.ChatService;

public class MainActivity extends AppCompatActivity {

    private int idCliente;
    private EditText texto;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mensagensListView = this.findViewById(R.id.main_lista_menagens);
        idCliente = 1;
        mensagensListView.setAdapter(new MensagemAdapter(this, Arrays.asList(new Mensagem(1, "Olá!"), new Mensagem(2, "Blz?")), idCliente));

        texto = this.findViewById(R.id.main_texto);

        btnEnviar = findViewById(R.id.main_btn_envair);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChatService().enviar(new Mensagem(idCliente, texto.getText().toString()));
            }
        });

    }
}
