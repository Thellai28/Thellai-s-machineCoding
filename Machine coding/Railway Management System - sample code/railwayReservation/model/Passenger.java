package railwayReservation.model;

public class Passenger {
    private static int centralTicketIdProvider = 0;
    private int ticketNumber;
    private String name;
    private int age;
    private char seatPreference;
    private String ticketType; // The use will directly enter the birth type:
    private int seatNumber;

    public Passenger( int age,  String name, char preference) {
        this.age = age;
        this.ticketNumber = ++centralTicketIdProvider;
        this.name = name;
        this.seatPreference = preference;
    }
    @Override
    public String toString(){
        return "Passenger ticket id : " + ticketNumber +
                "\nPassenger name : " + name +
                "\nPassenger age : " + age +
                "\nPassenger seat no : " + seatNumber+
                "\nPassenger Preference : " + seatPreference;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber( int ticketNumber ) {
        this.ticketNumber = ticketNumber;
    }

    public static int getCentralTicketIdProvider() {
        return centralTicketIdProvider;
    }

    public static void setCentralTicketIdProvider( int centralTicketIdProvider ) {
        Passenger.centralTicketIdProvider = centralTicketIdProvider;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public char getSeatPreference() {
        return seatPreference;
    }

    public void setSeatPreference( char seatPreference ) {
        this.seatPreference = seatPreference;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber( int seatNumber ) {
        this.seatNumber = seatNumber;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType( String ticketType ) {
        this.ticketType = ticketType;
    }
}
