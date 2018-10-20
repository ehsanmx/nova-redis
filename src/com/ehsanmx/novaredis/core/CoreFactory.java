package com.ehsanmx.novaredis.core;

import com.ehsanmx.novaredis.core.redis.RedisClient;
import com.ehsanmx.novaredis.core.redis.RedisClientImpl;
import com.ehsanmx.novaredis.core.server.ServerRepository;
import com.ehsanmx.novaredis.core.server.ServerRepositoryImpl;

public class CoreFactory {

    public RedisClient createRedisClient() {
        return new RedisClientImpl();
    }

    public ServerRepository createServerRepository() {
        return new ServerRepositoryImpl();
    }
}
