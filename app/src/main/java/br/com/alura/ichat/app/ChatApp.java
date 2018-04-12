package br.com.alura.ichat.app;

import android.app.Application;

import br.com.alura.ichat.service.IChatService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatApp extends Application {

    public IChatService chatService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IChatService.class);
    }

}
