package main;

import dao.AnimalsDao;
import dao.CamerasDao;
import dao.DataAccessException;
import dao.Database;
import dao.ImageMetadataDao;
import dao.ImageUrlsDao;
import dao.MachineLearningMetadataDao;
import dao.QueryRunner;
import model.Animal;
import model.Camera;
import model.Image;
import model.ImageMetadata;
import model.MachineLearningMetadata;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
  private static int animal_id = 0;
  private static int camera_id = 0;
  private static int image_id = 0;

  public static void main(String[] args) {
    Database db = new Database();
    try {
//      createDatabaseWithTables(db);
//      insertDummyDataIntoDatabase(db);
//      printDummyDataFromDatabase(db);
//      getSqlFromUserAndExecute(db);
      addLotsOfDataToDatabase(db);
    } catch (DataAccessException e) {
      e.printStackTrace();
      exit(1);
    }

  }

  public static void createDatabaseWithTables(Database db) throws DataAccessException {
    try {
      db.openConnection();
      db.createTables();
      db.closeConnection(true);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      db.closeConnection(false);
    }
  }

  public static void getSqlFromUserAndExecute(Database db) throws DataAccessException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("SELECT: ");
    String selectClause = scanner.nextLine();
    System.out.println("FROM: ");
    String fromClause = scanner.nextLine();
    System.out.println("WHERE: ");
    String whereClause = scanner.nextLine();

    try {
      Connection conn = db.openConnection();
      QueryRunner queryRunner = new QueryRunner(conn);
      queryRunner.runSelectStatement(selectClause, fromClause, whereClause);
      db.clearTables();
      db.closeConnection(true);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      db.closeConnection(false);
    }

  }

  public static void insertDummyDataIntoDatabase(Database db) throws DataAccessException {
    try {
      Connection conn = db.openConnection();
      Animal animal = new Animal("species", "child", "id");
      Camera camera = new Camera("id", "type", "location");
      Image image = new Image("id", "https://image.png");
      ImageMetadata imageMetadata = new ImageMetadata("id", 10, 102420,
              "camera_id");
      MachineLearningMetadata machineLearningMetadata = new MachineLearningMetadata("id",
              "environment", "animal_id");

      AnimalsDao animalsDao = new AnimalsDao(conn);
      animalsDao.insert(animal);

      CamerasDao camerasDao = new CamerasDao(conn);
      camerasDao.insert(camera);

      ImageUrlsDao imageUrlsDao = new ImageUrlsDao(conn);
      imageUrlsDao.insert(image);

      ImageMetadataDao imageMetadataDao = new ImageMetadataDao(conn);
      imageMetadataDao.insert(imageMetadata);

      MachineLearningMetadataDao machineLearningMetadataDao = new MachineLearningMetadataDao(conn);
      machineLearningMetadataDao.insert(machineLearningMetadata);

      db.closeConnection(true);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      db.closeConnection(false);
    }
  }

  public static void printDummyDataFromDatabase(Database db) throws DataAccessException {
    try {
      Connection conn = db.openConnection();

      AnimalsDao animalsDao = new AnimalsDao(conn);
      CamerasDao camerasDao = new CamerasDao(conn);
      ImageUrlsDao imageUrlsDao = new ImageUrlsDao(conn);
      ImageMetadataDao imageMetadataDao = new ImageMetadataDao(conn);
      MachineLearningMetadataDao machineLearningMetadataDao = new MachineLearningMetadataDao(conn);

      ArrayList<Animal> animals = animalsDao.getAllAnimals();
      ArrayList<Camera> cameras = camerasDao.getAllCameras();
      ArrayList<Image> images = imageUrlsDao.getAllImageUrls();
      ArrayList<ImageMetadata> imageMetadata = imageMetadataDao.getAllImageMetadata();
      ArrayList<MachineLearningMetadata> machineLearnings = machineLearningMetadataDao.getAllMachineLearningMetadata();

      for(Animal animal1 : animals) {
        System.out.println("Animal: " + "id:" + animal1.getAnimalId() + " age:" +
                animal1.getAnimalAgeString() + " species:" + animal1.getAnimalSpecies());
      }

      for(Camera camera1 : cameras) {
        System.out.println("Camera: " + "id:" + camera1.getCameraId() + " type:" +
                camera1.getCameraType() + " location:" + camera1.getLocation());
      }

      for(Image image1 : images) {
        System.out.println("Image: " + "id:" + image1.getImageId() + " url:" + image1.getImageUrl());
      }

      for(ImageMetadata imageMetadata1 : imageMetadata) {
        System.out.println("ImageMetadata: " + "id:" + imageMetadata1.getImageId() + " time:" +
                imageMetadata1.getTime() + " date:" + imageMetadata1.getDate() + " camera:" +
                imageMetadata1.getCameraId());
      }

      for(MachineLearningMetadata machineLearningMetadata1 : machineLearnings) {
        System.out.println("MachineLearningMetadata: " + "id:" +
                machineLearningMetadata1.getImageId() + " environment:" +
                machineLearningMetadata1.getEnvironment() + " animal:" +
                machineLearningMetadata1.getAnimalId());
      }

      db.closeConnection(true);
    } catch (DataAccessException dataAccessException) {
      dataAccessException.printStackTrace();
      db.closeConnection(false);
    }
  }

  public static void addLotsOfDataToDatabase(Database db) throws DataAccessException {
    try {
      Connection connection = db.openConnection();
      AnimalsDao animalsDao = new AnimalsDao(conn);
      CamerasDao camerasDao = new CamerasDao(conn);
      ImageUrlsDao imageUrlsDao = new ImageUrlsDao(conn);
      ImageMetadataDao imageMetadataDao = new ImageMetadataDao(conn);
      MachineLearningMetadataDao machineLearningMetadataDao = new MachineLearningMetadataDao(conn);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Animal getRandomAnimal() {
    String animal_species = "";
    if (animal_id % 3 == 0) {
      animal_species = "mule deer";
    } else if (animal_id % 3 == 1) {
      animal_species = "elephant";
    } else {
      animal_species = "squirrel";
    }
    animal_id++;

    return new Animal(animal_species, "adolescent", Integer.toString(animal_id));
  }

  private static Camera getRandomCamera() {
    String camera_type = "";
    String location = "";
    if (camera_id % 3 == 0) {
      camera_type = "wide lens";
      location = "savannah";
    } else if (camera_id % 3 == 1) {
      camera_type = "narrow lens";
      location = "arctic";
    } else {
      camera_type = "drone camera";
      location = "forest";
    }
    camera_id++;

    return new Camera(Integer.toString(camera_id), camera_type, location);
  }

  private static Image getRandomImage() {

    return new Image();
  }

  private static ImageMetadata getRandomImageMetadata() {

    return new ImageMetadata();
  }

  private static MachineLearningMetadata getRandomMachineLearningMetadata() {

    return new MachineLearningMetadata();
  }
}
