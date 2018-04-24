package br.com.alura.ichat.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import br.com.alura.ichat.R;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.app.ChatApp;
import br.com.alura.ichat.callback.EnviarMensagensCallback;
import br.com.alura.ichat.callback.OuvirMensagensCallback;
import br.com.alura.ichat.event.FailureEvent;
import br.com.alura.ichat.event.MensagemEvent;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.service.IChatService;
import br.com.alura.ichat.transformation.CircleTransform;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static br.com.alura.ichat.callback.OuvirMensagensCallback.MENSAGEM;
import static br.com.alura.ichat.callback.OuvirMensagensCallback.NOVA_MSG;

public class MainActivity extends AppCompatActivity {

    public static final String MSG_LIST = "MSG_LIST";
    private int idCliente = new Random().nextInt();;

    @Inject
    public IChatService chatService;

    @Inject
    public Picasso picasso;

    @Inject
    public EventBus eventBus;

    @Inject
    public InputMethodManager inputMethodManager;

    @BindView(R.id.main_imagem)
    public ImageView msgImagem;

    @BindView(R.id.main_texto)
    public EditText texto;

    @BindView(R.id.main_btn_envair)
    public Button btnEnviar;

    @BindView(R.id.main_lista_menagens)
    public ListView listaDeMensagens;

    private List<Mensagem> mensagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ChatApp.getComponent().inject(this);
        picasso.with(this).load("http://api.adorable.io/avatars/250/" + idCliente + ".png").into(msgImagem);

        if(savedInstanceState != null){
            mensagens = (List<Mensagem>) savedInstanceState.getSerializable(MSG_LIST);
        }else {
            mensagens = new ArrayList<>();
        }


        listaDeMensagens.setAdapter(new MensagemAdapter(this, mensagens, idCliente));

    }

    @Override
    protected void onResume() {
        super.onResume();
        ouvirMensagem(null);
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(MSG_LIST, (ArrayList<Mensagem>) mensagens);
    }

    @OnClick(R.id.main_btn_envair)
    public void onClickEnviar(View view){
        chatService.enviar(new Mensagem(idCliente, texto.getText().toString())).enqueue(new EnviarMensagensCallback());
        texto.getText().clear();
        inputMethodManager.hideSoftInputFromWindow(texto.getWindowToken(), 0);
    }

    @Subscribe
    public void colocaNaLista(MensagemEvent event) {
        this.mensagens.add(event.getMensagem());
        MensagemAdapter adapter = new MensagemAdapter(this, this.mensagens, idCliente);
        listaDeMensagens.setAdapter(adapter);
    }

    @Subscribe
    public void ouvirMensagem(MensagemEvent event) {
        Call<Mensagem> call = chatService.ouvirMensagem();
        call.enqueue(new OuvirMensagensCallback(this, eventBus));
    }

    @Subscribe
    public void lidarCom(FailureEvent event){
        ouvirMensagem(null);
    }

}
