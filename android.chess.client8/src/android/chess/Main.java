package android.chess;

import android.app.Activity;
import android.chess.client8.R;
import android.chess.visao.interfaces.ITabuleiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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