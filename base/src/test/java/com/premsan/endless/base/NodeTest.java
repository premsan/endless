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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class NodeTest {

    public Context context = new Context(new ConceptRepository(), new NodeRepository());

    @Test
    public void multiThreadNodeCreation() {

        context.conceptRepository().save(new Concept("booking"));

        final List<Thread> nodeThreads = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {

            nodeThreads.add(
                    new Thread(
                            () -> {
                                UUID uuid = UUID.randomUUID();

                                Node.Builder builder =
                                        new Node.Builder()
                                                .id(uuid)
                                                .concept(
                                                        context.conceptRepository()
                                                                .find("booking"));

                                context.nodeRepository().save(builder.build());
                            }));
        }

        for (final Thread nodeThread : nodeThreads) {
            nodeThread.start();
        }
    }
}