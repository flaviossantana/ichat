package br.com.alura.ichat.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.greenrobot.eventbus.EventBus;

import br.com.alura.ichat.activity.MainActivity;
import br.com.alura.ichat.event.FailureEvent;
import br.com.alura.ichat.event.MensagemEvent;
import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {

    public static final String MENSAGEM = "mensagem";
    public static final String NOVA_MSG = "nova_msg";

    private Context context;
    private EventBus eventBus;

    public OuvirMensagensCallback(Context context, EventBus eventBus) {
        this.context = context;
        this.eventBus = eventBus;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful()){
            Mensagem mensagem = response.body();
            eventBus.post(new MensagemEvent(mensagem));
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        eventBus.post(new FailureEvent());
    }



}
