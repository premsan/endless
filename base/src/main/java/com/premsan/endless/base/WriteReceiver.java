package com.premsan.endless.base;

import java.util.UUID;

public class WriteReceiver {

    private final Persistence persistence;

    public WriteReceiver(final Persistence persistence) {

        this.persistence = persistence;
    }

    public WriteId write(final Write write) {

        persistence.saveWrite(write);

        return new WriteId(UUID.randomUUID());
    }
}
