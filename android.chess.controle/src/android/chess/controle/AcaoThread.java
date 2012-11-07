package android.chess.controle;

/**
 * Executa uma ação em uma thread.
 *
 * @author augusteiner
 */
public abstract class AcaoThread implements Runnable {
    private Exception exception;
    /**
     * @return Exceção jogada durante execução do método {@link #executar()}.
     */
    public Exception getException() {
        return exception;
    }
    /**
     * Retorna se ocorreu uma exceção ao se executar o método
     * {@link #executar()}.
     *
     * @return <code>true</code> caso tenha ocorrido exceção, <code>false</code>
     *         caso contrário.
     */
    public boolean ocorreuExcecao() {
        return exception != null;
    }
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            executar();
        } catch (Exception e) {
            this.exception = e;
        }
    }
    /**
     * Possibilita execução de uma ação com possível lançamento de exception,
     * age em conjunto com o método {@link #run()}
     */
    protected abstract void executar() throws Exception;
}