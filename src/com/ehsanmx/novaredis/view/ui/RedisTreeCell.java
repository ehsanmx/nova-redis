package com.ehsanmx.novaredis.view.ui;

import com.ehsanmx.novaredis.view.controller.ServerController;
import com.ehsanmx.novaredis.view.plugin.item.DatabaseItemPlugin;
import com.ehsanmx.novaredis.view.plugin.item.ItemPlugin;
import com.ehsanmx.novaredis.view.plugin.item.RootItemPlugin;
import com.ehsanmx.novaredis.view.plugin.item.ServerItemPlugin;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RedisTreeCell<T> extends TreeCell<String> implements Cloneable{

    private Initializable serverController;
    private List<ItemPlugin> itemPlugins = new ArrayList<ItemPlugin>();

    public RedisTreeCell(Initializable serverController) {
        this.serverController = serverController;
        this.itemPlugins.add(new RootItemPlugin());
        this.itemPlugins.add(new ServerItemPlugin());
        this.itemPlugins.add(new DatabaseItemPlugin());
        this.init();
    }

    private void init() {
        for(ItemPlugin itemPlugin: this.itemPlugins) {
            itemPlugin.init(this);
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(item == null) {
            setText("");
            setGraphic(null);

            return;
        }

        for(ItemPlugin itemPlugin: this.itemPlugins) {
            itemPlugin.updateItem(this, item);
        }
    }

    public void showServerDialog() {
        System.out.println("New server:" + getItem());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ehsanmx/novaredis/resources/view/serverDialog.fxml"));
        ServerController serverController = (ServerController) this.serverController;
        FXMLLoader mainLoader = (FXMLLoader) this.getScene().getUserData();
        serverController.init(mainLoader.getController());

        loader.setController(this.serverController);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 200);
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (Throwable t) {
            System.err.println(t);
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new RedisTreeCell(this.serverController);
    }
}
