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
import java.util.Objects;

public class Value<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 2L;

    private final Class<T> type;

    private final T value;

    public Value(final Class<T> type, final T value) {
        Objects.requireNonNull(type, "type must not be null");
        Objects.requireNonNull(value, "value must not be null");

        this.type = type;
        this.value = value;
    }

    public Class<T> type() {

        return type;
    }

    public T value() {

        return value;
    }

    public <C> C value(final Class<C> type) {
        return type.cast(this.value);
    }
}
