/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.it.map.b.adventure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class Database {

    private static Connection connection;

    public static void createConnection() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./src/main/resources/db/TesoroDegliDei", "tesoro", "dei");
            System.out.println("Connessione al database avvenuta!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    //metodo per la creazione di tabelle nel database
    public void createTable() {
        try {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    String player = "CREATE TABLE IF NOT EXISTS player (id INT PRIMARY KEY, current_room VARCHAR(255), remaining_time INT)";
                    String game_state = "CREATE TABLE IF NOT EXISTS game_state (id INT PRIMARY KEY, object_name VARCHAR(255), used BOOLEAN)";
                    statement.executeUpdate(player);
                    statement.executeUpdate(game_state);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //metodo che permette il salvataggio nel database della stanza corrente, del giocatore e
    //del nome della stanza in cui si trova il giocatore
    public static void savePlayerRoom(int roomId, String currentRoom, int remaining_time) {
        try (PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM PLAYER")) {
            deleteStatement.executeUpdate();
        } catch (SQLException rollbackEx) {
            System.err.println("Error during rollback: " + rollbackEx.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO PLAYER (id, current_room, remaining_time) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, roomId);
            preparedStatement.setString(2, currentRoom);
            preparedStatement.setInt(3, remaining_time);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //metodo che consente di memorizzare gli oggetti utilizzati o raccolti dal giocatore nel database
    public static void saveObject(int obejctId, String object_name, boolean used) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("MERGE INTO game_state (id, object_name, used) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, obejctId);
            preparedStatement.setString(2, object_name);
            preparedStatement.setBoolean(3, used);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //metodo che permette di prelevare gli oggetti raccolti dal giocatore dal database
    public static boolean loadTakenObjects(int objectId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM game_state WHERE id = ?")) {
            statement.setInt(1, objectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //metodo che permette di prelevare gli oggetti utilizzati dal giocatore dal database
    public static boolean loadUsedObjects(int objectId) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM game_state WHERE id = ? AND used = true")) {
            statement.setInt(1, objectId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //metodo che consente di selezionare la stanza corrente del giocatore memorizzata nel database
    public static int loadPlayerRoom() {
        int roomId = -1;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT id FROM PLAYER LIMIT 1")) {
            if (resultSet.next()) {
                roomId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return roomId;
    }

    //metodo che consente di ottenere il tempo rimanenete, in secondi, dal database
    public static int getRemainingTime() {
        final String GET_REMAINING_TIME_QUERY = "SELECT remaining_time FROM PLAYER";

        try (PreparedStatement statement = connection.prepareStatement(GET_REMAINING_TIME_QUERY); ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("remaining_time");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //metodo che consente di eliminare i dati della tabella 'player' dal database    
    //metodo che consente di eliminare i dati della tabella 'player' dal database
    public static void deletePlayerData() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM PLAYER");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //metodo che consente di eliminare i dati della tabella 'game_state' dal database
    public static void deleteUsedObject() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM game_state");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //metodo che verifica se nella tabella 'player' sono presenti righe, quindi i dati sono stati salvati
    public boolean checkSavedGame() {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM player")) {
            if (resultSet.next()) {
                int rowCount = resultSet.getInt("count");
                return rowCount > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}
