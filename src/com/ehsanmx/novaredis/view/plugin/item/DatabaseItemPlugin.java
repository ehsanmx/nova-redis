package com.ehsanmx.novaredis.view.plugin.item;

import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DatabaseItemPlugin implements ItemPlugin {

    @Override
    public void init(RedisTreeCell redisTreeCell) {
    }

    @Override
    public void updateItem(RedisTreeCell redisTreeCell, String item) {
        if (item.startsWith("db_")) {
            redisTreeCell.setText(item.replaceAll("db_", ""));
            redisTreeCell.setGraphic(getDatabaseIcon());
            redisTreeCell.setOnMouseClicked((event -> {
                if (event.getClickCount() == 2) {
                    System.out.println("DataBase");
                }
            }));
        }
    }

    private ImageView getDatabaseIcon() {
        return new ImageView(
                new Image(getClass().getResourceAsStream("/com/ehsanmx/novaredis/resources/icon/database.png"))
        );
    }
}
