package model;

public class Student extends Person {
    private int roll;
    private String program;

    public Student() {
    }

    public Student(int id, String name, String email, int roll, String program) {
        super(id, name, email);
        this.roll = roll;
        this.program = program;
    }

    public int getRoll() {
        return roll;
    }

    public String getProgram() {
        return program;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public double fee() {
        return 1200.0;
    }

    public String display() {
        return super.display() + " " + roll + " " + program + " " + fee();
    }
}