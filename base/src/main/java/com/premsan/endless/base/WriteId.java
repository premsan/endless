package com.premsan.endless.base;

import java.io.Serializable;
import java.util.UUID;

public class WriteId implements Serializable {

    private UUID id;

    public WriteId(final UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
