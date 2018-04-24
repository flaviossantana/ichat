package br.com.alura.ichat.module;

import android.app.Application;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

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
                .baseUrl("https://ichatapi.herokuapp.com/")
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

    @Provides
    public InputMethodManager inputMethodManager(){
        return (InputMethodManager) app.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
