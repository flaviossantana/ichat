package br.com.alura.ichat.callback;

import br.com.alura.ichat.activity.MainActivity;
import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private final MainActivity activity;

    public OuvirMensagensCallback(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful()){
            Mensagem mensagem = response.body();
            activity.colocaNaLista(mensagem);
            activity.ouvirMensagem();
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {
        activity.ouvirMensagem();
    }

}
