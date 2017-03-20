import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

    private final String firstName;
    private final String lastName;
    private final String profession;
    private final String wage;
    private final String age;
    private final String skills;

    Person(String fName, String lName, String professionIn, String wageIn, String ageIn, String skillsIn) {
        this.firstName = fName;
        this.lastName = lName;
        this.profession = professionIn;
        this.wage = wageIn;
        this.age = ageIn;
        this.skills = skillsIn;


    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfession() {
        return profession;
    }

    public String getWage() {
        return wage;
    }

    public String getAge() {
        return age;
    }

    public String getSkills() {
        return skills;
    }
}