package com.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rpg.db.DatabaseManager;
import com.model.Personagem;
import com.model.TipoClasse;

public class PersonagemRepository {

    private final DatabaseManager databaseManager;

    public PersonagemRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public Personagem buscarPorNome(String nome) {
        String sql = "SELECT nome, classe, ataque, defesa, vida, vida_maxima, xp, nivel FROM personagem WHERE nome = ?";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return null;
                }

                return new Personagem(
                        resultSet.getString("nome"),
                        TipoClasse.valueOf(resultSet.getString("classe")),
                        resultSet.getInt("ataque"),
                        resultSet.getInt("defesa"),
                        resultSet.getInt("vida"),
                        resultSet.getInt("vida_maxima"),
                        resultSet.getInt("xp"),
                        resultSet.getInt("nivel"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar personagem no banco.", e);
        }
    }

    public void salvarOuAtualizar(Personagem personagem) {
        String sql = "INSERT INTO personagem (nome, classe, ataque, defesa, vida, vida_maxima, xp, nivel) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "classe = VALUES(classe), "
                + "ataque = VALUES(ataque), "
                + "defesa = VALUES(defesa), "
                + "vida = VALUES(vida), "
                + "vida_maxima = VALUES(vida_maxima), "
                + "xp = VALUES(xp), "
                + "nivel = VALUES(nivel)";

        try (Connection connection = databaseManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, personagem.getNome());
            statement.setString(2, personagem.getClasse().name());
            statement.setInt(3, personagem.getAtaque());
            statement.setInt(4, personagem.getDefesa());
            statement.setInt(5, personagem.getVida());
            statement.setInt(6, personagem.getVidaMaxima());
            statement.setInt(7, personagem.getXp());
            statement.setInt(8, personagem.getNivel());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar personagem no banco.", e);
        }
    }
}
