package br.com.alura.ichat.callback;

import br.com.alura.ichat.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnviarMensagensCallback implements Callback<Void> {

    public EnviarMensagensCallback() {

    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.isSuccessful()){

        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {

    }
}
