//Ehran_Lenaerts_R0661627
package fact.it.projectthemepark.controller;

import fact.it.projectthemepark.model.ThemePark;
import fact.it.projectthemepark.model.Attraction;
import fact.it.projectthemepark.model.Visitor;
import fact.it.projectthemepark.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

@Controller
public class MainController {
//You will need these ArrayLists later on.
    private ArrayList<Staff> staffMembers = new ArrayList<>();
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private ArrayList<ThemePark> themeParks = new ArrayList<>();


//Write your code here

    @PostConstruct
    public void fillAll() {
        staffMembers.addAll(fillStaffMembers());
        visitors.addAll(fillVisitors());
        themeParks.addAll(fillThemeParks());
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @RequestMapping("/1_NewVisitor")
    public String newVisitor(Model model) {
        model.addAttribute("themeParks", themeParks);

        return "1_NewVisitor";
    }

    @RequestMapping("/enterVisitor")
    public String entervisitor(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("Surname");
        Integer birthyear = Integer.parseInt(request.getParameter("yearOfBirth"));
        int ParkIndex = Integer.parseInt(request.getParameter("ThemeParks"));

        ThemePark visitedPark = themeParks.get(ParkIndex);
        Visitor visitor = new Visitor(firstName, surname);
        visitors.add(visitor);
        visitedPark.registerVisitor(visitor);
        model.addAttribute("visitor", visitor);

        return "2_Welcome";
    }

    @RequestMapping("/3_Staff")
    public String newStaff(Model model) {
        model.addAttribute("themeParks", themeParks);
        return "3_staff";
    }

    @RequestMapping("/newStaff")
    public String enterStaff(HttpServletRequest request, Model model) {
        String firstName = request.getParameter("staffFirstName");
        String lastName = (request.getParameter("staffSurname"));
        LocalDate employedsince = LocalDate.parse(request.getParameter("employedtime"), formatter);
        Boolean workingstudent = ((request.getParameter("workStudent") != null));
        int themeparkIndex = Integer.parseInt(request.getParameter("employedAt"));
        System.out.println(themeparkIndex);
        if (themeparkIndex < 0) {
            model.addAttribute("errormessage", "You didn't choose a theme park!");
            return "error";
        }

        Staff newstaff = new Staff(firstName, lastName);
        newstaff.setStartDate(employedsince);
        newstaff.setStudent(workingstudent);

        newstaff.setEmployedAt(themeParks.get(themeparkIndex));
        staffMembers.add(newstaff);
        model.addAttribute("newStaff", newstaff);

        return "0_exam";
    }

    @RequestMapping("/5_AllStaff")
    public String allStaff(Model model) {
        model.addAttribute("staffMembers", staffMembers);
        return "5_AllStaff";
    }

    @RequestMapping("/6_AllVisitors")
    public String allVisitor(Model model) {
        model.addAttribute("visitors", visitors);
        return "6_AllVisitors";
    }

    @RequestMapping("/7_NewThemepark")
    public String newThemePark() {
        return "7_NewThemepark";
    }

    @RequestMapping("/8_AllParks")
    public String allParks(Model model) {
        model.addAttribute("themeParks", themeParks);
        return "8_AllParks";
    }

    @RequestMapping("NewThemePark")
    public String addThemePark(HttpServletRequest request, Model model) {
        String newPark = request.getParameter("themepark");
        ThemePark NewPark = new ThemePark(newPark);

        themeParks.add(NewPark);
        model.addAttribute("themeParks", themeParks);
        return "8_AllParks";
    }

    @RequestMapping("9_NewAttraction")
    public String newAttraction(Model model) {
        model.addAttribute("themeParks", themeParks);
        model.addAttribute("Staff", staffMembers);
        return "9_NewAttraction";
    }

    @RequestMapping("NewAttraction")
    public String addNewAttraction(HttpServletRequest request, Model model) {
        String nameAttraction = request.getParameter("nameattraction");
        int durationAttraction = Integer.parseInt(request.getParameter("aduration"));
        String photoAttaction = request.getParameter("photo");
        int themeparkIndex = Integer.parseInt(request.getParameter("themeparkIndex"));
        if (themeparkIndex < 0) {
            model.addAttribute("errormessage", "You didn't choose any theme park!");
            return "error";
        }
        int staffIndex = Integer.parseInt(request.getParameter("staffIndex"));
        if (staffIndex < 0) {
            model.addAttribute("errormessage", "You didn't choose a staff member!");
            return "error";
        }

        Attraction newAttraction = new Attraction(nameAttraction, durationAttraction);
        newAttraction.setResponsible(staffMembers.get(staffIndex));
        newAttraction.setPhoto(photoAttaction);
        ThemePark newattrac = themeParks.get(themeparkIndex);
        newattrac.addAttraction(newAttraction);

        model.addAttribute("themepark",newattrac);

        return "10_ThemeParkAttractions";
    }

    @RequestMapping("/10_ThemeParkAttractions")
    public String goToThemePark(HttpServletRequest request, Model model) {
        int parkIndex = Integer.parseInt(request.getParameter("parkIndex"));
        model.addAttribute("themepark", themeParks.get(parkIndex));
        return "/10_ThemeParkAttractions";
    }

    @RequestMapping("/searchAttraction")
    public String findAttraction(HttpServletRequest request, Model model) {
        String tofindAttraction = request.getParameter("searchattraction");
        for (ThemePark parks: themeParks) {
            if (parks.searchAttractionByName(tofindAttraction) != null) {
                Attraction foundAttraction = parks.searchAttractionByName(tofindAttraction);
                model.addAttribute("foundAttraction", foundAttraction);
                return "/11_FoundAttraction";
            }

        }
        model.addAttribute("errormessage", "There is no attraction with the name '" + tofindAttraction + "'");
        return "error";
    }




//You will need these methods later on.
    private ArrayList<Staff> fillStaffMembers() {
        ArrayList<Staff> staffMembers = new ArrayList<>();

        Staff staff1 = new Staff("Johan", "Bertels");
        staff1.setStartDate(LocalDate.of(2002, 5, 1));
        Staff staff2 = new Staff("An", "Van Herck");
        staff2.setStartDate(LocalDate.of(2019, 3, 15));
        staff2.setStudent(true);
        Staff staff3 = new Staff("Bruno", "Coenen");
        staff3.setStartDate(LocalDate.of(1995,1,1));
        Staff staff4 = new Staff("Wout", "Dayaert");
        staff4.setStartDate(LocalDate.of(2002, 12, 15));
        Staff staff5 = new Staff("Louis", "Petit");
        staff5.setStartDate(LocalDate.of(2020, 8, 1));
        staff5.setStudent(true);
        Staff staff6 = new Staff("Jean", "Pinot");
        staff6.setStartDate(LocalDate.of(1999,4,1));
        Staff staff7 = new Staff("Ahmad", "Bezeri");
        staff7.setStartDate(LocalDate.of(2009, 5, 1));
        Staff staff8 = new Staff("Hans", "Volzky");
        staff8.setStartDate(LocalDate.of(2015, 6, 10));
        staff8.setStudent(true);
        Staff staff9 = new Staff("Joachim", "Henau");
        staff9.setStartDate(LocalDate.of(2007,9,18));
        staffMembers.add(staff1);
        staffMembers.add(staff2);
        staffMembers.add(staff3);
        staffMembers.add(staff4);
        staffMembers.add(staff5);
        staffMembers.add(staff6);
        staffMembers.add(staff7);
        staffMembers.add(staff8);
        staffMembers.add(staff9);
        return staffMembers;
    }

    private ArrayList<Visitor> fillVisitors() {
        ArrayList<Visitor> visitors = new ArrayList<>();
        Visitor visitor1 = new Visitor("Dominik", "Mioens");
        visitor1.setYearOfBirth(2001);
        Visitor visitor2 = new Visitor("Zion", "Noops");
        visitor2.setYearOfBirth(1996);
        Visitor visitor3 = new Visitor("Maria", "Bonetta");
        visitor3.setYearOfBirth(1998);
        Visitor visitor4 = new Visitor("Ehran", "Lenaerts");
        visitor4.setYearOfBirth(1998);
        visitors.add(visitor1);
        visitors.add(visitor2);
        visitors.add(visitor3);
        visitors.add(visitor4);
        visitors.get(0).addToWishList("De grote golf");
        visitors.get(0).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Piratenboot");
        visitors.get(1).addToWishList("Sky Scream");
        visitors.get(1).addToWishList("Halvar the ride");
        visitors.get(1).addToWishList("DreamCatcher");
        visitors.get(2).addToWishList("DinoSplash");
        visitors.get(3).addToWishList("Sky Scream");
        visitors.get(3).addToWishList("DreamCatcher");
        return visitors;
    }

    private ArrayList<ThemePark> fillThemeParks() {
        ArrayList<ThemePark> themeparks = new ArrayList<>();
        ThemePark themepark1 = new ThemePark("Plopsaland");
        ThemePark themepark2 = new ThemePark("Plopsa Coo");
        ThemePark themepark3 = new ThemePark("Holiday Park");
        Attraction attraction1 = new Attraction("Anubis the Ride", 60);
        Attraction attraction2 = new Attraction("De grote golf", 180);
        Attraction attraction3 = new Attraction("Piratenboot",150);
        Attraction attraction4 = new Attraction("SuperSplash", 258);
        Attraction attraction5 = new Attraction("Dansende fonteinen");
        Attraction attraction6 = new Attraction("Halvar the ride",130);
        Attraction attraction7 = new Attraction("DinoSplash", 240);
        Attraction attraction8 = new Attraction("Bounty Tower", 180);
        Attraction attraction9 = new Attraction("Sky Scream",50);
        attraction1.setPhoto("/img/anubis the ride.jpg");
        attraction2.setPhoto("/img/de grote golf.jpg");
        attraction3.setPhoto("/img/piratenboot.jpg");
        attraction4.setPhoto("/img/supersplash.jpg");
        attraction5.setPhoto("/img/dansende fonteinen.jpg");
        attraction6.setPhoto("/img/halvar the ride.jpg");
        attraction7.setPhoto("/img/dinosplash.jpg");
        attraction8.setPhoto("/img/bountytower.jpg");
        attraction9.setPhoto("/img/sky scream.jpg");
        attraction1.setResponsible(staffMembers.get(0));
        attraction2.setResponsible(staffMembers.get(1));
        attraction3.setResponsible(staffMembers.get(2));
        attraction4.setResponsible(staffMembers.get(3));
        attraction5.setResponsible(staffMembers.get(4));
        attraction6.setResponsible(staffMembers.get(5));
        attraction7.setResponsible(staffMembers.get(6));
        attraction8.setResponsible(staffMembers.get(7));
        attraction9.setResponsible(staffMembers.get(8));
        themepark1.addAttraction(attraction1);
        themepark1.addAttraction(attraction2);
        themepark1.addAttraction(attraction3);
        themepark1.addAttraction(attraction4);
        themepark2.addAttraction(attraction5);
        themepark2.addAttraction(attraction6);
        themepark3.addAttraction(attraction7);
        themepark3.addAttraction(attraction8);
        themepark3.addAttraction(attraction9);
        themeparks.add(themepark1);
        themeparks.add(themepark2);
        themeparks.add(themepark3);
        return themeparks;
    }


}

