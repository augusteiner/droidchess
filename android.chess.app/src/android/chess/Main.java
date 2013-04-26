package android.chess;

import android.chess.app.R;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.chess.visao.exceptions.InicializacaoException;
import android.chess.visao.views.ITabuleiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

/**
 * @author augusteiner
 * 
 */
public class Main extends FullWindowActivity {

    /**
     * @return
     */
    public ViewGroup getContentView() {
        return (ViewGroup) findViewById(getMainLayoutResource());
    }

    /**
     * @return
     */
    public int getMainLayoutResource() {
        return R.id.mainLayout;
    }

    /**
     * @param view
     */
    public void menuExitOnClick(MenuItem v) {
        finish();
    }

    /**
     * @param v
     */
    public void menuRestartOnClick(MenuItem v) {
        initTabuleiro();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        initTabuleiro();
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

    /**
     * @param contentView
     */
    private void initTabuleiro() {
        ViewGroup contentView = (ViewGroup) findViewById(getMainLayoutResource());

        try {
            ((ITabuleiro) findViewById(R.id.tabuleiro)).init(contentView);
        } catch (InicializacaoException e) {
            new Mensageiro(getApplicationContext()).erro(e);
        }
    }
}
