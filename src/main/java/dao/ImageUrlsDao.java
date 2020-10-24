package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Image;

/**
 * Class for accessing and inserting data in the Cameras table of our database
 */
public class ImageUrlsDao {
  private Connection conn;

  public ImageUrlsDao(Connection conn)
  {
    this.conn = conn;
  }

  /**
   * Inserts the given image object into the database
   * @param image the image object that we want to add to our image table in the database
   * @return commit: a boolean that tells us if the data was added properly, and if we can
   *                  commit the changes to our database
   * @throws DataAccessException: thrown when an SQL error occurs when trying to add the
   *                              image to the database
   */
  public boolean insert(Image image) throws DataAccessException {
    String sql = "INSERT INTO ImageUrls (image_id, image_url) VALUES(?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, image.getImageId());
      stmt.setString(2, image.getImageUrl());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException("Error encountered while inserting into the database");
    }
    return true;
  }

  /**
   * Gives every image in the ImageUrls table of our database
   * @return an ArrayList containing every event in the database
   */
  public ArrayList<Image> getAllImageUrls() throws DataAccessException {
    ArrayList<Image> images = new ArrayList<>();
    ResultSet rs = null;

    String sql = "SELECT * FROM ImageUrls;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      rs = stmt.executeQuery();

      while (rs.next()) {
        images.add(new Image(rs.getString("image_id"),
                rs.getString("image_url")));
      }

      if (images.size() > 0) {
        return images;
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while getting ImageUrls");

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
   * Clears all data from the ImageUrls Table in the database
   */
  public void clearTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sqlEvent = "DELETE FROM ImageUrls";
      stmt.executeUpdate(sqlEvent);
    } catch (SQLException e) {
      throw new DataAccessException("SQL Error encountered while clearing ImageUrls table");
    }
  }
}