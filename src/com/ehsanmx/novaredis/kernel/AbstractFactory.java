package com.ehsanmx.novaredis.kernel;

import com.ehsanmx.novaredis.view.ViewFactory;
import com.ehsanmx.novaredis.core.CoreFactory;

public class AbstractFactory {

    protected ViewFactory getControllerFactory() {
        return new ViewFactory();
    }

    protected CoreFactory getCoreFactory() {
        return new CoreFactory();
    }
}
