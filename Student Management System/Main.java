import java.util.Scanner;
import model.Student;
import dao.StudentDAO;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println();
            System.out.println("----- STUDENT MANAGEMENT SYSTEM -----");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Exit");
            System.out.println();

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice. Enter number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();
                    if (!email.matches("^[A-Za-z+_.-]+@[A-Za-z.-]+$")) {
                        System.out.println("Invalid email format.");
                        break;
                    }

                    System.out.print("Course: ");
                    String course = sc.nextLine().trim();

                    dao.addStudent(new Student(name, email, course));
                    break;

                case 2:
                    List<Student> students = dao.getAllStudents();
                    students.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("ID must be a number.");
                        sc.nextLine();
                        break;
                    }

                    int deleteId = sc.nextInt();
                    sc.nextLine();

                    if (deleteId <= 0) {
                        System.out.println("ID must be positive.");
                        break;
                    }

                    if (!dao.existsById(deleteId)) {
                        System.out.println("Student not found.");
                        break;
                    }

                    dao.deleteStudent(deleteId);
                    break;

                case 4:
                    System.out.print("Enter ID to update: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("ID must be a number.");
                        sc.nextLine();
                        break;
                    }

                    int updateId = sc.nextInt();
                    sc.nextLine();

                    if (updateId <= 0) {
                        System.out.println("ID must be positive.");
                        break;
                    }

                    if (!dao.existsById(updateId)) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("New Name: ");
                    String updateName = sc.nextLine().trim();
                    if (updateName.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }

                    System.out.print("New Email: ");
                    String updateEmail = sc.nextLine().trim();

                    System.out.print("New Course: ");
                    String updateCourse = sc.nextLine().trim();

                    dao.updateStudents(updateId, updateName, updateEmail, updateCourse);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}