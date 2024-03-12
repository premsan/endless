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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public abstract class PostgresJDBCPersistence extends Persistence {

    private final Connection connection;

    public PostgresJDBCPersistence() {

        try {

            final String SQL =
                    new BufferedReader(
                                    new InputStreamReader(
                                            Objects.requireNonNull(
                                                    PostgresJDBCPersistence.class
                                                            .getResourceAsStream(
                                                                    "/CREATE_TABLES.sql"))))
                            .lines()
                            .collect(Collectors.joining("\n"));

            this.connection = getConnection();
            Statement statement = this.connection.createStatement();

            statement.execute(SQL);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {

        String url = "jdbc:postgresql://localhost/test";
        Properties props = new Properties();

        return DriverManager.getConnection(url, props);
    }

    @Override
    public void persist(Serial serial) throws PersistenceException {

        PreparedStatement preparedStatement = null;
        PersistenceException persistenceException = null;

        try {

            preparedStatement = connection.prepareStatement("SQL");

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
