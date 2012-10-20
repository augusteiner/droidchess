package android.chess.test;

import android.chess.util.events.interfaces.IHandler;

public class EventTest extends Test {
    /**
     * @author augusteiner
     *
     */
    class ChangeEvent extends android.chess.util.events.Event<IChangeInfo> {

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
    /**
     * @throws Exception
     *
     */
    private void change() throws Exception {
        ChangeEvent e = new ChangeEvent();

        e.addHandler(new IHandler<EventTest.IChangeInfo>() {
            @Override
            public void handle(Object sender, IChangeInfo info) {
                EventTest.this.handle2(info);
            }

        });

        e.addHandler(new IHandler<EventTest.IChangeInfo>() {
            @Override
            public void handle(Object sender, IChangeInfo info) {
                EventTest.this.handle(info);
            }

        });

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
    /*
     * (non-Javadoc)
     *
     * @see android.chess.test.Test#run()
     */
    @Override
    public void run() throws Exception {
        change();
    }
}