package br.com.alura.ichat.component;

import android.app.Application;

import br.com.alura.ichat.activity.MainActivity;
import br.com.alura.ichat.adapter.MensagemAdapter;
import br.com.alura.ichat.module.ChatModule;
import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);

}
