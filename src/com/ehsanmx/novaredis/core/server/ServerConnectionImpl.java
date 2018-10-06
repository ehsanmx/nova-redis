package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Server;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerConnectionImpl implements ServerConnection {

    public ServerConnectionImpl() {
        try {
            this.writeJson();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    protected void writeJson() throws Exception{

        List<Server> servers = new ArrayList<Server>();

        ObjectMapper mapper = new ObjectMapper();
//        Server server1 = new Server("10.10.0.191", 10009);
//        Server server2 = new Server("10.10.0.121", 10007);
//        servers.add(server1);
//        servers.add(server2);
//        mapper.writeValue(new File("file.json"), servers);


        //JSON from file to Object
        servers = mapper.readValue(new File("file.json"), new TypeReference<List<Server>>(){});
        for(Server server: servers) {
            System.out.println(server.getHost());
        }
    }

    @Override
    public List<Server> findServers() {
        return null;
    }

    @Override
    public Server save(Server server) {
        return null;
    }

    @Override
    public Server load(Server server) {
        return null;
    }

    @Override
    public boolean delete(Server server) {
        return false;
    }

    @Override
    public boolean testConnection(Server server) {
        return false;
    }
}
