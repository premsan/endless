/*
 * Copyright (c) PREMSAN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.premsan.endless.base;

import java.io.Serializable;
import java.util.UUID;

public class SerialProperty implements Serial {

    private final UUID nodeId;

    private final String name;

    private final Class<?> dataType;

    private final Serializable value;

    public SerialProperty(final Property<?> property) {

        this.nodeId = property.getNode().getId();
        this.name = property.getName();
        this.dataType = property.getDataType();
        this.value = property.getValue();
    }

    public UUID getNodeId() {

        return this.nodeId;
    }

    public String getName() {

        return this.name;
    }

    public Class<?> getDataType() {

        return this.dataType;
    }

    public Serializable getValue() {

        return this.value;
    }
}
