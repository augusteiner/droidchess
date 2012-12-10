/**
 *
 */
package android.chess;

import android.chess.visao.R;
import android.chess.controle.UsuarioControle;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.util.events.interfaces.IAsyncCallback;
import android.chess.visao.ActivityCallback;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * @author augusteiner
 *
 */
public class CadastroActivity extends FullWindowActivity {
    /**
     *
     */
    UsuarioControle ctrl;
    /**
     *
     */
    EditText edtEmail;
    /**
     *
     */
    EditText edtLogin;
    /**
     *
     */
    EditText edtNome;
    /**
     *
     */
    EditText edtSenha;
    /**
     * @param view
     */
    public void btnCadastrarOnClick(View view) {
        String login = edtLogin.getText().toString();
        final String senha = edtSenha.getText().toString();
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();

        ctrl.cadastro(login, senha, nome, email,
            new IAsyncCallback<IUsuario>() {

                public void invoke(IUsuario usuario) {
                    ICredenciais cred = usuario.getCredenciais();

                    // parametro senha passado devido a criptografia
                    ctrl.autenticar(cred.getLogin(), senha,
                        new ActivityCallback<IUsuario>(CadastroActivity.this,
                            InicialActivity.class));
                }
            });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);

        ctrl = new UsuarioControle(new Mensageiro(getApplicationContext()));
    }
}
