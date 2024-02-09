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

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValueTest {

    @Test
    public void testValueConstructor() {

        final Value<LocalDate> dateValue =
                new Value<>(LocalDate.class, LocalDate.parse("2024-05-02"));

        Assertions.assertEquals(LocalDate.class, dateValue.type());
        Assertions.assertEquals(LocalDate.parse("2024-05-02"), dateValue.value());
    }
}
