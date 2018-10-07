package com.ehsanmx.novaredis.view.controller;

import com.ehsanmx.novaredis.core.server.ServerChannel;
import com.ehsanmx.novaredis.core.server.ServerConnection;
import com.ehsanmx.novaredis.model.Server;
import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label footer;

    @FXML
    public TreeView treeView;

    private ServerConnection serverConnection;

    private ServerChannel serverChannel;

    private RedisTreeCell<String> redisTreeCell;

    public MainController(ServerConnection serverConnection, ServerChannel serverChannel, RedisTreeCell<String> redisTreeCell) {
        this.serverConnection = serverConnection;
        this.serverChannel = serverChannel;
        this.redisTreeCell = redisTreeCell;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            TreeItem<String> treeItem = (TreeItem<String>) observable.getValue();
//            TreeItem<String> newItem = new TreeItem<String> ("db_test");
//            treeItem.getChildren().add(newItem);
//        });

        this.initTreeView();
    }

    public void updateLabel(String s) {
        this.footer.setText(s);
    }

    public void initTreeView() {
        TreeItem<String> rootItem = new TreeItem<String>("Servers");
        rootItem.setExpanded(true);

        if (rootItem.getChildren() != null) {
            rootItem.getChildren().removeAll();
        }
        Map<String, Server> servers = this.serverConnection.findServers();
        servers.forEach((key, server) -> {
            TreeItem<String> item = new TreeItem<String>("srv_" + key);
            rootItem.getChildren().add(item);
        });

        treeView.setCellFactory(params -> {
            try {
                return this.redisTreeCell.clone();
            } catch (CloneNotSupportedException e) {
                System.err.println(e.getMessage());

                return null;
            }
        });

        treeView.setRoot(rootItem);
    }

    public void updateTreeView(Server server) {
        TreeItem<String> item = new TreeItem<String>("srv_" + server.getName());
        treeView.getRoot().getChildren().add(item);
    }
}
