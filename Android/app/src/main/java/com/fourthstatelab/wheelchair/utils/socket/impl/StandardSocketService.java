package com.fourthstatelab.wheelchair.utils.socket.impl;

import android.support.v4.util.Consumer;

import com.fourthstatelab.wheelchair.utils.CommonUtil;
import com.fourthstatelab.wheelchair.utils.socket.SocketWrapper;
import com.fourthstatelab.wheelchair.utils.socket.CallbackConsumer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;

public class StandardSocketService implements SocketWrapper {

    private String endpoint;

    private int port;

    private java.net.Socket socketClient;

    private CallbackConsumer callbackConsumers;

    public StandardSocketService(String endpoint, int port) {
        this.endpoint = endpoint;
        this.port = port;
        callbackConsumers = new CallbackConsumer();
    }

    @Override
    public void connect(Consumer<SocketWrapper> action) {
        CommonUtil.runOnNewThread(() -> {
            try {
                socketClient = new java.net.Socket(InetAddress.getByName(endpoint), port);
                action.accept(StandardSocketService.this);
                startReceive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public <T> void on(String key, Class<T> type, Consumer<T> action) {
        callbackConsumers.on(key, action, type);
    }

    @Override
    public void send(Object object) {
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter
                    (new OutputStreamWriter(socketClient.getOutputStream())), true);
            writer.println(CommonUtil.toJson(object));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loops around to check and read the socket buffer
     */
    private void startReceive() {
        CommonUtil.runOnNewThread(() -> {
            while (!Thread.currentThread().isInterrupted() && !socketClient.isClosed()) {
                try {
                    String received = new BufferedReader(
                            new InputStreamReader(socketClient.getInputStream()))
                            .readLine();
                    if (received == null) {
                        continue;
                    }
                    // TODO parse to socket dto
                    callbackConsumers.executeCallback("", received);

                } catch (IOException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    @Override
    public void close(Runnable runnable) {
        try {
            socketClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeCallback(String key) {
        callbackConsumers.removeCallback(key);
    }
}
