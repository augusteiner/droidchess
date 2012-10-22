package android.chess.util.events;

import java.util.LinkedList;
import java.util.Queue;

import android.chess.util.events.interfaces.IEvent;
import android.chess.util.events.interfaces.IHandler;

/**
 *
 * @author augusteiner
 *
 * @param <I>
 */
public class Event<I> implements IEvent<I> {
    /**
     *
     */
    private Queue<IHandler<I>> handlers;
    /**
     *
     */
    private static final long serialVersionUID = -8275369616912873426L;

    /**
     * @todo Possivelmente adicionar sender como argumento.
     */
    public Event() {
        handlers = new LinkedList<IHandler<I>>();
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * android.chess.util.events.interfaces.IEvent#addHandler(android.chess.
     * util.events.interfaces.IHandler)
     */
    @Override
    public void addHandler(IHandler<I> handler) {
        getHandlers().add(handler);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.chess.util.events.interfaces.IEvent#raise(java.lang.Object)
     */
    @Override
    public void raise(I info) throws Exception {
        for (IHandler<I> handler : getHandlers()) {
            handler.handle(this, info);
        }
    }

    /**
     * @return
     */
    protected Queue<IHandler<I>> getHandlers() {
        return handlers;
    }
}