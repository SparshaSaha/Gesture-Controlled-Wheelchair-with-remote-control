package com.fourthstatelab.wheelchair.utils.socket;


import android.support.v4.util.Consumer;

public interface SocketWrapper {
    void connect(Consumer<SocketWrapper> action);

    <T> void on(String key, Class<T> type, Consumer<T> action);

    void removeCallback(String key);

    void send(Object object);

    void close(Runnable runnable);
}
