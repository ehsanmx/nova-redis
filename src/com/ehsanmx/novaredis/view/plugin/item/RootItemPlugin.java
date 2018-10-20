package com.ehsanmx.novaredis.view.plugin.item;

import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class RootItemPlugin implements ItemPlugin {

    private ContextMenu serverMenu = null;

    @Override
    public void updateItem(RedisTreeCell redisTreeCell, String item) {
            this.createServerMenu(redisTreeCell);
            if (item.equals("Servers")) {
                redisTreeCell.setText(item);
                redisTreeCell.setContextMenu(this.serverMenu);
            }
    }

    private void createServerMenu(RedisTreeCell redisTreeCell) {
        if (this.serverMenu != null) {
            return;
        }

        this.serverMenu = new ContextMenu();
        MenuItem addServerItem = new MenuItem("Add new server ...");
        serverMenu.getItems().add(addServerItem);
        addServerItem.setOnAction((event) -> {
            redisTreeCell.showServerDialog();
        });

        System.out.println(serverMenu);
    }
}
