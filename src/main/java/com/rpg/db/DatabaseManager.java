package com.rpg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:rpg.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void init() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS personagem ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT UNIQUE NOT NULL,"
                + "classe TEXT NOT NULL,"
                + "ataque INTEGER NOT NULL,"
                + "defesa INTEGER NOT NULL,"
                + "vida INTEGER NOT NULL,"
                + "vida_maxima INTEGER NOT NULL,"
                + "xp INTEGER NOT NULL,"
                + "nivel INTEGER NOT NULL"
                + ");";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
