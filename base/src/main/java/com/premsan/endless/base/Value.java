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

public final class Value<T> {

    private final Class<T> type;

    private final T value;

    public Value(final Class<T> type, final T value) {

        this.type = Objects.requireNonNull(type, "type must not be null");
        this.value = Objects.requireNonNull(value, "value must not be null");
    }

    public Class<T> type() {
        return type;
    }

    public T value() {
        return value;
    }
}
