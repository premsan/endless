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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConceptRepository {

    private final Map<String, Concept> conceptMap = new HashMap<>();

    private SerialChannel serialChannel;

    public ConceptRepository() {}

    public ConceptRepository(final SerialChannel serialChannel) {

        this.serialChannel = serialChannel;
    }

    public synchronized void save(final Concept concept) {

        this.conceptMap.put(concept.getName(), concept);

        if (serialChannel != null) {

            try {

                serialChannel.write(concept);

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }

    public Concept find(final String name) {

        return this.conceptMap.get(name);
    }
}
