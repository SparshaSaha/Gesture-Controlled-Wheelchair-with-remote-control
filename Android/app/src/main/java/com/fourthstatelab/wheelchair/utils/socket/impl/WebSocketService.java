package com.fourthstatelab.wheelchair.utils.socket.impl;


import android.support.v4.util.Consumer;
import android.util.Log;

import com.fourthstatelab.wheelchair.utils.socket.SocketWrapper;
import com.fourthstatelab.wheelchair.utils.socket.CallbackConsumer;
import com.fourthstatelab.wheelchair.utils.CommonUtil;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import static com.fourthstatelab.wheelchair.utils.socket.constant.CallbackConstant.CONNECT;

public class WebSocketService implements SocketWrapper {

    CallbackConsumer callbackConsumers;

    WebSocketClient socketClient;

    public WebSocketService(String endpoint) {

        URI endpointUri = null;
        try {
            endpointUri = new URI(endpoint);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        socketClient = new WebSocketClient(endpointUri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                callbackConsumers.executeCallback(CONNECT, WebSocketService.this);
            }

            @Override
            public void onMessage(String message) {
                // TODO parse to socket dto
                callbackConsumers.executeCallback("", message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d("SocketWrapper CLosed", reason + code);
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };

        callbackConsumers = new CallbackConsumer();
    }

    @Override
    public void connect(Consumer<SocketWrapper> action) {
        callbackConsumers.on(CONNECT, action, SocketWrapper.class);
        CommonUtil.runOnNewThread(() -> socketClient.connect());
    }

    @Override
    public <T> void on(String key, Class<T> type, Consumer<T> action) {
        callbackConsumers.on(key, action, type);
    }

    @Override
    public void send(Object object) {
        socketClient.send(CommonUtil.toJson(object));
    }

    @Override
    public void close(Runnable runnable) {
        socketClient.close();
    }

    @Override
    public void removeCallback(String key) {
        callbackConsumers.removeCallback(key);
    }
}
