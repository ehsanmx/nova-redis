package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Database;
import com.ehsanmx.novaredis.model.Entry;
import com.ehsanmx.novaredis.model.Server;

import java.util.List;
import java.util.Map;

public interface ServerConnection {

    public Map<String, Server> findServers();

    public Server save(Server server);

    public Server load(String name);

    public boolean delete(Server server);

    public boolean testConnection(Server server);
}
