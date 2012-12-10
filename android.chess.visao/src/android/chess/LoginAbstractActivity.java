/**
 *
 */
package android.chess;

import android.chess.visao.R;
import android.chess.controle.UsuarioControle;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.visao.ActivityCallback;
import android.chess.visao.FullWindowActivity;
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
public class LoginAbstractActivity extends FullWindowActivity {
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
        String login = edtLogin.getText().toString();
        String senha = edtPasswd.getText().toString();

        ctrl.autenticar(login, senha, new ActivityCallback<IUsuario>(this,
            InicialActivity.class));
    }
    /**
     * @param view
     */
    public void btnRegisterOnClick(View view) {
        Intent intent = new Intent(LoginAbstractActivity.this,
            CadastroActivity.class);

        startActivity(intent);
    }
    /**
     * @param view
     */
    public void btnSairOnClick(View view) {
        finish();
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

        setContentView(R.layout.activity_login);

        edtLogin = (TextView) findViewById(R.id.edtLogin);
        edtPasswd = (TextView) findViewById(R.id.edtPasswd);

        edtLogin.setText("android-chess");
        edtPasswd.setText("123456");

        ctrl = new UsuarioControle(new Mensageiro(getApplicationContext()));
    }
}
