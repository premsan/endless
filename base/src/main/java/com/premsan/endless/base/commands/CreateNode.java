package com.premsan.endless.base.commands;

import com.premsan.endless.base.Property;
import com.premsan.endless.base.Write;

import java.util.Set;
import java.util.UUID;

public class CreateNode implements Write {

    private final UUID conceptId;

    private final Set<Property<?>> properties;

    public CreateNode(final UUID conceptId, final Set<Property<?>> properties) {
        this.conceptId = conceptId;
        this.properties = properties;
    }

    public UUID getConceptId() {
        return conceptId;
    }

    public Set<Property<?>> getProperties() {
        return properties;
    }
}
