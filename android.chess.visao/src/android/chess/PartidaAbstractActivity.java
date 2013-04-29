package android.chess;

import android.app.AlertDialog.Builder;
import android.chess.dominio.events.handlers.IAntesPromocaoHandler;
import android.chess.dominio.events.info.interfaces.IPromocaoInfo;
import android.chess.dominio.excecao.ChessException;
import android.chess.visao.FullWindowActivity;
import android.chess.visao.Mensageiro;
import android.chess.visao.R;
import android.chess.visao.exceptions.InicializacaoException;
import android.chess.visao.views.ITabuleiro;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;

/**
 * @author augusteiner
 * 
 */
public abstract class PartidaAbstractActivity extends FullWindowActivity
        implements IAntesPromocaoHandler {

    /**
     * @return
     */
    public abstract int getContentLayoutId();

    /**
     * @return
     */
    public abstract int getMainViewId();

    /**
     * @return
     */
    public abstract int getTabuleiroViewId(); // R.id.tabuleiro

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentLayoutId());

        initTabuleiro();
    }

    /**
     * @param mainLayout
     */
    protected void initTabuleiro() {
        try {
            ViewGroup mainLayout = (ViewGroup) findViewById(getMainViewId());

            ITabuleiro tabuleiro = (ITabuleiro) findViewById(getTabuleiroViewId());

            tabuleiro.init(this, mainLayout);
        } catch (InicializacaoException e) {
            new Mensageiro(getApplicationContext()).erro(e);
        }
    }

    /**
     * Exibe um diálogo para escolha da peça da promoção.
     */
    public void showPromotionDialog(IPromocaoInfo info) {
        final IPromocaoInfo infoRef = info;

        Builder b = new Builder(this);

        b.setCancelable(false);
        b.setTitle(R.string.promoteTitle);

        b.setSingleChoiceItems(R.array.itemsPromotion, -1,
                new OnClickListener() {

                    /*
                     * (non-Javadoc)
                     * 
                     * @see
                     * android.content.DialogInterface.OnClickListener#onClick
                     * (android .content.DialogInterface, int)
                     */
                    public void onClick(DialogInterface dialog, int choice) {

                        try {
                            infoRef.setTipoPromocao(choice);

                            infoRef.callback();
                        } catch (ChessException e) {
                            e.printStackTrace();

                            Mensageiro.makeText(PartidaAbstractActivity.this,
                                    e.getMessage(), Mensageiro.LENGTH_LONG);
                            // throw e;
                            return;
                        }

                        dialog.dismiss();
                    }
                });

        b.create().show();
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * android.chess.dominio.events.handlers.IAntesPromocaoHandler#onAntesPromocao
     * (android.chess.dominio.events.info.interfaces.IPromocaoInfo)
     */
    public void onAntesPromocao(IPromocaoInfo info) throws ChessException {
        showPromotionDialog(info);
    }
}