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

import java.util.Objects;

public final class Property<T> {

    private final String name;

    private Value<T> value;

    public Property(final String name, final Value<T> value) {

        this.name = Objects.requireNonNull(name, "name must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

    public String name() {

        return this.name;
    }

    public Value<T> value() {

        return this.value;
    }

    public Property<T> value(final Value<T> value) {

        this.value = Objects.requireNonNull(value, "value must not be null");
        return this;
    }
}
