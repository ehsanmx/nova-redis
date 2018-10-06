package com.ehsanmx.novaredis.core;

import com.ehsanmx.novaredis.core.server.ServerChannelImpl;

public class CoreFactory {

    public ServerChannelImpl createReader() {
        return new ServerChannelImpl();
    }
}
