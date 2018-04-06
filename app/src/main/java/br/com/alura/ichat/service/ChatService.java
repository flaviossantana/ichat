package br.com.alura.ichat.service;

import org.json.JSONStringer;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.alura.ichat.modelo.Mensagem;

public class ChatService {

    public void enviar(final Mensagem mensagem) {

        new Thread(new Runnable() {

            @Override
            public void run() {

                String texto = mensagem.getTexto();

                try {

                    // Altere para o seu IP
                    URL url = new URL("http://192.168.0.100:8080/polling");

                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

                    httpConnection.setRequestMethod("POST");

                    httpConnection.setRequestProperty("Content-type", "application/json");

                    JSONStringer json = new JSONStringer()
                            .object()
                            .key("text")
                            .value(texto)
                            .key("id")
                            .value(mensagem.getId())
                            .endObject();

                    OutputStream saida = httpConnection.getOutputStream();
                    PrintStream ps = new PrintStream(saida);

                    ps.println(json.toString());

                    httpConnection.connect();
                    httpConnection.getInputStream();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


}
