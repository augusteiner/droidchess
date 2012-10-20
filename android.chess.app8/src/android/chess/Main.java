package android.chess;

import android.app.Activity;
import android.chess.app8.R;
import android.chess.visao.interfaces.ITabuleiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);

        ViewGroup mainLayout = (ViewGroup) findViewById(R.id.mainLayout);

        ((ITabuleiro) findViewById(R.id.tabuleiro)).init(mainLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
}