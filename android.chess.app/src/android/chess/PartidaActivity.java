package android.chess;

import android.chess.app.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @author augusteiner
 * 
 */
public class PartidaActivity extends PartidaAbstractActivity {

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
    // private void initTabuleiro() {
    // super.ini
    // ViewGroup contentView = (ViewGroup)
    // findViewById(getMainLayoutResource());
    //
    // try {
    // ((ITabuleiro) findViewById(getta)).init(this, contentView);
    // } catch (InicializacaoException e) {
    // new Mensageiro(getApplicationContext()).erro(e);
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.PartidaAbstractActivity#getContentLayoutId()
     */
    @Override
    public int getContentLayoutId() {
        return R.layout.main;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.PartidaAbstractActivity#getMainViewId()
     */
    public int getMainViewId() {
        return R.id.mainLayout;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.chess.PartidaAbstractActivity#getTabuleiroViewId()
     */
    @Override
    public int getTabuleiroViewId() {
        return R.id.tabuleiro;
    }
}
