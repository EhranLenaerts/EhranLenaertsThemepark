//Ehran_Lenaerts_R0661627
package fact.it.projectthemepark.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Staff extends Person {
    private LocalDate startDate = LocalDate.now();
    private Boolean student = false;
    private ThemePark ThemePark;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Staff(String firstName, String surName) {
        super(firstName, surName);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Boolean isStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    @Override
    public String toString() {
        if (isStudent()) {
            return String.format("Staff member %s (working student) is employed since %s", super.toString(), getStartDate().format(formatter));
        }
        else {
            return String.format("Staff member %s is employed since %s", super.toString(), getStartDate().format(formatter));
        }
    }

    public ThemePark getEmployedAt() {
        return ThemePark;
    }

    public void setEmployedAt(ThemePark employedAt) {
        this.ThemePark = employedAt;
    }
}
