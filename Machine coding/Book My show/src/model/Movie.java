package model;

public class Movie {
    private String name;
    private String language;

    public Movie( String language, String name ) {
        this.language = language;
        this.name = name;
    }


    //----------------<getters & setters>-----------------------------

    public String getLanguage() {
        return language;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
