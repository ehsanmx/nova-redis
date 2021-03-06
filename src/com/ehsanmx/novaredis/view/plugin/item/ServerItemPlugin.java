package com.ehsanmx.novaredis.view.plugin.item;

import com.ehsanmx.novaredis.core.redis.RedisClient;
import com.ehsanmx.novaredis.core.server.ServerRepository;
import com.ehsanmx.novaredis.kernel.AbstractPlugin;
import com.ehsanmx.novaredis.model.Database;
import com.ehsanmx.novaredis.model.Server;
import com.ehsanmx.novaredis.view.ui.RedisTreeCell;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Map;

public class ServerItemPlugin extends AbstractPlugin implements ItemPlugin {

    private ContextMenu instanceMenu = null;
    private RedisClient redisClient = null;
    private ServerRepository serverRepository = null;

    public ServerItemPlugin() {
        this.redisClient = this.getCoreFactory().createRedisClient();
        this.serverRepository = this.getCoreFactory().createServerRepository();
    }

    @Override
    public void init(RedisTreeCell redisTreeCell) {
        createInstanceMenu(redisTreeCell);
    }

    @Override
    public void updateItem(RedisTreeCell redisTreeCell, String item) {
        if (item.startsWith("srv_")) {
            String serverName = item.replaceAll("srv_", "");
            redisTreeCell.setText(serverName);
            redisTreeCell.setGraphic(this.getServerIcon());
            redisTreeCell.setContextMenu(this.instanceMenu);
            redisTreeCell.setOnMouseClicked((event -> {
                if (event.getClickCount() == 2) {
                    System.out.println("m-s");
                    this.createDatabaseItems(redisTreeCell, serverName);
                }
            }));
        }
    }

    private ImageView getServerIcon() {
        return new ImageView(
                new Image(getClass().getResourceAsStream("/com/ehsanmx/novaredis/resources/icon/server.png"))
        );
    }

    private void createInstanceMenu(RedisTreeCell redisTreeCell) {
        if (this.instanceMenu != null) {
            return;
        }

        this.instanceMenu = new ContextMenu();
        MenuItem editInstanceItem = new MenuItem("Edit ...");
        this.instanceMenu.getItems().add(editInstanceItem);
        editInstanceItem.setOnAction((event) -> {
            System.out.println("Edit server:" + redisTreeCell.getItem());
        });
    }

    private void createDatabaseItems(RedisTreeCell redisTreeCell, String serverName) {
        if (redisTreeCell.getTreeItem().getChildren().size() > 0) {
            return;
        }
        Server server = this.serverRepository.load(serverName);
        Map<String, Database> databaseMap = this.redisClient.build(server).findServerDatabases();
        databaseMap.forEach((name, database) -> {
            String itemTitle = String.format("db_%s",name);
            if (database.getKeys() != 0) {
                itemTitle = String.format("db_%s(%d)",name, database.getKeys());
            }

            TreeItem<String> item = new TreeItem<String>(itemTitle);
            redisTreeCell.getTreeItem().getChildren().add(item);
        });
    }
}
