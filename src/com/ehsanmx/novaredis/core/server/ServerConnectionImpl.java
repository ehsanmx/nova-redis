package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Server;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerConnectionImpl implements ServerConnection {

    private static final String SERVER_DATABASE_PATH = "servers.nrd";

    private ObjectMapper mapper;

    public ServerConnectionImpl() {
        this.mapper = new ObjectMapper();;
    }

    @Override
    public Map<String, Server> findServers(){
        System.out.println("Finding Servers");
        Map<String, Server > servers = this.loadServersFromDatabase();

        servers.forEach((k,v) -> {
            System.out.println(k + " : " + v);
        });

        return servers;
    }

    @Override
    public Server save(Server server) {
        System.out.println("Save Server: " + server);
        Map<String, Server > servers = this.loadServersFromDatabase();
        servers.put(server.getName(), server);
        this.saveServersIntoDatabase(servers);

        return server;
    }

    @Override
    public Server load(String name) {
        System.out.println("Load Server: " + name);
        Map<String, Server > servers = this.loadServersFromDatabase();

        Server server = servers.get(name);
        if (server == null) {
            throw new RuntimeException("Server not found");
        }

        return server;
    }

    @Override
    public boolean delete(Server server) {
        System.out.println("Delete Server: " + server);
        Map<String, Server > servers = this.loadServersFromDatabase();
        servers.remove(server.getName());
        this.saveServersIntoDatabase(servers);

        return true;
    }

    @Override
    public boolean testConnection(Server server) {
        System.out.println("Test connection: " + server);
        return false;
    }

    protected Map<String ,Server> loadServersFromDatabase() {
        Map<String, Server> servers = new HashMap<String, Server>();

        File serverDatabaseFile = new File(SERVER_DATABASE_PATH);
        if (!serverDatabaseFile.exists() || serverDatabaseFile.isDirectory()) {
            return servers;
        }

        try {
            servers = this.mapper.readValue(serverDatabaseFile, new TypeReference<Map<String, Server>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return servers;
    }

    protected void saveServersIntoDatabase(Map<String, Server> servers) {
        File serverDatabaseFile = new File(SERVER_DATABASE_PATH);
        if (serverDatabaseFile.isDirectory()) {
            throw new RuntimeException("There is a directory with this name");
        }

        try {
            this.mapper.writeValue(serverDatabaseFile, servers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
