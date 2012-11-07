package android.chess;

import android.app.Activity;
import android.chess.app8.R;
import android.chess.visao.Mensageiro;
import android.chess.visao.exceptions.InicializacaoException;
import android.chess.visao.views.ITabuleiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author augusteiner
 *
 */
public class Main extends Activity {

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

        ViewGroup mainLayout = (ViewGroup) findViewById(R.id.mainLayout);

        try {
            ((ITabuleiro) findViewById(R.id.tabuleiro)).init(mainLayout);
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