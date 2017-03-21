import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {

    //Instance variables
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty profession;
    private final IntegerProperty wage;
    private final IntegerProperty age;
    private final StringProperty skills;

    //Constructor
    Person(String fName, String lName, String professionIn, int wageIn, int ageIn, String skillsIn) {
        this.firstName = new SimpleStringProperty(fName);
        this.lastName = new SimpleStringProperty(lName);
        this.profession = new SimpleStringProperty(professionIn);
        this.wage = new SimpleIntegerProperty(wageIn);
        this.age = new SimpleIntegerProperty(ageIn);
        this.skills = new SimpleStringProperty(skillsIn);
    }

    //Get functions
    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getProfession() {
        return profession.get();
    }

    public int getWage() {
        return wage.get();
    }

    public int getAge() {
        return age.get();
    }

    public String getSkills() {
        return skills.get();
    }
}