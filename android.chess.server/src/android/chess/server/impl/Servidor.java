package android.chess.server.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Map;

import android.chess.dominio.Convite;
import android.chess.dominio.Partida;
import android.chess.dominio.Usuario;
import android.chess.dominio.interfaces.IConvite;
import android.chess.dominio.interfaces.ICredenciais;
import android.chess.dominio.interfaces.IJogada;
import android.chess.dominio.interfaces.IUsuario;
import android.chess.negocio.JogadorNegocio;
import android.chess.server.comunicacao.Requisicao;
import android.chess.server.comunicacao.Resposta;
import android.chess.server.exceptions.RequisicaoException;

/**
 * @author augusteiner
 * 
 */
public class Servidor {
	/**
	 * @author augusteiner
	 * 
	 */
	private static class Holder {
		/**
         *
         */
		private static final Servidor INSTANCIA;
		static {
			try {
				INSTANCIA = new Servidor();
			} catch (Exception e) {
				e.printStackTrace();

				throw new ExceptionInInitializerError(e);
			}
		}
	}

	/**
	 * @author augusteiner
	 * 
	 */
	private class ObjectStreams {
		/**
         *
         */
		private ObjectInputStream in;
		/**
         *
         */
		private ObjectOutputStream out;

		/**
		 * @param client
		 * @throws IOException
		 */
		private ObjectStreams(Socket client) throws IOException {

			in = new ObjectInputStream(client.getInputStream());
			out = new ObjectOutputStream(client.getOutputStream());
		}
	}

	/**
     *
     */
	private Map<IUsuario, IConvite> requisicoes;
	/**
	 * Usuario atual conectado ao servidor.
	 */
	private IUsuario usuario;
	/**
	 * Socket servidor associado a esta fachada.
	 */
	protected ServerSocket socket;
	/**
	 * 
	 * FIXME Colocar configurações em arquivo .xml?
	 */
	public static final String address = "10.0.2.2";
	/**
     *
     */
	public static final int port = 9666;

