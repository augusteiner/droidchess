/**
 *
 */
package android.chess;

import android.app.Activity;
import android.chess.app8.R;
import android.chess.controle.UsuarioControle;
import android.chess.visao.Mensageiro;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

/**
 * @author augusteiner
 *
 */
public class Login extends Activity {
    /**
     *
     */
    UsuarioControle ctrl;
    /**
     *
     */
    TextView edtLogin;
    /**
     *
     */
    TextView edtPasswd;
    /**
     * @param view
     */
    public void btnLoginOnClick(View view) {
        try {
            ctrl.autenticar(edtLogin.getText().toString(), edtPasswd.getText()
                .toString());
        } catch (ExceptionInInitializerError e) {
            new Mensageiro(getApplicationContext()).erro(e.getCause()
                .getCause().getCause().getMessage());

            return;
        } catch (Throwable e) {
            new Mensageiro(getApplicationContext()).erro(e.getMessage());

            return;
        }

        Intent intent = new Intent(Login.this, Main.class);

        startActivity(intent);
    }
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);

        return true;
    }
    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        edtLogin = (TextView) findViewById(R.id.edtLogin);
        edtPasswd = (TextView) findViewById(R.id.edtPasswd);

        edtLogin.setText("android-chess");
        edtPasswd.setText("123456");

        ctrl = new UsuarioControle();
    }
}
