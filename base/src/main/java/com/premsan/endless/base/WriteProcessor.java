package com.premsan.endless.base;

public class WriteProcessor {

    private final Persistence persistence;

    public WriteProcessor(final Persistence persistence) {

        this.persistence = persistence;
    }

    public static class State {

        private String lastId;
    }
}