	/**
	 * @throws IOException
	 */
	private Servidor() throws Exception {
		try {
			socket = new ServerSocket(port);

			requisicoes = new Hashtable<IUsuario, IConvite>();
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * @param arg
	 */
	protected Servidor(boolean arg) {

	}

	/**
	 * Chama o método <code>ServerSocket.accept()</code> na instância singleton
	 * de socket associada a esta fachada.
	 * 
	 * @return Socket cliente a ser servido.
	 * 
	 * @throws IOException
	 * 
	 * @see {@link ServerSocket#accept()}
	 */
	public Socket accept() throws IOException {
		return socket.accept();
	}

	/**
	 * Loop principal do servidor esperando por conexão de clientes a serem
	 * servidos.
	 * 
	 * @throws Exception
	 */
	public void servir() throws Exception {
		// Persistencia.instancia();

		while (true) {
			new ServidorThread(this).start();
		}
	}

	/**
	 * Serve conteúdo para um cliente conectado via o socket informado.
	 * 
	 * @param client
	 *            Socket de comunicação com o cliente.
	 * 
	 * @throws IOException
	 * 
	 * @throws ClassNotFoundException
	 */
	public void servir(Socket client) throws IOException,
			ClassNotFoundException, Exception {

		ObjectStreams streams = new ObjectStreams(client);

		while (true) {
			continuarServindo(streams);
		}
	}

	/**
	 * Calcula a resposta a um cadastro de jogador/usuário sendo requisitado.
	 * 
	 * @param requisicao
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @todo Implementar busca do jogador/usuario e verificar credenciais.
	 */
	private Resposta autenticar(ICredenciais credenciais) throws Exception {

		// Class<Usuario> uclass = Usuario.class;

		// Persistencia p = Persistencia.instancia();
		Usuario usr = null;

		// try {
		// usr = p.find(uclass, "login", credenciais.getLogin());
		// } catch (NoSuchFieldException e) {
		// throw new AutenticacaoException(e);
		// }

		// if (usr != null
		// && credenciais.getSenha().equals(
		// usr.getCredenciais().getSenha())) {
		// usr.setOnline(true);
		//
		// persistir(usr);

		return new Resposta(usr, usr);
		// } else {
		// throw new AutenticacaoException(credenciais);
		// }
	}

	/**
	 * @param usuario
	 * @return
	 * @throws Exception
	 */
	private Resposta cadastrar(IUsuario usuario) throws Exception {
		persistir(usuario);

		return new Resposta(null, usuario);
	}

	/**
	 * @param streams
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws Exception
	 */
	private void continuarServindo(ObjectStreams streams) throws IOException,
			ClassNotFoundException, Exception {

		Requisicao r = (Requisicao) streams.in.readObject();

		Object response = null;

		try {
			response = responder(r);
		} catch (Exception e) {
			if (e instanceof Serializable)
				response = new Resposta(r.getRemetente(),
						new RequisicaoException(e));
			else
				throw e;
		}

		streams.out.writeObject(response);

		// in.close();

		streams.out.flush();
		// out.close();
	}

	/**
	 * @param mensagem
	 * @return
	 */
	private Resposta convidar(Integer index) {
		return new Resposta(null, JogadorNegocio.instancia().disponiveis(index));
	}

	/**
	 * @return
	 */
	private Resposta convites() {
		IConvite convite = requisicoes.get(usuario);

		return new Resposta(usuario, convite);
	}

	/**
	 * Calcula a resposta a uma jogada sendo requisitada.
	 * 
	 * @param requisicao
	 * 
	 * @param jogada
	 * @return
	 */
	private Resposta jogada(IJogada jogada) {
		return new Resposta(null, jogada);
	}

	/**
	 * @param remetente
	 * @param destinatario
	 * @return
	 */
	private Resposta partida(IUsuario remetente, IUsuario destinatario) {
		requisicoes.put(destinatario, new Convite(remetente, destinatario));

		return new Resposta(destinatario, new Partida(remetente, destinatario));
	}

	/**
	 * @param usr
	 */
	private void persistir(IUsuario usr) throws Exception {
		// Persistencia p = Persistencia.instancia();
		//
		// p.beginTransaction();
		//
		// try {
		// p.persist(usr);
		//
		// p.flush();
		// } catch (Exception e) {
		// p.rollback();
		//
		// throw e;
		// }
		//
		// p.commit();
	}

	/**
	 * Calcula a resposta a ser enviada de acordo com a requisição recebida.
	 * 
	 * @return Resposta a requisição solicitada.
	 * 
	 * @throws RequisicaoException
	 *             Caso o tipo da requisição seja desconhecido.
	 */
	private Resposta responder(Requisicao r) throws Exception {
		switch (r.getTipo()) {
		case CONVITE:
			return convites();
		case CADASTRO:
			return cadastrar((IUsuario) r.getMensagem());
		case PARTIDA:
			return partida(r.getRemetente(), (IUsuario) r.getMensagem());
		case JOGADA:
			return jogada((IJogada) r.getMensagem());
		case DESCONECTAR:
			Runtime.getRuntime().exit(0);
		case AUTENTICAR:
			return autenticar((ICredenciais) r.getMensagem());
		case CONVIDAR:
			return convidar((Integer) r.getMensagem());
		default:
			throw new RequisicaoException(r);
		}
	}

	/**
	 * Retorna instância única associada a esta fachada.
	 * 
	 * @return
	 */
	public static Servidor getInstancia() {
		return Holder.INSTANCIA;
	}

	/**
	 * Cria um novo socket de comunicação com o socket servidor associado a esta
	 * instância.
	 * 
	 * @return Socket cliente para comunicação com servidor.
	 * 
	 * @throws IOException
	 * 
	 * @see {@link Socket#Socket(String, int)}
	 */
	public static Socket novoSocket() throws IOException {
		return new Socket(address, port);
	}

}