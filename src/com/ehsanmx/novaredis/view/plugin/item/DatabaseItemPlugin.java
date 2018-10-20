package com.ehsanmx.novaredis.view.plugin.item;

import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DatabaseItemPlugin implements ItemPlugin{

    @Override
    public void updateItem(RedisTreeCell redisTreeCell, String item) {
        if (item.startsWith("db_")) {
            redisTreeCell.setText(item.replaceAll("db_", ""));
            redisTreeCell.setGraphic(getDatabaseIcon());
            }
    }

    private ImageView getDatabaseIcon() {
        return new ImageView(
                new Image(getClass().getResourceAsStream("/com/ehsanmx/novaredis/resources/icon/database.png"))
        );
    }
}
