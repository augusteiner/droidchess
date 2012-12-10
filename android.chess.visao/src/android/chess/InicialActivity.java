package android.chess;

import android.chess.visao.R;
import android.chess.controle.UsuarioControle;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * @author augusteiner
 *
 */
public class InicialActivity extends FullWindowActivity {
    /**
     *
     */
    public UsuarioControle ctrl;
    /**
     *
     */
    private TextView txtWelcome;
    /**
     * @param view
     */
    public void btnConvidarOnClick(View view) {
        Intent intent = new Intent(this, ConvidarActivity.class);

        startActivity(intent);
    }
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        txtWelcome = (TextView) findViewById(R.id.txtMainWelcome);

        Resources r = getApplicationContext().getResources();

        String welcome = r.getString(R.string.textWelcomeMain);

        // IUsuario usuario =
        // getIntent().getSerializableExtra(IUsuario.Key.name);

        ctrl = new UsuarioControle(new Mensageiro(getApplicationContext()));

        txtWelcome.setText(String.format(welcome, ctrl.getControlado()
            .getNome()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
