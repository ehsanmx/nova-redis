package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Database;
import com.ehsanmx.novaredis.model.Entry;
import com.ehsanmx.novaredis.model.Server;

import java.util.List;

public interface ServerConnection {

    public List<Server> findServers();

    public Server save(Server server);

    public Server load(Server server);

    public boolean delete(Server server);

    public boolean testConnection(Server server);
}
