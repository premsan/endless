package com.premsan.endless.base;

import java.util.UUID;

public class ReaderState implements Meta {

    private UUID id;
    private String lastId;

    public ReaderState(final UUID id, final String lastId) {
        this.id = id;
        this.lastId = lastId;
    }

    public UUID getId() {
        return id;
    }

    public String getLastId() {
        return lastId;
    }
}
