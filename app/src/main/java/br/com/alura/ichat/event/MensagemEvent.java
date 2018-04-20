package br.com.alura.ichat.event;

import br.com.alura.ichat.modelo.Mensagem;

public class MensagemEvent {

    private  Mensagem mensagem;

    public MensagemEvent() {
    }

    public MensagemEvent(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }
}
