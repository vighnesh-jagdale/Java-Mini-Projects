package dao;

import model.Student;
import util.DBConnection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class StudentDAO{

    public void addStudent(Student student){
        String query = "INSERT INTO students (name,email,course) VALUES (?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){

                ps.setString(1, student.getName());
                ps.setString(2, student.getEmail());
                ps.setString(3, student.getCourse());
                
                ps.executeUpdate();
                System.out.println("Student Added Successfully");
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    public List<Student> getAllStudents(){
        List<Student> list = new ArrayList<>();
        String query = "SELECT * FROM students";

        try(Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query)){

            while(rs.next()){
                Student s = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course")
                );
                list.add(s);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public void deleteStudent(int deleteId){
        String query = "DELETE FROM students WHERE id = ?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query)){

                ps.setInt(1, deleteId);
                ps.executeUpdate();
                System.out.println("Student Deleted Successfully");
            }catch (SQLException e){
                e.printStackTrace();
            }
    }

    public boolean existsById(int id) {

        String sql = "SELECT 1 FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();   // true if record exists
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateStudents(int updateId, String name, String email, String course){
        
        if (!existsById(updateId)) {
            System.out.println("❌ Student with ID " + updateId + " does not exist.");
            return;
        }
        String query = "UPDATE students SET name = ?, email = ?, course = ? WHERE id = ?";

        try(Connection con = DBConnection.getConnection();
        PreparedStatement ps = con.prepareStatement(query)){

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.setInt(4, updateId);

            ps.executeUpdate();
            System.out.println("Student Updated Successfully");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}