package android.chess;

import android.app.Activity;
import android.chess.app.R;
import android.chess.visao.Mensageiro;
import android.chess.visao.exceptions.InicializacaoException;
import android.chess.visao.views.ITabuleiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author augusteiner
 * 
 */
public class Main extends Activity {

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

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
