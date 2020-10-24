package model;

enum AnimalAgeGroup {
  Child,
  Juvenile,
  Adolescent,
  Adult,
  Elderly;

  public String toString(AnimalAgeGroup age) {
    switch(age) {
      case Child:
        return "child";
      case Juvenile:
        return "juvenile";
      case Adolescent:
        return "adolescent";
      case Adult:
        return "adult";
      case Elderly:
        return "elderly";
    }
  }
}

public class Animal {
  private String animal_species;
  private AnimalAgeGroup animal_age;
  private String animal_id;

  public Animal(String animal_species, AnimalAgeGroup animal_age, String animal_id) {
    this.animal_species = animal_species;
    this.animal_age = animal_age;
    this.animal_id = animal_id;
  }

  public Animal(String animal_species, String animal_age, String animal_id) {
    this.animal_species = animal_species;

    switch(animal_age) {
      case "child":
        this.animal_age = AnimalAgeGroup.Child;
        break;
      case "juvenile":
        this.animal_age = AnimalAgeGroup.Juvenile;
        break;
      case "adolescent":
        this.animal_age = AnimalAgeGroup.Adolescent;
        break;
      case "adult":
        this.animal_age = AnimalAgeGroup.Adult;
        break;
      case "elderly":
        this.animal_age = AnimalAgeGroup.Elderly;
        break;
    }

    this.animal_id = animal_id;
  }

  public String getAnimalSpecies() {
    return this.animal_species;
  }

  public AnimalAgeGroup getAnimalAge() {
    return this.animal_age;
  }

  public String getAnimalAgeString() {
    return this.animal_age.toString();
  }

  public String getAnimalId() {
    return this.animal_id;
  }

  public void setAnimalSpecies(String animal_species) {
    this.animal_species = animal_species;
  }

  public void setAnimalAgeGroup(AnimalAgeGroup animal_age) {
    this.animal_age = animal_age;
  }

  public void setAnimalId(String animal_id) {
    this.animal_id = animal_id;
  }
}