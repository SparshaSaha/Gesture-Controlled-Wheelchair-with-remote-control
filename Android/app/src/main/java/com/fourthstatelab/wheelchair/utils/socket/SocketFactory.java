package com.fourthstatelab.wheelchair.utils.socket;

import com.fourthstatelab.wheelchair.utils.socket.impl.StandardSocketService;
import com.fourthstatelab.wheelchair.utils.socket.impl.WebSocketService;

public class SocketFactory {

    public static SocketWrapper getWebSocket(String endpoint) {
        return new WebSocketService(endpoint);
    }

    public static SocketWrapper getStandardSocket(String endpoint, int port) {
        return new StandardSocketService(endpoint, port);
    }
}
