package br.com.alura.ichat.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.alura.ichat.R;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.app.ChatApp;
import br.com.alura.ichat.callback.EnviarMensagensCallback;
import br.com.alura.ichat.callback.OuvirMensagensCallback;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.IChatService;
import br.com.alura.ichat.transformation.CircleTransform;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idCliente = 1;

    @Inject
    public IChatService chatService;

    @BindView(R.id.main_imagem)
    public ImageView msgImagem;

    @BindView(R.id.main_texto)
    public EditText texto;

    @BindView(R.id.main_btn_envair)
    public Button btnEnviar;

    @BindView(R.id.main_lista_menagens)
    public ListView listaDeMensagens;

    private List<Mensagem> mensagens = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Picasso.with(this).load("http://api.adorable.io/avatars/250/" + idCliente + ".png").into(msgImagem);

        ChatApp.getComponent().inject(this);

        mensagens = new ArrayList<>();

        listaDeMensagens.setAdapter(new MensagemAdapter(this, mensagens, idCliente));

        ouvirMensagem();

    }

    @OnClick(R.id.main_btn_envair)
    public void onClickEnviar(View view){
        chatService.enviar(new Mensagem(idCliente, texto.getText().toString())).enqueue(new EnviarMensagensCallback());
        texto.setText("");
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
