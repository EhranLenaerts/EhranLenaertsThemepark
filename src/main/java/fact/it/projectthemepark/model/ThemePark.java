//Ehran_Lenaerts_R0661627
package fact.it.projectthemepark.model;

import java.util.ArrayList;

public class ThemePark {
    private ArrayList< Attraction > attractions = new ArrayList<>();
    private String name = null;
    private int numberVisitors = 0;

    public ThemePark(String name) {
        this.name = name;
    }

    public ArrayList<Attraction> getAttractions() {
        return attractions;
    }

    public String getName() {
        return name;
    }

    public int getNumberVisitors() {
        return numberVisitors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfAttractions() {
        return attractions.size();
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public Attraction searchAttractionByName(String name) {
        for (Attraction attraction: attractions) {
            if ((attraction.getName()).equals(name)) {
                return attraction;
            }
        }
        return null;
    }

    public void registerVisitor(Visitor visitor) {
        numberVisitors = numberVisitors + 1;
        visitor.setThemeParkCode(numberVisitors);
    }
}
