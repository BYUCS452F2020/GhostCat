package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Camera;

/**
 * Class for accessing and inserting data in the Cameras table of our database
 */
public class CamerasDao {
    private Connection conn;

    public CamerasDao(Connection conn)
    {
        this.conn = conn;
    }

    /**
     * Inserts the given camera object into the database
     * @param camera the camera object that we want to add to our camera table in the database
     * @return commit: a boolean that tells us if the data was added properly, and if we can
     *                  commit the changes to our database
     * @throws DataAccessException: thrown when an SQL error occurs when trying to add the
     *                              camera to the database
     */
    public boolean insert(Camera camera) throws DataAccessException {
        String sql = "INSERT INTO Cameras (camera_id, camera_type, location) VALUES(?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, camera.getCameraId());
            stmt.setString(2, camera.getCameraType());
            stmt.setString(3, camera.getLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
        return true;
    }

    /**
     * Gives every camera in the Cameras table of our database
     * @return an ArrayList containing every event in the database
     */
    public ArrayList<Camera> getAllCameras() throws DataAccessException {
        ArrayList<Camera> cameras = new ArrayList<>();
        ResultSet rs = null;

        String sql = "SELECT * FROM Cameras;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            rs = stmt.executeQuery();

            while (rs.next()) {
                cameras.add(new Camera(rs.getString("camera_id"),
                        rs.getString("camera_type"), rs.getString("location")));
            }

            if (cameras.size() > 0) {
                return cameras;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while getting Cameras");

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
     * Clears all data from the Cameras Table in the database
     */
    public void clearTable() throws DataAccessException {
        try (Statement stmt = conn.createStatement()){
            String sqlEvent = "DELETE FROM Cameras";
            stmt.executeUpdate(sqlEvent);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing Cameras table");
        }
    }
}