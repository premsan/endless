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

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class FileBootstrap {

    private final Context context;
    private final File bootstrapFile;

    public FileBootstrap(final File bootstrapFile) {

        this.context = new Context(new NodeStore());

        this.bootstrapFile = bootstrapFile;
    }

    public Context bootstrap() throws IOException, ClassNotFoundException {

        ClassNotFoundException classNotFoundException = null;

        try (final FileInputStream bootstrapFileInputStream = new FileInputStream(bootstrapFile);
                final ObjectInputStream objectInputStream =
                        new ObjectInputStream(bootstrapFileInputStream)) {

            while (true) {

                try {

                    context.nodeStore().load((Node) objectInputStream.readObject());

                } catch (EOFException eofException) {

                    break;
                } catch (ClassNotFoundException classNotFoundException1) {

                    classNotFoundException = classNotFoundException1;

                    break;
                }
            }
        }

        if (classNotFoundException != null) {

            throw classNotFoundException;
        }

        return context;
    }
}
