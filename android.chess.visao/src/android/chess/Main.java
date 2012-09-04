package android.chess;

import java.util.Iterator;

import android.app.Activity;
import android.chess.dominio.Partida;
import android.chess.dominio.interfaces.IPeca;
import android.chess.visao.Peca;
import android.chess.visao.R;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author augusteiner
 * 
 */
public class Main extends Activity {

    /**
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context c) {
        return c.getResources().getDisplayMetrics();
    }

    /**
     * @return
     */
    public ViewGroup getContentView() {
        return (ViewGroup) findViewById(getMainLayoutResource());
    }

    /**
     * @return
     */
    public DisplayMetrics getDisplayMetrics() {
        return getDisplayMetrics(getApplicationContext());
    }

    /**
     * @return
     */
    public int getMainLayoutResource() {
        return R.id.layout_main;
    }

    /**
     *
     */
    private void initTabuleiro() {
        Partida p = new Partida();
        Iterator<IPeca> pecas = p.getTabuleiro().getPecas();
        Context context = getApplicationContext();

        ViewGroup contentView = getContentView();
        Peca peca = null;

        int side = getDisplayMetrics().widthPixels / 8;

        LayoutParams lp = new LayoutParams(side, side);
        lp.addRule(RelativeLayout.ALIGN_TOP, R.id.tabuleiro);

        for (int i = 0, j; i < 2; i++) {
            for (j = 0; j < 7; j++) {
                peca = new Peca(context);
                peca.setTag(pecas.next());
                peca.setLayoutParams(lp);
                // peca.setBackgroundResource(context.getResources().get)

                // contentView.addView(peca);
            }
        }
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
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initTabuleiro();

        setContentView(R.layout.main);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);

        return true;
    }
}
