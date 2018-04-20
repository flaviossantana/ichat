package br.com.alura.ichat.module;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.alura.ichat.service.IChatService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ChatModule {

    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }

    @Provides
    public IChatService chatService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.103:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IChatService.class);
    }

    @Provides
    public Picasso picasso(){
        return new Picasso.Builder(app).build();
    }

    @Provides
    public EventBus eventBus(){
        return EventBus.builder().build();
    }

}
