package com.ehsanmx.novaredis.view;

import com.ehsanmx.novaredis.kernel.AbstractFactory;
import com.ehsanmx.novaredis.view.controller.MainController;
import com.ehsanmx.novaredis.view.controller.ServerController;
import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import javafx.fxml.Initializable;

public class ViewFactory extends AbstractFactory {

    public Initializable createMainController() {
        return new MainController(
                this.getCoreFactory().createServerConnection(),
                this.getCoreFactory().createServerChannel(),
                this.createRedisTreeCell()
        );
    }

    public Initializable createServerController() {
        return new ServerController(
                this.getCoreFactory().createServerConnection()
        );
    }

    public RedisTreeCell<String> createRedisTreeCell() {
        return new RedisTreeCell<String>(this.createServerController());
    }
}
