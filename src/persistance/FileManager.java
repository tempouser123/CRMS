package persistance;

import model.Course;
import model.Person;
import model.Student;
import model.Teacher;
import services.SimpleList;

import java.io.FileWriter;

public class FileManager {
    public static void exportPeopleCSV(SimpleList<Person> people, String file) {
        try {
            FileWriter w = new FileWriter(file);
            w.write("id,name,email,type,extra\n");
            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                if (p instanceof Student) {
                    Student s = (Student) p;
                    w.write(p.getId() + "," + p.getName() + "," + p.getEmail() + ",Student," + s.getRoll() + "-" + s.getProgram() + "\n");
                } else if (p instanceof Teacher) {
                    Teacher t = (Teacher) p;
                    w.write(p.getId() + "," + p.getName() + "," + p.getEmail() + ",Teacher," + t.getSubject() + "\n");
                } else {
                    w.write(p.getId() + "," + p.getName() + "," + p.getEmail() + ",Person,\n");
                }
            }
            w.close();
        } catch (Exception e) {
            System.out.println("csv fail");
        }
    }

    public static void exportCoursesCSV(SimpleList<Course> courses, String file) {
        try {
            FileWriter w = new FileWriter(file);
            w.write("id,title,credits\n");
            for (int i = 0; i < courses.size(); i++) {
                Course c = courses.get(i);
                w.write(c.getId() + "," + c.getTitle() + "," + c.getCredits() + "\n");
            }
            w.close();
        } catch (Exception e) {
            System.out.println("csv fail");
        }
    }
}