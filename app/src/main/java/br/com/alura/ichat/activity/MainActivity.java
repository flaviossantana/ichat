package br.com.alura.ichat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.alura.ichat.R;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.callback.OuvirMensagensCallback;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.ChatService;
import br.com.alura.ichat.service.IChatService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private int idCliente;
    private EditText texto;
    private Button btnEnviar;
    private List<Mensagem> mensagens;
    private ListView mensagensListView;
    private ChatService chatService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idCliente = 1;

        mensagens = mensagens = new ArrayList<>();

        mensagensListView = this.findViewById(R.id.main_lista_menagens);
        mensagensListView.setAdapter(new MensagemAdapter(this, mensagens, idCliente));

        texto = this.findViewById(R.id.main_texto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final IChatService chatService = retrofit.create(IChatService.class);

        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallback(this));

    }

    public void colocaNaLista(Mensagem mensagem) {
        mensagens.add(mensagem);
        MensagemAdapter adapter = new MensagemAdapter(this, mensagens, idCliente);
        mensagensListView.setAdapter(adapter);
        chatService.ouvirMensagens();
    }


}
