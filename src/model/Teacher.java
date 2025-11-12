package model;

public class Teacher extends Person {
    private String subject;

    public Teacher() {
    }

    public Teacher(int id, String name, String email, String subject) {
        super(id, name, email);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double fee() {
        return 0.0;
    }

    public String display() {
        return super.display() + " " + subject;
    }
}