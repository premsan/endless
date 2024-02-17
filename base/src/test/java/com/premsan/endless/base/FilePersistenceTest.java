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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FilePersistenceTest {

    private final File file = new File("nodes-" + System.currentTimeMillis());

    @Test
    void persist() throws IOException, ClassNotFoundException {

        final UUID parentId = UUID.randomUUID();
        final Concept userConcept = new Concept("user");
        final Concept bookingConcept = new Concept("booking");

        final Node parentNode = new Node(parentId, userConcept, new HashMap<>());
        final HashMap<Node, String> parents = new HashMap<>();
        parents.put(parentNode, "owner");

        new Node(UUID.randomUUID(), bookingConcept, parents);

        new Node(UUID.randomUUID(), bookingConcept, parents);

        final FilePersistence filePersistence = new FilePersistence(file);

        filePersistence.persist(parentNode);
        filePersistence.close();

        final FileBootstrap fileBootstrap = new FileBootstrap(file);
        final Context context = fileBootstrap.bootstrap();

        Assertions.assertEquals(parentId, context.nodeRepository().find(parentId).getId());
        Assertions.assertEquals(2, context.nodeRepository().find(parentId).getChildren().size());

        file.deleteOnExit();
    }
}
