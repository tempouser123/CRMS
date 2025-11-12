package services;

import model.Person;
import model.Student;
import model.Teacher;

import java.util.Scanner;

public class PersonService {
    private final SimpleList<Person> people = new SimpleList<>();

    public void add(Student s) {
        if (findIndex(s.getId()) != -1) throw new ValidationException("id exists");
        people.add(s);
    }

    public void add(Teacher t) {
        if (findIndex(t.getId()) != -1) throw new ValidationException("id exists");
        people.add(t);
    }

    public void editBasic(int id, String name, String email) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        Person p = people.get(i);
        p.setName(name);
        p.setEmail(email);
        people.set(i, p);
    }

    public void editStudent(int id, int roll, String program) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        Person p = people.get(i);
        if (p instanceof Student) {
            ((Student) p).setRoll(roll);
            ((Student) p).setProgram(program);
        } else {
            throw new ValidationException("not student");
        }
    }

    public void editTeacher(int id, String subject) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        Person p = people.get(i);
        if (p instanceof Teacher) {
            ((Teacher) p).setSubject(subject);
        } else {
            throw new ValidationException("not teacher");
        }
    }

    public void remove(int id) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        people.removeAt(i);
    }

    public Person get(int id) {
        int i = findIndex(id);
        if (i == -1) throw new NotFoundException("not found");
        return people.get(i);
    }

    public void list() {
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).display());
        }
    }

    public void sortById() {
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                if (people.get(i).getId() > people.get(j).getId()) {
                    Person a = people.get(i);
                    people.set(i, people.get(j));
                    people.set(j, a);
                }
            }
        }
    }

    public void sortByName() {
        for (int i = 0; i < people.size(); i++) {
            for (int j = i + 1; j < people.size(); j++) {
                if (people.get(i).getName().compareToIgnoreCase(people.get(j).getName()) > 0) {
                    Person a = people.get(i);
                    people.set(i, people.get(j));
                    people.set(j, a);
                }
            }
        }
    }

    public void searchByName(String q) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getName().toLowerCase().contains(q.toLowerCase())) {
                System.out.println(people.get(i).display());
            }
        }
    }

    public int findIndex(int id) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == id) return i;
        }
        return -1;
    }

    public SimpleList<Person> data() {
        return people;
    }

    public void seedDemo() {
        if (people.size() == 0) {
            add(new Student(1, "Riya", "r@x.com", 101, "BCA"));
            add(new Teacher(2, "Arun", "a@x.com", "Java"));
        }
    }

    public static Person makePersonFromInput(Scanner sc) {
        System.out.print("1 Student 2 Teacher: ");
        int t = sc.nextInt();
        sc.nextLine();
        if (t == 1) {
            System.out.print("id: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("name: ");
            String n = sc.nextLine();
            System.out.print("email: ");
            String e = sc.nextLine();
            System.out.print("roll: ");
            int r = sc.nextInt();
            sc.nextLine();
            System.out.print("program: ");
            String p = sc.nextLine();
            return new Student(id, n, e, r, p);
        } else {
            System.out.print("id: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("name: ");
            String n = sc.nextLine();
            System.out.print("email: ");
            String e = sc.nextLine();
            System.out.print("subject: ");
            String s = sc.nextLine();
            return new Teacher(id, n, e, s);
        }
    }
}