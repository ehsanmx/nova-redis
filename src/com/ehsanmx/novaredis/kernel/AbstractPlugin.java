package com.ehsanmx.novaredis.kernel;

import com.ehsanmx.novaredis.core.CoreFactory;

public class AbstractPlugin{

    protected CoreFactory getCoreFactory() {
        return new CoreFactory();
    }
}
