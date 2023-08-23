package com.example.ss31.modelClass;

public class Message {

    int id;
    String content;
    boolean sentByUser;

    public Message(int id,String content,boolean sentByUser){
        this.id = id;
        this.content = content;
        this.sentByUser = sentByUser;
    }

    public Message(String content,boolean sentByUser){
        this.content = content;
        this.sentByUser = sentByUser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSentByUser(boolean sentByUser) {
        this.sentByUser = sentByUser;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isSentByUser() {
        return sentByUser;
    }
}