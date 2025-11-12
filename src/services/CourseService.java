package services;

import model.Course;

public class CourseService {
    private final SimpleList<Course> courses = new SimpleList<>();

    public void add(Course c) {
        if (findIndex(c.getId()) != -1) throw new ValidationException("id exists");
        courses.add(c);
    }

    public void edit(int id, String title, int credits) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        Course c = courses.get(i);
        c.setTitle(title);
        c.setCredits(credits);
        courses.set(i, c);
    }

    public void remove(int id) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        courses.removeAt(i);
    }

    public Course get(int id) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        return courses.get(i);
    }

    public void list() {
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).display());
        }
    }

    public void sortById() {
        for (int i = 0; i < courses.size(); i++) {
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(i).getId() > courses.get(j).getId()) {
                    Course a = courses.get(i);
                    courses.set(i, courses.get(j));
                    courses.set(j, a);
                }
            }
        }
    }

    public void sortByTitle() {
        for (int i = 0; i < courses.size(); i++) {
            for (int j = i + 1; j < courses.size(); j++) {
                if (courses.get(i).getTitle().compareToIgnoreCase(courses.get(j).getTitle()) > 0) {
                    Course a = courses.get(i);
                    courses.set(i, courses.get(j));
                    courses.set(j, a);
                }
            }
        }
    }

    public void searchByTitle(String q) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getTitle().toLowerCase().contains(q.toLowerCase())) {
                System.out.println(courses.get(i).display());
            }
        }
    }

    public int findIndex(int id) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getId() == id) return i;
        }
        return -1;
    }

    public SimpleList<Course> data() {
        return courses;
    }

    public void seedDemo() {
        if (courses.size() == 0) {
            add(new Course(11, "OOP", 4));
            add(new Course(12, "DSA", 4));
        }
    }
}