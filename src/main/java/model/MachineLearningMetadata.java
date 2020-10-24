package model;

public class MachineLearningMetadata {
  private String image_id;
  private String environment;
  private String animal_id;

  public MachineLearningMetadata(String image_id, String environment, String animal_id) {
    this.image_id = image_id;
    this.environment = environment;
    this.animal_id = animal_id;
  }

  public void setImageId(String image_id) {
    this.image_id = image_id;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public void setAnimalId(String animal_id) {
    this.animal_id = animal_id;
  }

  public String getEnvironment() {
    return this.environment;
  }

  public String getImageId() {
    return this.image_id;
  }

  public String getAnimalId() {
    return this.animal_id;
  }
}
