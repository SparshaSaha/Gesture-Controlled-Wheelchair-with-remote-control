package com.fourthstatelab.wheelchair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.fourthstatelab.wheelchair.utils.socket.SocketFactory;
import com.fourthstatelab.wheelchair.utils.socket.SocketWrapper;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /********* SOCKET TESTS *********/
        SocketWrapper socket = SocketFactory.getWebSocket("ws://2468849a.ngrok.io/socket.io/?EIO=3&transport=websocket");
        socket.on("", String.class, (str) -> {
            Log.d("SocketWrapper", str);
        });
        socket.connect(socketConnection -> {
            Log.d("SocketWrapper", "Connected");
            socket.send("Hey");
        });
        /********* SOCKET TESTS *********/
    }
}
