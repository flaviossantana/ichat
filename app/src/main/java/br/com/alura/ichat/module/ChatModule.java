package br.com.alura.ichat.module;

import br.com.alura.ichat.service.IChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ChatModule {

    @Provides
    public IChatService chatService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IChatService.class);
    }

}
