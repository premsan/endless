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

public class PropertySchema implements Serializable {

    private static final long serialVersionUID = 3L;

    private final long creationTimeMillis;
    private final Concept concept;
    private final UUID id;

    private final String name;
    private final PropertyType propertyType;
    private final int minCardinality;
    private final int maxCardinality;

    public PropertySchema(
            final long creationTimeMillis,
            final Concept concept,
            final UUID id,
            final String name,
            final PropertyType propertyType,
            final int minCardinality,
            final int maxCardinality) {

        this.creationTimeMillis = creationTimeMillis;
        this.concept = concept;
        this.id = id;

        this.name = name;
        this.propertyType = propertyType;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
    }

    public long getCreationTimeMillis() {
        return creationTimeMillis;
    }

    public Concept getConcept() {
        return concept;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public int getMinCardinality() {
        return minCardinality;
    }

    public int getMaxCardinality() {
        return maxCardinality;
    }
}
