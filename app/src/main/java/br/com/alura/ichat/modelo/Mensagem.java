package br.com.alura.ichat.modelo;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private int id;
    private String text;

    public Mensagem(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
