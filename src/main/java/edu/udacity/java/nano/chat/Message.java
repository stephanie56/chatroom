package edu.udacity.java.nano.chat;

/**
 * WebSocket message model
 * Message model is the message payload that will be exchanged between the client and the server.
 * Implement the Message class in chat module. Make sure you cover all there basic types.
 *
 * ENTER
 * CHAT
 * LEAVE
 */

/**
 * String username: username of the current user - get it from UI
 * String msg: message sent by the user - get it from UI
 * int onlineCount: the number of all current online users
 * String type: could be "Enter", "Chat", or "Leave"
 * If Message.type == "Enter", adds current session to the onlineSessions, update onlineCount
 * If Message.type == "Leave", remove current session from the onlineSessions, update onlineCount
 * If Message.type == "Chat", UI takes the message and render messages
 *
 * */
public class Message {
    // TODO: add message model.
    private String username;
    private String msg;
    private int onlineCount;
    private String type;

    public Message(String username, String msg, int onlineCount, String type) {
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
