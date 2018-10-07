package com.ehsanmx.novaredis.core.server;

import com.ehsanmx.novaredis.model.Database;
import com.ehsanmx.novaredis.model.Entry;
import com.ehsanmx.novaredis.model.EntryGroup;
import com.ehsanmx.novaredis.model.Server;

import java.util.Map;

public interface ServerChannel {

    public String getServerInfo(Server server);

    public Map<String, Database> findServerDatabases(Server server);

    public String getServerDatabaseInfo(Database database);

    public Entry get(String key);

    public void set(String key, String value);

    public Map<String, Entry> match(Database database);

    public Map<String, EntryGroup> matchWithGroup(Database database);
}
