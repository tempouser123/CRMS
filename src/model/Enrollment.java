package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Enrollment implements Serializable, Printable {
    private int studentId;
    private int courseId;
    private LocalDate date;

    public Enrollment() {
    }

    public Enrollment(int studentId, int courseId, LocalDate date) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public String display() {
        return studentId + "->" + courseId + " " + date;
    }
}