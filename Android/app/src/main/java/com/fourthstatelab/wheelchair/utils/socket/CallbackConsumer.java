package com.fourthstatelab.wheelchair.utils.socket;

import android.support.v4.util.Consumer;

import com.fourthstatelab.wheelchair.utils.CommonUtil;

import java.util.HashMap;

/**
 * @author sridharswain

 * Used to save callbacks to be called when
 * any message comes from server.
 * @see com.fourthstatelab.wheelchair.utils.socket.impl.StandardSocketService
 * @see com.fourthstatelab.wheelchair.utils.socket.impl.WebSocketService
 */
public class CallbackConsumer {
    HashMap<String, Callback> callbackConsumers = new HashMap<>();

    public <T> void on(String key, Consumer<T> consumer, Class<T> type) {
        callbackConsumers.put(key, new Callback(consumer, type));
    }

    public <T> void executeCallback(String key, String rawPayload) {
        Callback<T> callback = callbackConsumers.get(key);
        if (callback != null) {
            T payload = CommonUtil.toObject(rawPayload, callback.type);
            callback.call(payload);
        }
    }

    public <T> void executeCallback(String key, T payload) {
        Callback<T> callback = callbackConsumers.get(key);
        if (callback != null) {
            callback.call(payload);
        }
    }

    public void removeCallback(String key) {
        callbackConsumers.remove(key);
    }

    private class Callback<T> {
        Consumer<T> consumer;

        Class<T> type;

        private Callback(Consumer<T> consumer, Class<T> type) {
            this.consumer = consumer;
            this.type = type;
        }

        private void call(T object) {
            this.consumer.accept(object);
        }
    }
}
