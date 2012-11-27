package android.chess.controle;

import android.chess.client.ClienteFactory;
import android.chess.controle.exceptions.ExecucaoException;
import android.chess.dominio.Usuario;
import android.chess.dominio.excecao.ChessException;
import android.chess.dominio.excecao.TurnoException;
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
    private IPartida partida;
    /**
     *
     */
    protected Usuario jogador;
    /**
     * 
     * @throws ExecucaoException
     * @throws RequisicaoException
     * 
     * @todo Adicionar jogadores como parametro.
     * @todo Criar evento para repassar a UI?
     * @todo Deve requisitar à aplicação servidora uma nova partida.
     * @todo Implementar escolha/convite de adversário.
     */
    public PartidaControle() throws ExecucaoException {
        AcaoThread r = new AcaoThread() {
            @Override
            protected void executar() throws Exception {
                jogador = ClienteFactory.getPadrao().getUsuario();
            }
        };

        threadAndJoin(r);
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
    public void novaPartida() throws ExecucaoException {
        AcaoThread r = new AcaoThread() {
            @Override
            protected void executar() throws Exception {
                partida = ClienteFactory.getPadrao().novaPartida();
            }
        };

        threadAndJoin(r);
    }
    /**
     * @param r
     * @return
     */
    private Thread thread(AcaoThread r) {
        Thread t = new Thread(r);

        t.start();

        return t;
    }
    /**
     * @param r
     * @throws InterruptedException
     */
    private void threadAndJoin(AcaoThread r) throws ExecucaoException {
        Thread t = thread(r);

        try {
            t.join();

            if (r.ocorreuExcecao()) {
                throw r.getException();
            }
        } catch (Exception e) {
            throw new ExecucaoException(e);
        }
    }
}