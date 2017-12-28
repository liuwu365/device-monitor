package com.device.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Description:
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketListener.class);

    private static Session session;
    private static WebSocketListener myWebSocket;

    public synchronized static WebSocketListener getInstance() {
        if (myWebSocket == null) {
            myWebSocket = new WebSocketListener();
        }
        return myWebSocket;
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        if (this.session.isOpen()) {
            this.session.getBasicRemote().sendText(message);
        } else {
            this.session.close();
        }

    }

}
