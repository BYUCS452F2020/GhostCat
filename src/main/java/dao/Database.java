package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for opening and closing connections to our database. Also supports a method to create 4 tables
 * in the database, and a method for erasing those 4 tables
 */
public class Database {
    private Connection conn;

    static {
        try {
            //This is how we set up the driver for our database
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Whenever we want to make a change to our database we will have to open a connection and use
     * Statements created by that connection to initiate transactions
     * @return returns the connection that has been established with the database
     */
    public Connection openConnection() throws DataAccessException {
        try {
            //The Structure for this Connection is driver:language:path
            //The pathing assumes you start in the root of your project unless given a non-relative path
            final String CONNECTION_URL = "jdbc:sqlite:ghostcat.sqlite";

            // Open a database connection to the file given in the path
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return conn;
    }

    /**
     * This will end the transaction and allow us to either commit our changes to the database or rollback any
     * changes that were made before we encountered a potential error.
     * @param commit: a boolean that tells us if we should commit our changes or not
     */
    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                //This will commit the changes to the database
                conn.commit();
            } else {
                //If we find out something went wrong, pass a false into closeConnection and this
                //will rollback any changes we made during this connection
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    /**
     * Creates a table in our database for each of the five tables: ImageBinaries, ImageMetadata, Cameras, MachineLearningMetadata, and Animals
     * @throws DataAccessException if a SQL error occurred while creating the tables
     */
    public void createTables() throws DataAccessException {

        try (Statement stmt = conn.createStatement()) {

            String sqlImageBinary = "CREATE TABLE IF NOT EXISTS ImageBinaries " +
                    "(" +
                    "image_id text not null unique, " +
                    "primary key (image_id)" +
                    "image_binary text not null unique" +
                    ")";

            stmt.executeUpdate(sqlImageBinary);

            String sqlImageMetadata = "CREATE TABLE IF NOT EXISTS ImageMetadata " +
                    "(" +
                    "image_id text not null, " +
                    "primary key (image_id), " +
                    "foreign key (image_id) references ImageBinaries(image_id), " +
                    "time integer not null, " +
                    "date integer not null, " +
                    "camera_id text not null, " +
                    "foreign key (camera_id) references Cameras(camera_id)" +
                    ")";

            stmt.executeUpdate(sqlImageMetadata);

            String sqlCameras = "CREATE TABLE IF NOT EXISTS Cameras " +
                    "(" +
                    "camera_id text not null unique, " +
                    "primary key (camera_id), " +
                    "camera_type text not null, " +
                    "location text not null" +
                    ")";

            stmt.executeUpdate(sqlCameras);

            String sqlMachineLearning = "CREATE TABLE IF NOT EXISTS MachineLearningMetadata " +
                    "(" +
                    "image_id text not null unique, " +
                    "primary key (image_id), " +
                    "foreign key (image_id) references ImageBinaries(image_id), " +
                    "environment text not null, " +
                    "animal_id text not null, " +
                    "foreign key (animal_id) references Animals(animal_id), " +
                    ")";

            stmt.executeUpdate(sqlMachineLearning);

            String sqlAnimals = "CREATE TABLE IF NOT EXISTS Animals " +
                    "(" +
                    "animal_id text not null unique, " +
                    "primary key (animal_id), " +
                    "animal_species text, " +
                    "animal_age text" +
                    ")";

            stmt.executeUpdate(sqlAnimals);

        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while creating tables");
        }
    }

    /**
     * Clears all data from our five tables: ImageBinaries, ImageMetadata, Cameras, MachineLearningMetadata, and Animals
     * @throws DataAccessException if a SQL error occurred while clearing the tables
     */
    public void clearTables() throws DataAccessException {

        try (Statement stmt = conn.createStatement()){

            String sqlImageBinary = "DELETE FROM ImageBinaries";
            stmt.executeUpdate(sqlImageBinary);

            String sqlImageMetadata = "DELETE FROM ImageMetadata";
            stmt.executeUpdate(sqlImageMetadata);

            String sqlCameras = "DELETE FROM Cameras";
            stmt.executeUpdate(sqlCameras);

            String sqlMachineLearning = "DELETE FROM MachineLearningMetadata";
            stmt.executeUpdate(sqlMachineLearning);

            String sqlAnimals = "DELETE FROM Animals";
            stmt.executeUpdate(sqlAnimals);

        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}