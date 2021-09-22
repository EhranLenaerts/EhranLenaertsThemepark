//Ehran_Lenaerts_R0661627
package fact.it.projectthemepark.model;

import java.util.Locale;

public class Person {
    private String firstName = null;
    private String surName = null;

    public Person() {
    }

    public Person(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String toString() {
        return getSurName().toUpperCase() + " " + getFirstName();
    }
}
