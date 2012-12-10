package android.chess;

import java.util.ArrayList;

import android.chess.controle.UsuarioControle;
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IPartida;
import android.chess.util.events.interfaces.IAsyncCallback;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.chess.visao.R;
import android.chess.visao.interfaces.IMensageiro;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author augusteiner
 */
public class ConvidarActivity extends FullWindowActivity {
    /**
     * @author augusteiner
     *
     */
    private class ConvidarAsyncCallback implements IAsyncCallback<IPartida> {
        /*
         * (non-Javadoc)
         *
         * @see
         * android.chess.util.events.interfaces.IAsyncCallback#invoke(java.lang
         * .Object)
         */
        public void invoke(IPartida arg) {
            startActivity(new Intent(ConvidarActivity.this,
                ActivitiesAbstractInfo.getInstancia().getPartidaActivityClass()));
        }

    }
    /**
     * @author augusteiner
     *
     */
    private class ConvidarOnItemLongClickListener
        implements
            OnItemLongClickListener {
        /*
         * (non-Javadoc)
         *
         * @see
         * android.widget.AdapterView.OnItemLongClickListener#onItemLongClick
         * (android.widget.AdapterView, android.view.View, int, long)
         */
        public boolean onItemLongClick(AdapterView<?> adapter, View view,
            int position, long arg3) {

            if (lstJogadores.getOnItemSelectedListener() != null) {
                lstJogadores.getOnItemSelectedListener().onItemSelected(
                    adapter, view, position, arg3);
            }

            return false;
        }
    }
    /**
     * @author augusteiner
     *
     */
    private class ListaConvidarAsyncCallback
        implements
            IAsyncCallback<ArrayList<IJogador>> {
        /*
         * (non-Javadoc)
         *
         * @see
         * android.chess.util.events.interfaces.IAsyncCallback#invoke(java.lang
         * .Object)
         */
        public void invoke(ArrayList<IJogador> arg) {
            lstJogadores.setAdapter(new ArrayAdapter<IJogador>(
                ConvidarActivity.this, android.R.layout.simple_list_item_1,
                android.R.id.text1, arg));
        }
    }
    /**
     *
     */
    private ListView lstJogadores;
    /**
     *
     */
    UsuarioControle ctrl;
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_convidar);

        final IMensageiro msgr = new Mensageiro(getApplicationContext());

        ctrl = new UsuarioControle(msgr);

        lstJogadores = (ListView) findViewById(R.id.lstJogadores);

        lstJogadores
            .setOnItemLongClickListener(new ConvidarOnItemLongClickListener());

        lstJogadores.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View view,
                int position, long arg3) {

                ctrl.convidar(
                    (IJogador) lstJogadores.getItemAtPosition(position),
                    new ConvidarAsyncCallback());

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        ctrl.convidar(0, new ListaConvidarAsyncCallback());
    }
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.convidar, menu);

        return true;
    }
}
