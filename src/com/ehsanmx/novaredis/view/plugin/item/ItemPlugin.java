package com.ehsanmx.novaredis.view.plugin.item;

import com.ehsanmx.novaredis.view.ui.RedisTreeCell;

public interface ItemPlugin {

    public void init(RedisTreeCell redisTreeCell);

    public void updateItem(RedisTreeCell redisTreeCell, String item);
}
