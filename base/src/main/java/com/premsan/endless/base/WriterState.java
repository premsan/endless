package com.premsan.endless.base;

import java.util.UUID;

public class WriterState implements Meta {

    private final UUID id;
    private final String lastId;

    public WriterState(final UUID id, final String lastId) {
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
