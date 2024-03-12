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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FilePersistence extends Persistence {

    private final ObjectMapper objectMapper = new ObjectMapperFactory().get();

    private final BufferedWriter writer;

    public FilePersistence(final File file) throws IOException {

        this.writer = new BufferedWriter(new FileWriter(file, true));
    }

    public void close() throws IOException {

        this.writer.close();
    }

    @Override
    public void persist(Serial poll) throws PersistenceException {

        try {

            this.writer.write(objectMapper.writeValueAsString(serials.peek()));
            this.writer.newLine();

        } catch (IOException e) {

            throw new PersistenceException(e);
        }
    }
}
