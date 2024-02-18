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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileBootstrap {

    private final ObjectMapper objectMapper = new ObjectMapperFactory().get();

    private final Context context = new Context();
    private final File bootstrapFile;

    public FileBootstrap(final File bootstrapFile) {

        this.bootstrapFile = bootstrapFile;
    }

    public Context bootstrap() throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader((bootstrapFile)))) {

            while (true) {

                final String line = br.readLine();

                if (line == null) {

                    break;
                }
                context.nodeRepository().load(objectMapper.readValue(line, SerialNode.class));
            }
        }
        return context;
    }
}
