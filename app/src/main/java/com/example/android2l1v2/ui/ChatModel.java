package com.example.android2l1v2.ui;

import java.io.Serializable;

public class ChatModel implements Serializable {
    private String chat;

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public ChatModel(String chat) {
        this.chat = chat;
    }
}
