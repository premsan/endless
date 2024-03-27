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

public class PropertySchema {

    private final DataType dataType;
    private final String name;
    private final int minCardinality;
    private final int maxCardinality;

    public PropertySchema(
            final DataType dataType,
            final String name,
            final int minCardinality,
            final int maxCardinality) {

        this.dataType = dataType;
        this.name = name;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
    }

    public DataType getPrimitive() {

        return this.dataType;
    }

    public String getName() {

        return this.name;
    }

    public int getMinCardinality() {

        return this.minCardinality;
    }

    public int getMaxCardinality() {

        return this.maxCardinality;
    }

    public enum DataType {
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        NODE_REF
    }
}
