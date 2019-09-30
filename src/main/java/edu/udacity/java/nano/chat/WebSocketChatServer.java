package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
/** We declare a Java class WebSocket server endpoint by annotating it with @ServerEndpoint.
 *  We also specify the URI where the endpoint is deployed.
 * */
@ServerEndpoint("/chat")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        //TODO: add send message method.
        // Use RemoteEndpoint.sendString endpoint to send back data

        onlineSessions.forEach((id, session)->{
            System.out.println("Session Id : " + id + " Session : " + session);
            // (1) Iterate each session and return a reference a RemoteEndpoint object representing the peer
            // of this conversation that is able to send messages synchronously to the peer.
            // (2) Broadcast the message
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        // Add the current session to the onlineSessions map
        onlineSessions.put(session.getId(), session);
        // new user enter the chat room, dispatch a message with (1) the updated online session number and (2) user action
        Message userEnterChatRoomMessage = new Message("", "", onlineSessions.size(), "Enter");
        sendMessageToAll(JSON.toJSONString(userEnterChatRoomMessage));
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     * jsonStr: the serialized message object sent by client, with username and message value
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        // Deserialize the message jsonStr from UI and convert it to a new Message instance using fastjson
        // See fastjson doc: https://github.com/alibaba/fastjson/wiki/Quick-Start-CN
        Message userStartNewChatMessage = JSON.parseObject(jsonStr, Message.class);
        userStartNewChatMessage.setType("Chat");
        userStartNewChatMessage.setOnlineCount(onlineSessions.size());
        sendMessageToAll(JSON.toJSONString(userStartNewChatMessage));
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        // Remove current session from the onlineSessions map
        onlineSessions.remove(session.getId());
        Message userLeaveChatRoomMessage = new Message("", "", onlineSessions.size(), "Leave");
        sendMessageToAll(JSON.toJSONString(userLeaveChatRoomMessage));
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
