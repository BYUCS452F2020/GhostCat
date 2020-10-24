package model;

public class ImageMetadata {
  private String image_id;
  private int time;
  private int date;
  private String camera_id;

  public ImageMetadata(String image_id, int time, int date, String camera_id) {
    this.image_id = image_id;
    this.time = time;
    this.date = date;
    this.camera_id = camera_id;
  }

  public void setImageId(String image_id) {
    this.image_id = image_id;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public void setDate(int date) {
    this.date = date;
  }

  public void setCameraId(String camera_id) {
    this.camera_id = camera_id;
  }

  public String getImageId() {
    return this.image_id;
  }

  public int getTime() {
    return this.time;
  }

  public int getDate() {
    return this.date;
  }

  public String getCameraId() {
    return this.camera_id;
  }
}
