//Ehran_Lenaerts_R0661627
package fact.it.projectthemepark.model;

public class Attraction {
    private Staff responsible;
    private String name = null;
    private int duration = 0;
    private String photo = null;

    public Attraction() {
    }

    public Attraction(String name) {
        this.name = name;
    }
    public Attraction(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public Staff getResponsible() {
        return responsible;
    }

    public void setResponsible(Staff responsible) {
        this.responsible = responsible;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
