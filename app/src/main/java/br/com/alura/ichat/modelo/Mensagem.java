package br.com.alura.ichat.modelo;

public class Mensagem {

    private int id;
    private String texto;

    public Mensagem() {
        super();
    }

    public Mensagem(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
