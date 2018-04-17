package br.com.alura.ichat.component;

import br.com.alura.ichat.activity.MainActivity;
import br.com.alura.ichat.module.ChatModule;
import dagger.Component;

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);

}
