/**
 *
 */
package android.chess.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.chess.dominio.Usuario;
import android.chess.dominio.interfaces.IJogador;
import android.chess.persistencia.Persistencia;

/**
 * @author augusteiner
 *
 */
public class JogadorNegocio {
    private static class Holder {
        /**
         *
         */
        private static final JogadorNegocio INSTANCIA = new JogadorNegocio();
    }
    /**
     *
     */
    private JogadorNegocio() {

    }
    /**
     * Retorna os jogadores disponíveis para um iniciar uma partida.
     *
     * @param pagina
     *            Caso a quantidade de jogadores seja elevada ao fornecer uma
     *            <code>pagina</code> é possível carregar mais jogadores.
     *
     * @return Iterator da lista de jogadores.
     */
    public List<IJogador> disponiveis(int pagina) {
        Persistencia p = Persistencia.instancia();

        Iterator<Usuario> disponiveis = p.find(Usuario.class, "online", true,
            pagina, IJogador.limitePagina);

        ArrayList<IJogador> lista = new ArrayList<IJogador>();

        while (disponiveis.hasNext()) {
            lista.add(disponiveis.next());
        }

        return lista;
    }
    /**
     * @return
     */
    public static JogadorNegocio instancia() {
        return Holder.INSTANCIA;
    }
}
