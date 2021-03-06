/**
 *
 */
package android.chess.server.comunicacao;

import java.io.Serializable;

import android.chess.dominio.interfaces.IUsuario;

/**
 * Resposta enviada pelo servidor a um cliente conectado.
 *
 * @author augusteiner
 */
public class Resposta implements Serializable {

    /**
     * Destinatário da resposta do servidor.
     */
    private IUsuario destinatario;
    /**
     * Conteúdo a ser enviado ao {@link #destinatario} pelo servidor.
     */
    private Object mensagem;
    /**
     *
     */
    private static final long serialVersionUID = -6060812451523286920L;
    /**
     * @param destinatario
     */
    public Resposta(IUsuario destinatario) {
        this(destinatario, null);
    }
    /**
     * @param destinatario
     *
     * @param mensagem
     */
    public Resposta(IUsuario destinatario, Object mensagem) {
        this.destinatario = destinatario;
        this.mensagem = mensagem;
    }
    /**
     * @return O destinatário desta resposta.
     */
    public IUsuario getDestinatario() {
        return destinatario;
    }

    /**
     * @return Mensagem enviada pelo servidor.
     */
    public Object getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem
     *            Nova mensagem a ser enviada ao cliente.
     */
    public void setMensagem(Object mensagem) {
        this.mensagem = mensagem;
    }
}
