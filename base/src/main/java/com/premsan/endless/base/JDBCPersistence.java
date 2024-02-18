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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCPersistence implements Persistence {

    private static final String SQL = "INSERT INTO CONCEPTS value(?, ?, ?)";

    private final ObjectMapper objectMapper = new ObjectMapperFactory().get();

    private final Connection connection;

    public JDBCPersistence(final Connection connection) {

        this.connection = connection;
    }

    @Override
    public void persist(final Serial serial) throws PersistenceException {

        PreparedStatement preparedStatement = null;
        PersistenceException persistenceException = null;

        try {

            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(3, objectMapper.writeValueAsString(serial));
            preparedStatement.execute();

        } catch (SQLException | JsonProcessingException e) {

            persistenceException = new PersistenceException(e);

        } finally {

            try {

                if (preparedStatement != null) {

                    preparedStatement.close();
                }

            } catch (SQLException e) {

                persistenceException = new PersistenceException(e);
            }
        }

        if (persistenceException != null) {

            throw persistenceException;
        }
    }
}
