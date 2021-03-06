package br.com.alura.ichat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import br.com.alura.ichat.R;
import br.com.alura.ichat.app.ChatApp;
import br.com.alura.ichat.modelo.Mensagem;
import br.com.alura.ichat.transformation.CircleTransform;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemAdapter extends BaseAdapter {

    private int idCliente;
    private Context context;;
    private List<Mensagem> mensagens;

    @Inject
    public Picasso picasso;

    @BindView(R.id.mensagem_imagem)
    public ImageView imagem;

    @BindView(R.id.mensagem_texto)
    public TextView texto;

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

        ButterKnife.bind(this, view);
        ChatApp.getComponent().inject(this);

        Mensagem mensagem = getItem(position);

        view.setBackgroundColor(corLinha(mensagem));

        texto.setText(mensagem.getText());
        picasso.with(context).load("http://api.adorable.io/avatars/250/" + mensagem.getId() + ".png").transform(new CircleTransform()).into(imagem);

        return view;
    }

    private int corLinha(Mensagem mensagem) {
        return idCliente != mensagem.getId() ? Color.CYAN : Color.WHITE;
    }
}
