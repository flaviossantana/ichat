package br.com.alura.ichat.callback;

import br.com.alura.ichat.activity.MainActivity;
import br.com.alura.ichat.modelo.Mensagem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuvirMensagensCallback implements Callback<Mensagem> {

    private final MainActivity mainActivity;

    public OuvirMensagensCallback(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onResponse(Call<Mensagem> call, Response<Mensagem> response) {

        if(response.isSuccessful()){
            Mensagem mensagem = response.body();
            mainActivity.colocaNaLista(mensagem);
        }
    }

    @Override
    public void onFailure(Call<Mensagem> call, Throwable t) {

    }

}
