package android.chess.test;

import android.chess.util.events.interfaces.IHandler;

public class Evento extends Test {
    private final class IHandlerImplementation
        implements
            IHandler<Evento.IChangeInfo> {
        /**
         *
         */
        private static final long serialVersionUID = 1433630133431950981L;

        @Override
        public void handle(Object sender, IChangeInfo info) {
            Evento.this.handle2(info);
        }
    }
    private final class IHandlerImplementation2
        implements
            IHandler<Evento.IChangeInfo> {
        /**
         *
         */
        private static final long serialVersionUID = 8484815804896058728L;

        @Override
        public void handle(Object sender, IChangeInfo info) {
            Evento.this.handle(info);
        }
    }
    /**
     * @author augusteiner
     * 
     */
    class ChangeEvent extends android.chess.util.events.Event<IChangeInfo> {

        /**
         *
         */
        private static final long serialVersionUID = 3286470807183027900L;

    }
    /**
     * @author augusteiner
     * 
     */
    class ChangeInfo implements IChangeInfo {

        private Object state;

        /**
         * @param state
         */
        public ChangeInfo(Object state) {
            this.state = state;
        }
        /*
         * (non-Javadoc)
         * 
         * @see android.chess.test.EventTest.IChangeInfo#getState()
         */
        @Override
        public Object getState() {
            return state;
        }
    }
    /**
     * @author augusteiner
     * 
     */
    interface IChangeInfo {
        /**
         * @return
         */
        Object getState();
    }
    /*
     * (non-Javadoc)
     * 
     * @see android.chess.test.Test#run()
     */
    @Override
    public void run() throws Exception {
        change();
    }
    /**
     * @throws Exception
     * 
     */
    private void change() throws Exception {
        ChangeEvent e = new ChangeEvent();

        e.addHandler(new IHandlerImplementation());

        e.addHandler(new IHandlerImplementation2());

        e.raise(new ChangeInfo("Blablabla"));
    }
    /**
     * @param info
     */
    private void handle(IChangeInfo info) {
        System.out.println(String.format("1# Changed something (%s).",
            info.getState()));
    }
    /**
     * @param info
     */
    protected void handle2(IChangeInfo info) {
        System.out.println(String.format("2# Changed something (%s).",
            info.getState()));
    }
}