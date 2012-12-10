package android.chess;

import android.chess.visao.R;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.chess.visao.exceptions.InicializacaoException;
import android.chess.visao.views.ITabuleiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

/**
 * @author augusteiner
 *
 */
public abstract class PartidaAbstractActivity extends FullWindowActivity {
    /**
     * @return
     */
    public abstract int getContentViewLayout();// R.layout.partida
    /**
     * @return
     */
    public abstract int getTabuleiroViewId(); // R.id.tabuleiro
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewLayout());

        ViewGroup mainLayout = (ViewGroup) findViewById(getContentViewLayout());

        try {
            ((ITabuleiro) findViewById(getTabuleiroViewId())).init(mainLayout);
        } catch (InicializacaoException e) {
            new Mensageiro(getApplicationContext()).erro(e);
        }
    }
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
}