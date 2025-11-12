package ui;

import model.*;
import persistance.FileManager;
import persistance.ObjectStore;
import services.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuSystem {
    private final Scanner sc = new Scanner(System.in);
    private final PersonService personService = new PersonService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final LoginService loginService = new LoginService();

    public void start() {
        personService.seedDemo();
        courseService.seedDemo();
        tryLogin();
        int ch = -1;
        while (ch != 0) {
            System.out.println();
            System.out.println("1 People");
            System.out.println("2 Courses");
            System.out.println("3 Enrollments");
            System.out.println("4 Reports");
            System.out.println("5 Save");
            System.out.println("6 Load");
            System.out.println("7 Export CSV");
            System.out.println("8 Change Password");
            System.out.println("0 Exit");
            System.out.print("Choice: ");
            ch = readInt();
            if (ch == 1) peopleMenu();
            else if (ch == 2) courseMenu();
            else if (ch == 3) enrollMenu();
            else if (ch == 4) reportMenu();
            else if (ch == 5) saveAll();
            else if (ch == 6) loadAll();
            else if (ch == 7) exportCSV();
            else if (ch == 8) changePass();
            else if (ch == 0) System.out.println("Bye");
        }
    }

    private void tryLogin() {
        int tries = 3;
        while (tries > 0) {
            System.out.print("User: ");
            String u = sc.nextLine();
            System.out.print("Pass: ");
            String p = sc.nextLine();
            if (loginService.authenticate(u, p)) return;
            tries--;
            System.out.println("Wrong. Left: " + tries);
        }
        System.exit(0);
    }

    private void peopleMenu() {
        int ch = -1;
        while (ch != 0) {
            System.out.println();
            System.out.println("1 Add");
            System.out.println("2 List");
            System.out.println("3 Edit Basic");
            System.out.println("4 Edit Student");
            System.out.println("5 Edit Teacher");
            System.out.println("6 Delete");
            System.out.println("7 Sort By Id");
            System.out.println("8 Sort By Name");
            System.out.println("9 Search By Name");
            System.out.println("0 Back");
            System.out.print("Choice: ");
            ch = readInt();
            try {
                if (ch == 1) {
                    Person p = PersonService.makePersonFromInput(sc);
                    if (p instanceof Student) personService.add((Student) p);
                    else personService.add((Teacher) p);
                } else if (ch == 2) {
                    personService.list();
                } else if (ch == 3) {
                    System.out.print("id: ");
                    int id = readInt();
                    System.out.print("name: ");
                    String n = sc.nextLine();
                    System.out.print("email: ");
                    String e = sc.nextLine();
                    personService.editBasic(id, n, e);
                } else if (ch == 4) {
                    System.out.print("id: ");
                    int id = readInt();
                    System.out.print("roll: ");
                    int r = readInt();
                    System.out.print("program: ");
                    String pg = sc.nextLine();
                    personService.editStudent(id, r, pg);
                } else if (ch == 5) {
                    System.out.print("id: ");
                    int id = readInt();
                    System.out.print("subject: ");
                    String s = sc.nextLine();
                    personService.editTeacher(id, s);
                } else if (ch == 6) {
                    System.out.print("id: ");
                    int id = readInt();
                    personService.remove(id);
                } else if (ch == 7) {
                    personService.sortById();
                } else if (ch == 8) {
                    personService.sortByName();
                } else if (ch == 9) {
                    System.out.print("query: ");
                    String q = sc.nextLine();
                    personService.searchByName(q);
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void courseMenu() {
        int ch = -1;
        while (ch != 0) {
            System.out.println();
            System.out.println("1 Add");
            System.out.println("2 List");
            System.out.println("3 Edit");
            System.out.println("4 Delete");
            System.out.println("5 Sort By Id");
            System.out.println("6 Sort By Title");
            System.out.println("7 Search By Title");
            System.out.println("0 Back");
            System.out.print("Choice: ");
            ch = readInt();
            try {
                if (ch == 1) {
                    System.out.print("id: ");
                    int id = readInt();
                    System.out.print("title: ");
                    String t = sc.nextLine();
                    System.out.print("credits: ");
                    int c = readInt();
                    courseService.add(new Course(id, t, c));
                } else if (ch == 2) {
                    courseService.list();
                } else if (ch == 3) {
                    System.out.print("id: ");
                    int id = readInt();
                    System.out.print("title: ");
                    String t = sc.nextLine();
                    System.out.print("credits: ");
                    int c = readInt();
                    courseService.edit(id, t, c);
                } else if (ch == 4) {
                    System.out.print("id: ");
                    int id = readInt();
                    courseService.remove(id);
                } else if (ch == 5) {
                    courseService.sortById();
                } else if (ch == 6) {
                    courseService.sortByTitle();
                } else if (ch == 7) {
                    System.out.print("query: ");
                    String q = sc.nextLine();
                    courseService.searchByTitle(q);
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void enrollMenu() {
        int ch = -1;
        while (ch != 0) {
            System.out.println();
            System.out.println("1 Enroll");
            System.out.println("2 Drop");
            System.out.println("3 List All");
            System.out.println("4 List By Student");
            System.out.println("5 List By Course");
            System.out.println("0 Back");
            System.out.print("Choice: ");
            ch = readInt();
            try {
                if (ch == 1) {
                    System.out.print("student id: ");
                    int sid = readInt();
                    System.out.print("course id: ");
                    int cid = readInt();
                    Person p = personService.get(sid);
                    if (!(p instanceof Student)) throw new ValidationException("need student");
                    courseService.get(cid);
                    enrollmentService.enroll(sid, cid);
                } else if (ch == 2) {
                    System.out.print("student id: ");
                    int sid = readInt();
                    System.out.print("course id: ");
                    int cid = readInt();
                    enrollmentService.drop(sid, cid);
                } else if (ch == 3) {
                    enrollmentService.list();
                } else if (ch == 4) {
                    System.out.print("student id: ");
                    int sid = readInt();
                    enrollmentService.listByStudent(sid);
                } else if (ch == 5) {
                    System.out.print("course id: ");
                    int cid = readInt();
                    enrollmentService.listByCourse(cid);
                }
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private void reportMenu() {
        int ch = -1;
        while (ch != 0) {
            System.out.println();
            System.out.println("1 Count Students");
            System.out.println("2 Count Teachers");
            System.out.println("3 Courses Without Students");
            System.out.println("4 Students Without Courses");
            System.out.println("0 Back");
            System.out.print("Choice: ");
            ch = readInt();
            if (ch == 1) {
                int count = 0;
                for (int i = 0; i < personService.data().size(); i++) {
                    if (personService.data().get(i) instanceof Student) count++;
                }
                System.out.println(count);
            } else if (ch == 2) {
                int count = 0;
                for (int i = 0; i < personService.data().size(); i++) {
                    if (personService.data().get(i) instanceof Teacher) count++;
                }
                System.out.println(count);
            } else if (ch == 3) {
                for (int i = 0; i < courseService.data().size(); i++) {
                    int cid = courseService.data().get(i).getId();
                    boolean any = false;
                    for (int j = 0; j < enrollmentService.data().size(); j++) {
                        if (enrollmentService.data().get(j).getCourseId() == cid) {
                            any = true;
                            break;
                        }
                    }
                    if (!any) System.out.println(courseService.data().get(i).display());
                }
            } else if (ch == 4) {
                for (int i = 0; i < personService.data().size(); i++) {
                    if (!(personService.data().get(i) instanceof Student)) continue;
                    int sid = personService.data().get(i).getId();
                    boolean any = false;
                    for (int j = 0; j < enrollmentService.data().size(); j++) {
                        if (enrollmentService.data().get(j).getStudentId() == sid) {
                            any = true;
                            break;
                        }
                    }
                    if (!any) System.out.println(personService.data().get(i).display());
                }
            }
        }
    }

    private void saveAll() {
        ObjectStore.save("people.dat", personService.data());
        ObjectStore.save("courses.dat", courseService.data());
        ObjectStore.save("enrollments.dat", enrollmentService.data());
        System.out.println("saved");
    }

    private void loadAll() {
        ObjectStore.load("people.dat", personService.data());
        ObjectStore.load("courses.dat", courseService.data());
        ObjectStore.load("enrollments.dat", enrollmentService.data());
        System.out.println("loaded");
    }

    private void exportCSV() {
        FileManager.exportPeopleCSV(personService.data(), "people.csv");
        FileManager.exportCoursesCSV(courseService.data(), "courses.csv");
        System.out.println("exported");
    }

    private void changePass() {
        System.out.print("old: ");
        String o = sc.nextLine();
        System.out.print("new: ");
        String n = sc.nextLine();
        try {
            loginService.changePassword(o, n);
            System.out.println("ok");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private int readInt() {
        try {
            int x = sc.nextInt();
            sc.nextLine();
            return x;
        } catch (InputMismatchException e) {
            sc.nextLine();
            return -1;
        }
    }
}
