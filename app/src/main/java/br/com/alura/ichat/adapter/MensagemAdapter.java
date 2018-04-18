package br.com.alura.ichat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alura.ichat.R;
import br.com.alura.ichat.modelo.Mensagem;
import butterknife.BindView;

public class MensagemAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Context context;;
    private int idCliente;

    @BindView(R.id.mensagem_imagem)
    private ImageView imgTexto;

    @BindView(R.id.mensagem_texto)
    private TextView texto;

    public MensagemAdapter(Context context, List<Mensagem> mensagens, int idCliente) {
        this.mensagens = mensagens;
        this.context = context;
        this.idCliente = idCliente;
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

        texto = view.findViewById(R.id.mensagem_texto);

        Mensagem mensagem = getItem(position);

        if(idCliente != mensagem.getId()){
            view.setBackgroundColor(Color.CYAN);
        }

        texto.setText(mensagem.getText());


        return view;
    }
}
