package com.ehsanmx.novaredis.view.ui;

import com.ehsanmx.novaredis.view.controller.ServerController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RedisTreeCell<T> extends TreeCell<String> implements Cloneable{

    private ContextMenu serverMenu = null;
    private ContextMenu instanceMenu = null;
    private Initializable serverController;

    public RedisTreeCell(Initializable serverController) {
        this.serverController = serverController;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText("");
            setGraphic(null);
        } else {
            setText(item);
            this.createServerMenu();
            this.createInstanceMenu();
            if (item.equals("Servers")) {
                setContextMenu(this.serverMenu);
            } else {
                setContextMenu(this.instanceMenu);
            }
        }
    }

    private void createServerMenu() {
        if (this.serverMenu != null) {
            return;
        }

        this.serverMenu = new ContextMenu();
        MenuItem addServerItem = new MenuItem("Add new server ...");
        serverMenu.getItems().add(addServerItem);
        addServerItem.setOnAction((event) -> {
            showServerDialog();
        });

        System.out.println(serverMenu);
    }

    private void showServerDialog() {
        System.out.println("New server:" + getItem());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ehsanmx/novaredis/resources/view/serverDialog.fxml"));
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

    /**
     * TODO refactor this
     */
    private void createInstanceMenu() {
        if (this.instanceMenu != null) {
            return;
        }

        this.instanceMenu = new ContextMenu();
        MenuItem editInstanceItem = new MenuItem("Edit new server ...");
        this.instanceMenu.getItems().add(editInstanceItem);
        editInstanceItem.setOnAction((event) -> {
            System.out.println("Edit server:" + getItem());
        });

        System.out.println(this.instanceMenu);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new RedisTreeCell(this.serverController);
    }
}
