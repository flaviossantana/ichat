package br.com.alura.ichat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.ichat.R;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.callback.EnviarMensagensCallback;
import br.com.alura.ichat.callback.OuvirMensagensCallback;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.IChatService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int idCliente = 1;
    private EditText texto;
    private Button btnEnviar;
    private List<Mensagem> mensagens;
    private ListView listaDeMensagens;
    private IChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listaDeMensagens = this.findViewById(R.id.main_lista_menagens);

        mensagens = new ArrayList<>();

        MensagemAdapter mensagemAdapter = new MensagemAdapter(this, mensagens, idCliente);

        listaDeMensagens.setAdapter(mensagemAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        chatService = retrofit.create(IChatService.class);

        ouvirMensagem();

        btnEnviar = findViewById(R.id.main_btn_envair);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.enviar(new Mensagem(idCliente, texto.getText().toString())).enqueue(new EnviarMensagensCallback());
            }
        });

        texto = this.findViewById(R.id.main_texto);

    }

    public void colocaNaLista(Mensagem mensagem) {
        this.mensagens.add(mensagem);
        MensagemAdapter adapter = new MensagemAdapter(this, this.mensagens, idCliente);
        listaDeMensagens.setAdapter(adapter);
    }

    public void ouvirMensagem() {
        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallback(this));
    }

}
