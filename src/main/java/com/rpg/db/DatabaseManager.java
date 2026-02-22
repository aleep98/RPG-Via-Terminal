package com.rpg.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    private static final Map<String, String> DOTENV = loadDotEnv();
    private static final String DB_HOST = getConfig("RPG_DB_HOST", "localhost");
    private static final String DB_PORT = getConfig("RPG_DB_PORT", "3306");
    private static final String DB_NAME = getConfig("RPG_DB_NAME", "rpg");
    private static final String DB_USER = getConfig("RPG_DB_USER", "root");
    private static final String DB_PASSWORD = getConfig("RPG_DB_PASSWORD", "");
    private static final String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    public void init() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS personagem ("
                + "id INT NOT NULL AUTO_INCREMENT,"
                + "nome VARCHAR(100) NOT NULL,"
                + "classe VARCHAR(30) NOT NULL,"
                + "ataque INT NOT NULL,"
                + "defesa INT NOT NULL,"
                + "vida INT NOT NULL,"
                + "vida_maxima INT NOT NULL,"
                + "xp INT NOT NULL,"
                + "nivel INT NOT NULL,"
                + "PRIMARY KEY (id),"
                + "UNIQUE KEY uk_personagem_nome (nome)"
                + ") ENGINE=InnoDB;";

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static String getConfig(String key, String defaultValue) {
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue.trim();
        }

        String dotEnvValue = DOTENV.get(key);
        if (dotEnvValue != null && !dotEnvValue.trim().isEmpty()) {
            return dotEnvValue.trim();
        }

        return defaultValue;
    }

    private static Map<String, String> loadDotEnv() {
        Map<String, String> values = new HashMap<>();
        Path envPath = Paths.get(".env");

        if (!Files.exists(envPath)) {
            return values;
        }

        try {
            List<String> lines = Files.readAllLines(envPath, StandardCharsets.UTF_8);
            for (String rawLine : lines) {
                String line = rawLine.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                int idx = line.indexOf('=');
                if (idx <= 0) {
                    continue;
                }

                String key = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();

                if ((value.startsWith("\"") && value.endsWith("\""))
                        || (value.startsWith("'") && value.endsWith("'"))) {
                    value = value.substring(1, value.length() - 1);
                }

                values.put(key, value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo .env", e);
        }

        return values;
    }
}
