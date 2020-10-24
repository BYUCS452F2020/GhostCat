package model;

public class Camera {
  private String camera_id;
  private String camera_type;
  private String location;

  public Camera(String camera_id, String camera_type, String location) {
    this.camera_id = camera_id;
    this.camera_type = camera_type;
    this.location = location;
  }

  public String getCameraId() {
    return camera_id;
  }

  public void setCameraId(String camera_id) {
    this.camera_id = camera_id;
  }

  public String getCameraType() {
    return camera_type;
  }

  public void setCameraType(String camera_type) {
    this.camera_type = camera_type;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}