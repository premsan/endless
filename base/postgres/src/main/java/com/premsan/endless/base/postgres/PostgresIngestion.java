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
package com.premsan.endless.base.postgres;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.premsan.endless.base.Context;
import com.premsan.endless.base.Node;
import com.premsan.endless.base.ObjectMapperFactory;
import com.premsan.endless.base.SerialNode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class PostgresIngestion implements Runnable {

    protected final ObjectMapper objectMapper = new ObjectMapperFactory().get();

    private long offset = 0;

    private final Context context;
    private final Connection connection;

    public PostgresIngestion(final Context context) throws SQLException {

        this.context = context;
        this.connection = getConnection();


        try {

            final String SQL =
                    new BufferedReader(
                            new InputStreamReader(
                                    Objects.requireNonNull(
                                            getClass().getResourceAsStream(
                                                    "/CREATE_TABLES.sql"))))
                            .lines()
                            .collect(Collectors.joining("\n"));

            Statement statement = this.connection.createStatement();

            statement.execute(SQL);

            new Thread(this).start();

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
    public void run() {

        PreparedStatement preparedStatement = null;

        while (true) {

            try {

                preparedStatement =
                        connection.prepareStatement(
                                "SELECT val FROM NODES WHERE id > (?) AND LIMIT 100");
                preparedStatement.setLong(1, offset);

                final ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    final String nodeStr = resultSet.getString("val");

                    final Node node = objectMapper.readValue(nodeStr, Node.class);

                    context.nodeRepository().load(new SerialNode(node));

                    offset = resultSet.getLong("id");
                }

                Thread.sleep(1000);

            } catch (final InterruptedException ignored) {


            } catch (final JsonProcessingException | SQLException e) {

                throw new RuntimeException(e);
            }
        }
    }
}
