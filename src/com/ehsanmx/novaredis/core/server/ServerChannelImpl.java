package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Database;
import com.ehsanmx.novaredis.model.Entry;
import com.ehsanmx.novaredis.model.EntryGroup;
import com.ehsanmx.novaredis.model.Server;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

// TODO interface is missing
public class ServerChannelImpl implements ServerChannel {

    public String getMessage() {
        Jedis jedis = new Jedis("10.10.0.191", 10009);
//        jedis.set("foo", "bar");
        return  jedis.get("foo");
    }

    @Override
    public String getServerInfo(Server server) {
        return null;
    }

    @Override
    public Map<String, Database> findServerDatabases(Server server) {
        return null;
    }

    @Override
    public String getServerDatabaseInfo(Database database) {
        return null;
    }

    @Override
    public Entry get(String key) {
        return null;
    }

    @Override
    public void set(String key, String value) {

    }

    @Override
    public Map<String, Entry> match(Database database) {
        return null;
    }

    @Override
    public Map<String, EntryGroup> matchWithGroup(Database database) {
        return null;
    }
}
