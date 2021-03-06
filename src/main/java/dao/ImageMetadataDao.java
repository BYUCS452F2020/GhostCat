package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.ImageMetadata;

/**
 * Class for accessing and inserting data in the Cameras table of our database
 */
public class ImageMetadataDao {
  private Connection conn;

  public ImageMetadataDao(Connection conn)
  {
    this.conn = conn;
  }

  /**
   * Inserts the given imageMetadata object into the database
   * @param imageMetadata the imageMetadata object that we want to add to our ImageMetadata table in the database
   * @return commit: a boolean that tells us if the data was added properly, and if we can
   *                  commit the changes to our database
   * @throws DataAccessException: thrown when an SQL error occurs when trying to add the
   *                              imageMetadata to the database
   */
  public boolean insert(ImageMetadata imageMetadata) throws DataAccessException {
    String sql = "INSERT INTO ImageMetadata (image_id, time, date, camera_id) VALUES(?,?,?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, imageMetadata.getImageId());
      stmt.setInt(2, imageMetadata.getTime());
      stmt.setInt(3, imageMetadata.getDate());
      stmt.setString(4, imageMetadata.getCameraId());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException("Error encountered while inserting into the database");
    }
    return true;
  }

  /**
   * Gives every image in the ImageMetadata table of our database
   * @return an ArrayList containing every event in the database
   */
  public ArrayList<ImageMetadata> getAllImageMetadata() throws DataAccessException {
    ArrayList<ImageMetadata> images = new ArrayList<>();
    ResultSet rs = null;

    String sql = "SELECT * FROM ImageMetadata;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      rs = stmt.executeQuery();

      while (rs.next()) {
        images.add(new ImageMetadata(rs.getString("image_id"),
                rs.getInt("time"), rs.getInt("date"), rs.getString("camera_id")));
      }

      if (images.size() > 0) {
        return images;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while getting ImageMetadata");

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
   * Clears all data from the ImageMetadata Table in the database
   */
  public void clearTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sqlEvent = "DELETE FROM ImageMetadata";
      stmt.executeUpdate(sqlEvent);
    } catch (SQLException e) {
      throw new DataAccessException("SQL Error encountered while clearing ImageMetadata table");
    }
  }
}