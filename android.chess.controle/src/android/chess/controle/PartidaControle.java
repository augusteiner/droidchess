package android.chess.controle;

import android.chess.controle.exceptions.ExecucaoException;
import android.chess.dominio.Jogador;
import android.chess.dominio.Partida;
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
	private IPartida partida;
	/**
     *
     */
	protected IJogador jogador;

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
		IJogador j1 = new Jogador();
		IJogador j2 = new Jogador();

		partida = new Partida(j1, j2);

		jogador = j1;
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
}