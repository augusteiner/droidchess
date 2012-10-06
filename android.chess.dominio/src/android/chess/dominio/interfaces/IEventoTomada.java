package android.chess.dominio.interfaces;

/**
 * @author augusteiner
 * 
 */
public interface IEventoTomada {

    /**
     * @return
     */
    public IPeca getDest();

    /**
     * @return
     */
    public IPeca getOrig();
}
