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

import java.util.UUID;

public class SerialConcept implements Serial {

    private final UUID id;

    private final long ts;

    private final String name;

    public SerialConcept(final Concept concept) {

        this.id = concept.getId();

        this.ts = concept.getTs();

        this.name = concept.getName();
    }

    @Override
    public UUID getId() {

        return this.id;
    }

    @Override
    public long getTs() {

        return this.ts;
    }

    public String getName() {

        return this.name;
    }
}
