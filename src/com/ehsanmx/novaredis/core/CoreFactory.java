package com.ehsanmx.novaredis.core;

import com.ehsanmx.novaredis.core.server.ServerChannel;
import com.ehsanmx.novaredis.core.server.ServerChannelImpl;
import com.ehsanmx.novaredis.core.server.ServerConnection;
import com.ehsanmx.novaredis.core.server.ServerConnectionImpl;
import redis.clients.jedis.Jedis;

public class CoreFactory {

    public ServerChannel createServerChannel() {
        return new ServerChannelImpl();
    }

    public ServerConnection createServerConnection() {
        return new ServerConnectionImpl();
    }
}
