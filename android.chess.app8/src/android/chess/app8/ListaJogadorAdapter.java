package android.chess.app8;

import java.util.ArrayList;
import java.util.Iterator;

import android.chess.dominio.interfaces.IJogador;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author augusteiner
 *
 */
public class ListaJogadorAdapter extends BaseAdapter {
    ArrayList<IJogador> lista;
    /**
     * @param iterator
     */
    public ListaJogadorAdapter(Iterator<IJogador> iterator) {
        super();

        lista = new ArrayList<IJogador>();

        while (iterator.hasNext()) {
            lista.add(iterator.next());
        }
    }
    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getCount()
     */
    public int getCount() {
        return lista.size();
    }
    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItem(int)
     */
    public Object getItem(int position) {
        return getJogador(position);
    }
    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    public long getItemId(int position) {
        return getJogador(position).getId();
    }
    /*
     * (non-Javadoc)
     *
     * @see android.widget.Adapter#getView(int, android.view.View,
     * android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;

        if (convertView != null && convertView instanceof TextView) {
            view = (TextView) convertView;
        } else {
            view = new TextView(parent.getContext());
        }

        view.setText(getJogador(position).getNome());
        view.setTag(getJogador(position));

        return view;
    }
    /**
     * @param position
     * @return
     */
    private IJogador getJogador(int position) {
        return lista.get(position);
    }
}
