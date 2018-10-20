package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Server;

import java.util.Map;

public interface ServerRepository {

    public Map<String, Server> findServers();

    public Server save(Server server);

    public Server load(String name);

    public boolean delete(Server server);
}
