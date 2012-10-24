package android.chess.controle;

<<<<<<< HEAD
import android.chess.client.Cliente;
=======
import android.chess.client.ClienteFactory;
>>>>>>> f788d73... Otimizados imports.
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.ITabuleiro;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;
import android.chess.server.exceptions.RequisicaoException;

/**
 * @author augusteiner
 *
 */
public class PartidaControle {
    /**
     *
     */
    private IPartida partida;

    /**
     *
     * @throws RequisicaoException
     *
     * @todo Adicionar jogadores como parametro.
     * @todo Criar evento para repassar a UI?
     * @todo Deve requisitar à aplicação servidora uma nova partida.
     * @todo Implementar escolha/convite de adversário.
     */
    public PartidaControle() {
        //
    }

    /**
     * @return
     */
    public ITabuleiro getTabuleiro() {
        return partida.getTabuleiro();
    }

    /**
     * @return
     */
    public Cor getTurno() {
        return partida.getTurno();
    }

    /**
     *
     * @param origI
     *
     * @param origJ
     *
     * @param destI
     *
     * @param destJ
     *
     * @throws ChessException
     */
    public void mover(int origI, int origJ, int destI, int destJ)
        throws ChessException {
        partida.jogada(origI, origJ, destI, destJ);
    }
    /**
     * @throws RequisicaoException
     */
    public void novaPartida() throws RequisicaoException {
        partida = Cliente.novaPartida();
    }
}