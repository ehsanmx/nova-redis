package com.ehsanmx.novaredis.core.redis;

import com.ehsanmx.novaredis.model.Database;
import com.ehsanmx.novaredis.model.Entry;
import com.ehsanmx.novaredis.model.EntryGroup;
import com.ehsanmx.novaredis.model.Server;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RedisClientImpl implements RedisClient {

    private Jedis jedis;

    @Override
    public String getServerInfo() {
        return this.jedis.info();
    }

    @Override
    public RedisClient build(Server server) {
        this.jedis = new Jedis(server.getHost(), server.getPort());

        return this;
    }

    @Override
    public Map<String, Database> findServerDatabases() {
        List<String> response = this.jedis.configGet("databases");
        String info = this.jedis.info();
        String dbPattern = "(db\\w):(keys=)(\\w+)";

        Pattern pattern = Pattern.compile(dbPattern);
        Matcher matcher = pattern.matcher(info);

        Map<String, Database> databaseMap = new TreeMap<String, Database>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return extractInt(o1) - extractInt(o2);
            }

            private int extractInt(String s) {
                String num = s.replaceAll("\\D", "");
                return num.isEmpty() ? 0 : Integer.parseInt(num);
            }
        });

        while(matcher.find()) {
            Database database = new Database(matcher.group(1), Long.parseLong(matcher.group(3)));
            databaseMap.put(matcher.group(1), database);
            System.out.println(matcher.group(1) +":"+ matcher.group(3));
        }

        String dbSizeString = response.get(1);
        int dbSize = Integer.parseInt(dbSizeString);
        for (int i = 0; i < dbSize; i++) {
            String dbKey = String.format("db%d", i);
            if (databaseMap.get(dbKey) == null) {
                Database database = new Database(dbKey, 0);
                databaseMap.put(dbKey, database);
            }
        }

        return databaseMap;
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

    @Override
    public boolean testConnection() {
        System.out.println("Test connection");
        boolean status = false;
        try {
            jedis.connect();
            status = jedis.isConnected();
            jedis.disconnect();
        } catch (JedisConnectionException exception) {
            System.err.println(exception.getMessage());
        }

        return status;
    }
}
