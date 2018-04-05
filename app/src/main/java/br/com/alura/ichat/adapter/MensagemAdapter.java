package br.com.alura.ichat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import br.com.alura.ichat.R;
import br.com.alura.ichat.modelo.Mensagem;

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Context context;

    public MensagemAdapter(List<Mensagem> mensagens, Context context) {
        this.mensagens = mensagens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagem getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.mensagem, parent, false);

        Mensagem mensagem = getItem(position);

        TextView texto = view.findViewById(R.id.mensagem_texto);
        texto.setText(mensagem.getTexto());




        return view;
    }
}
