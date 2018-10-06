package com.ehsanmx.novaredis.model;

public class Server {

    private String name;

    private String host;

    private int port;

    private String password;

    public Server() { }

    public Server(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    public Server(String name, String host, int port, String password) {
        this.name = name;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
