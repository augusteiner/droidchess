package android.chess.controle;

import android.chess.client.ClienteFactory;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.TurnoException;
import android.chess.dominio.interfaces.IJogador;
import android.chess.dominio.interfaces.IPartida;
import android.chess.dominio.interfaces.ITabuleiro;
import android.chess.dominio.pecas.interfaces.IPeca.Cor;
import android.chess.server.exceptions.RequisicaoException;

/**
 * @author augusteiner
 *
 */
public class PartidaControle extends Controle<IPartida> {
    /**
     *
     */
    private IJogador jogador;
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
    public PartidaControle(JogadorControle controle) {
        this.jogador = controle.getControlado();
    }
    /*
     * (non-Javadoc)
     *
     * @see android.chess.controle.Controle#getControlado()
     */
    @Override
    public IPartida getControlado() {
        return partida;
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
        if (getTurno() != jogador.getCor())
            throw new TurnoException(jogador.getCor().outra());

        partida.jogada(origI, origJ, destI, destJ);
    }
    /**
     * @throws RequisicaoException
     */
    public void novaPartida() throws RequisicaoException {
        partida = ClienteFactory.getPadrao().novaPartida();
    }
}