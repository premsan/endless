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

public final class Context {

    private final ConceptRepository conceptRepository;

    private final NodeRepository nodeRepository;

    public Context() {

        this.conceptRepository = new ConceptRepository();
        this.nodeRepository = new NodeRepository();
    }

    public Context(final ConceptRepository conceptRepository, final NodeRepository nodeRepository) {

        this.conceptRepository = conceptRepository;
        this.nodeRepository = nodeRepository;
    }

    public ConceptRepository conceptRepository() {

        return this.conceptRepository;
    }

    public NodeRepository nodeRepository() {

        return this.nodeRepository;
    }
}
