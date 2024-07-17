package model;

public class Employee {
    private static long employeeIdGenerator = 1;// This is determined as long, to avoid being included in integer fields
    private int employeeId;
    private String name;
    private int age;
    private String reportingTo;
    private String designation;
    private String department;

    public Employee( String name, int age, String reportingTo, String designation, String department ) {
        this.age = age;
        this.name = name;
        this.reportingTo = reportingTo;
        this.designation = designation;
        this.department = department;
        this.employeeId = (int)employeeIdGenerator++;
    }

    @Override
    public String toString() {
        String reportingManager = (reportingTo != null ) ? reportingTo : "Im CEO";
        return String.format("%-5d| %-20s| %-5d| %-27s| %-25s| %-20s",
                employeeId, name, age, designation, department,reportingManager );
    }

    //------------------------< Getter & setter >---------------------------------------

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment( String department ) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation( String designation ) {
        this.designation = designation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId( int employeeId ) {
        this.employeeId = employeeId;
    }

    public String getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo( String reportingTo ) {
        this.reportingTo = reportingTo;
    }

    public String getName() {
        return name;
    }
}
