package com.premsan.endless.base.commands;

import com.premsan.endless.base.PropertySchema;
import com.premsan.endless.base.Write;

import java.io.Serializable;
import java.util.UUID;

public class CreateProperty<T extends Serializable> implements Write {

    private final UUID nodeId;
    private final PropertySchema schema;
    private final T value;

    public CreateProperty(final UUID nodeId, final PropertySchema schema, final T value) {

        this.nodeId = nodeId;
        this.schema = schema;
        this.value = value;
    }

    public UUID getNodeId() {

        return nodeId;
    }

    public PropertySchema getSchema() {

        return schema;
    }

    public T getValue() {

        return value;
    }
}
