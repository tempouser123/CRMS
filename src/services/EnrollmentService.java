package services;

import model.Enrollment;

import java.time.LocalDate;

public class EnrollmentService {
    private final SimpleList<Enrollment> enrollments = new SimpleList<>();

    public void enroll(int studentId, int courseId) {
        enrollments.add(new Enrollment(studentId, courseId, LocalDate.now()));
    }

    public void drop(int studentId, int courseId) {
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            if (e.getStudentId() == studentId && e.getCourseId() == courseId) {
                enrollments.removeAt(i);
                return;
            }
        }
        throw new NotFoundException("not found");
    }

    public void list() {
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println(enrollments.get(i).display());
        }
    }

    public void listByStudent(int sid) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getStudentId() == sid) {
                System.out.println(enrollments.get(i).display());
            }
        }
    }

    public void listByCourse(int cid) {
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getCourseId() == cid) {
                System.out.println(enrollments.get(i).display());
            }
        }
    }

    public SimpleList<Enrollment> data() {
        return enrollments;
    }
}