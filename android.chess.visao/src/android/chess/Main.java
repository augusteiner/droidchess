package android.chess;

import javax.swing.text.View;

import android.app.Activity;
import android.chess.visao.R;
import android.chess.visao.Tabuleiro;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
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
     * @param view
     */
    public void menuExitOnClick(View v) {
        finish();
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

        ViewGroup contentView = (ViewGroup) findViewById(R.id.layout_main);

        ((Tabuleiro) findViewById(R.id.tabuleiro)).init(contentView);
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
