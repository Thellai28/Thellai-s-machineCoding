package model;

public class Passenger {
    private String name;
    private String preferredBerth;

    public Passenger( String name, String preferredBerth ) {
        this.name = name;
        this.preferredBerth = preferredBerth;
    }


    //----------------< Getter>------------------------

    public String getName() {
        return name;
    }

    public String getPreferredBerth() {
        return preferredBerth;
    }

}
