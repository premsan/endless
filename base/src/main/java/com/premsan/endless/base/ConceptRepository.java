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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ConceptRepository {

    private final Map<String, Concept> conceptMap = new HashMap<>();

    private Persistence persistence;

    public ConceptRepository(final Persistence persistence) {

        this.persistence = persistence;
    }

    public synchronized Concept add(final Concept.Builder conceptBuilder) {

        final Concept concept =
                conceptBuilder.id(UUID.randomUUID()).ts(System.currentTimeMillis()).build();

        this.conceptMap.put(concept.getName(), concept);

        if (persistence != null) {

            persistence.write(new SerialConcept(concept));
        }

        return concept;
    }

    public Concept find(final String name) {

        return this.conceptMap.get(name);
    }

    public void load(final SerialConcept serialConcept) {

        final Concept concept =
                new Concept.Builder()
                        .id(serialConcept.getId())
                        .ts(serialConcept.getTs())
                        .name(serialConcept.getName())
                        .build();

        this.conceptMap.put(concept.getName(), concept);
    }
}
