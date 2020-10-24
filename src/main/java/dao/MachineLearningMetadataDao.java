package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.MachineLearningMetadata;

/**
 * Class for accessing and inserting data in the Cameras table of our database
 */
public class MachineLearningMetadataDao {
  private Connection conn;

  public MachineLearningMetadataDao(Connection conn)
  {
    this.conn = conn;
  }

  /**
   * Inserts the given machineLearningMetadata object into the database
   * @param machineLearningMetadata the machineLearningMetadata object that we want to add to our MachineLearningMetadata table in the database
   * @return commit: a boolean that tells us if the data was added properly, and if we can
   *                  commit the changes to our database
   * @throws DataAccessException: thrown when an SQL error occurs when trying to add the
   *                              machineLearningMetadata to the database
   */
  public boolean insert(MachineLearningMetadata machineLearningMetadata) throws DataAccessException {
    String sql = "INSERT INTO MachineLearningMetadata (image_id, environment, animal_id) VALUES(?,?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, machineLearningMetadata.getImageId());
      stmt.setString(2, machineLearningMetadata.getEnvironment());
      stmt.setString(3, machineLearningMetadata.getAnimalId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException("Error encountered while inserting into the database");
    }
    return true;
  }

  /**
   * Gives all the machine learning metadata in the MachineLearningMetadata table of our database
   * @return an ArrayList containing every event in the database
   */
  public ArrayList<MachineLearningMetadata> getAllMachineLearningMetadata() throws DataAccessException {
    ArrayList<MachineLearningMetadata> metadataResultList = new ArrayList<>();
    ResultSet rs = null;

    String sql = "SELECT * FROM MachineLearningMetadata;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      rs = stmt.executeQuery();

      while (rs.next()) {
        metadataResultList.add(new MachineLearningMetadata(rs.getString("image_id"),
                rs.getString("environment"), rs.getString("animal_id")));
      }

      if (metadataResultList.size() > 0) {
        return metadataResultList;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while getting MachineLearningMetadata");

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
   * Clears all data from the MachineLearningMetadata Table in the database
   */
  public void clearTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sqlEvent = "DELETE FROM MachineLearningMetadata";
      stmt.executeUpdate(sqlEvent);
    } catch (SQLException e) {
      throw new DataAccessException("SQL Error encountered while clearing MachineLearningMetadata table");
    }
  }
}