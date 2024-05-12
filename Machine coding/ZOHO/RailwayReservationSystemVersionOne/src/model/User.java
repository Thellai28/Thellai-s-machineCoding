package model;

public class User {
    private String name;
    private int age;
    private String BerthPreference;
    private String gender;

    public User( int age, String berthPreference, String gender, String name ) {
        this.age = age;
        BerthPreference = berthPreference;
        this.gender = gender;
        this.name = name;
    }



    //-------------< Getters & setters >----------------------------------------


    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public String getBerthPreference() {
        return BerthPreference;
    }

    public void setBerthPreference( String berthPreference ) {
        BerthPreference = berthPreference;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }
}
