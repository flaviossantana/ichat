package br.com.alura.ichat.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import br.com.alura.ichat.activity.MainActivity;
import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {

    public static final String MENSAGEM = "mensagem";
    public static final String NOVA_MSG = "nova_msg";

    private Context context;

    public OuvirMensagensCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful()){
            Mensagem mensagem = response.body();
            sendMensagem(mensagem);
        }
    }

    private void sendMensagem(Mensagem mensagem) {
        Intent intent = new Intent(NOVA_MSG);
        intent.putExtra(MENSAGEM, mensagem);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(context);
        broadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        ((MainActivity)context).ouvirMensagem();
    }

}
