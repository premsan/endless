package com.premsan.endless.base.commands;

import com.premsan.endless.base.Write;

public class CreateConcept implements Write {

    private final String name;

    public CreateConcept(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
