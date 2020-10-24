package model;

public class Image {
  private String image_id;
  private String image_url;

  public Image(String image_id, String image_url) {
    this.image_id = image_id;
    this.image_url = image_url;
  }

  public String getImageUrl() {
    return this.image_url;
  }

  public String getImageId() {
    return this.image_id;
  }
}
