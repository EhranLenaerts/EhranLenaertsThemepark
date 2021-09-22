//Ehran_Lenaerts_R0661627
package fact.it.projectthemepark.model;

import java.util.ArrayList;
import java.util.Locale;

public class Visitor extends Person {
    private int yearOfBirth = 0;
    private int themeParkCode = 0;
    private ArrayList<String> wishList = new ArrayList<>();

    public Visitor(String firstName, String surName) {
        super(firstName, surName);
        this.themeParkCode = -1;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getThemeParkCode() {
        return themeParkCode;
    }

    public void setThemeParkCode(int themeParkCode) {
        this.themeParkCode = themeParkCode;
    }

    public ArrayList<String> getWishList() {
        return wishList;
    }

    public boolean addToWishList(String attractionName) {
        if (wishList.size() >= 5) {
            return false;
        }
        else  {
            wishList.add(attractionName);
            return true;
        }
    }

    @Override
    public String toString() {
        return "Visitor " + super.toString() + " with theme park code " + getThemeParkCode();
    }

    public int getNumberOfWishes() {
        return wishList.size();
    }
}
