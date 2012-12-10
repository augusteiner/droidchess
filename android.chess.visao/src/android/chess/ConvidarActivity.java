package android.chess;

import java.util.ArrayList;

import android.chess.visao.R;
import android.chess.controle.UsuarioControle;
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IPartida;
import android.chess.util.events.interfaces.IAsyncCallback;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.chess.visao.interfaces.IMensageiro;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author augusteiner
 */
public class ConvidarActivity extends FullWindowActivity {
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

        setContentView(R.layout.convidar);

        final IMensageiro msgr = new Mensageiro(getApplicationContext());

        ctrl = new UsuarioControle(msgr);

        lstJogadores = (ListView) findViewById(R.id.lstJogadores);

        lstJogadores.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                int position, long arg3) {

                ctrl.convidar((IJogador) lstJogadores.getSelectedItem(),
                    new IAsyncCallback<IPartida>() {

                        public void invoke(IPartida arg) {
                            startActivity(new Intent(ConvidarActivity.this, InicialActivity.class));
                        }

                    });

                return true;
            }
        });

        ctrl.convidar(0, new IAsyncCallback<ArrayList<IJogador>>() {
            public void invoke(ArrayList<IJogador> arg) {
                lstJogadores.setAdapter(new ArrayAdapter<IJogador>(
                    ConvidarActivity.this, android.R.layout.simple_list_item_1,
                    android.R.id.text1, arg));
            }
        });
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
