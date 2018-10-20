package com.ehsanmx.novaredis.model;

public class Database {

    private String index;

    private long keys;

    public Database(String index, long keys) {
        this.index = index;
        this.keys = keys;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public long getKeys() {
        return keys;
    }

    public void setKeys(long keys) {
        this.keys = keys;
    }
}
