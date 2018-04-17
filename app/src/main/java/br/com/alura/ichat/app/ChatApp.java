package br.com.alura.ichat.app;

import android.app.Application;

import br.com.alura.ichat.component.ChatComponent;
import br.com.alura.ichat.component.DaggerChatComponent;

public class ChatApp extends Application {

    private static ChatComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerChatComponent.builder().build();
    }

    public static ChatComponent getComponent() {
        return component;
    }
}
