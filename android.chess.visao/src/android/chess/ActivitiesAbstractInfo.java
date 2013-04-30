package android.chess;

/**
 * Classe para fornecer informações específicas de cada plataforma atingida.
 * Esta classe deve prover métodos para recuprar activities específicas a serem
 * iniciadas e outras informações somente definidas para a plataforma a ser
 * atingida.
 * 
 * @author augusteiner
 * 
 * @deprecated
 */
public abstract class ActivitiesAbstractInfo {
    /**
     * Instancia atual a ser utilizada que contém informações.
     */
    private static ActivitiesAbstractInfo instancia;

    /**
     * Retorna a classe da {@link Activity} a ser iniciada para a partida.
     * 
     * @return O objeto {@link Class} da activity.
     */
    // FIXME Utilizar factories para tal propósito?
    public abstract Class<? extends PartidaAbstractActivity> getPartidaActivityClass();

    /**
     * Recupera a instância contendo as informações.
     * 
     * @return A instância.
     */
    public static ActivitiesAbstractInfo getInstancia() {
        return instancia;
    }

    /**
     * Atualiza a instância que contém as informações com a
     * <code>instancia</code> fornecida.
     * 
     * @param instancia
     * A nova instância a ser utilizada.
     */
    public static void setInstancia(ActivitiesAbstractInfo instancia) {
        ActivitiesAbstractInfo.instancia = instancia;
    }
}
