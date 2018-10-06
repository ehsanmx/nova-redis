package com.ehsanmx.novaredis.view.controller;

import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import com.ehsanmx.novaredis.core.server.ServerChannelImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label footer;

    @FXML
    public TreeView treeView;

    private ServerChannelImpl reader;

    private RedisTreeCell<String> redisTreeCell;

    public MainController(ServerChannelImpl reader, RedisTreeCell<String> redisTreeCell) {
        this.reader = reader;
        this.redisTreeCell = redisTreeCell;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> rootItem = new TreeItem<String> ("Servers");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("localhost" + i);
            item.setGraphic(new ImageView(
                    new Image(getClass().getResourceAsStream("/com/ehsanmx/novaredis/resources/icon/server.png"))
            ));
            rootItem.getChildren().add(item);
        }

//        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            TreeItem<String> treeItem = (TreeItem<String>) observable.getValue();
//            TreeItem<String> newItem = new TreeItem<String> ("db0");
//            newItem.setGraphic(new ImageView(
//                    new Image(getClass().getResourceAsStream("/com/ehsanmx/novaredis/resources/icon/database.png"))
//            ));
//            treeItem.getChildren().add(newItem);
//            System.out.println(treeItem.getValue());
//        });

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
}
