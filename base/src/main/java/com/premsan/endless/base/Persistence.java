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
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Persistence implements Runnable {

    protected final ObjectMapper objectMapper = new ObjectMapperFactory().get();

    protected final ConcurrentLinkedQueue<Serial> serials = new ConcurrentLinkedQueue<>();

    public void write(final Serial serial) {

        serials.offer(serial);
    }

    protected abstract void persist(Serial serial) throws PersistenceException;

    public static class PersistenceException extends Exception {

        public PersistenceException(Throwable cause) {

            super(cause);
        }
    }

    public void run() {

        while (true) {

            try {

                if (!serials.isEmpty()) {

                    persist(serials.poll());
                }

            } catch (PersistenceException e) {

                throw new RuntimeException(e);
            }
        }
    }
}
