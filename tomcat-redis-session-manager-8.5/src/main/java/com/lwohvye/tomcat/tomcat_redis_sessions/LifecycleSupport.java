package com.lwohvye.tomcat.tomcat_redis_sessions;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public final class LifecycleSupport {
    private final Lifecycle lifecycle;
    private final List<LifecycleListener> listeners = new CopyOnWriteArrayList();

    public LifecycleSupport(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public void addLifecycleListener(LifecycleListener listener) {
        this.listeners.add(listener);
    }

    public LifecycleListener[] findLifecycleListeners() {
        return (LifecycleListener[])this.listeners.toArray(new LifecycleListener[0]);
    }

    public void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(this.lifecycle, type, data);
        Iterator i$ = this.listeners.iterator();

        while(i$.hasNext()) {
            LifecycleListener listener = (LifecycleListener)i$.next();
            listener.lifecycleEvent(event);
        }

    }

    public void removeLifecycleListener(LifecycleListener listener) {
        this.listeners.remove(listener);
    }
}
