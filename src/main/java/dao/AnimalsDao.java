package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Animal;

/**
 * Class for accessing and inserting data in the Animals table of our database
 */
public class AnimalsDao {
    private Connection conn;

    public AnimalsDao(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Inserts the given animal object into the database
     * @param animal the animal object that we want to add to our animal table in the database
     * @return commit: a boolean that tells us if the data was added properly, and if we can
     *                  commit the changes to our database
     * @throws DataAccessException: thrown when an SQL error occurs when trying to add the
     *                              animal to the database
     */
    public boolean insert(Animal animal) throws DataAccessException {
        String sql = "INSERT INTO Animals (animal_id, animal_species, animal_age) VALUES(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, animal.getAnimalId());
            stmt.setString(2, animal.getAnimalSpecies());
            stmt.setString(3, animal.getAnimalAgeString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }

        return true;
    }

    /**
     * Gives every animal in the Animals table of our database
     * @return an ArrayList containing every event in the database
     */
    public ArrayList<Animal> getAllAnimals() throws DataAccessException {
        ArrayList<Animal> animals = new ArrayList<>();
        ResultSet rs = null;

        String sql = "SELECT * FROM Animals;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            rs = stmt.executeQuery();

            while (rs.next()) {
                animals.add(new Animal(rs.getString("animal_species"), rs.getString("animal_age"),
                        rs.getString("animal_id")));
            }

            if (animals.size() > 0) {
                return animals;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while getting animals");

        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Clears all data from the Animals Table in the database
     */
    public void clearTable() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sqlEvent = "DELETE FROM Animals";
            stmt.executeUpdate(sqlEvent);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Animals table");
        }
    }
}