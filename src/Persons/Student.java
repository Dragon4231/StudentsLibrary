package Persons;

import Database.DataBase;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Student {
    int id, course, age, scholarship;
    double score;
    String name, faculty;
    DataBase dataBase = new DataBase();
    Connection con = dataBase.getDbConnection();

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", course=" + course +
                ", age=" + age +
                ", scholarship=" + scholarship +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", faculty='" + faculty;
    }

    public Student(Integer id, String name, Integer age, String faculty, Integer course, int scholarship, double score) throws Exception {
        if( id < 0 || age < 0 || course < 1 || course > 6 || scholarship > 1 || scholarship < 0 ) throw new Exception("error value");
        this.id = id;
        this.name = name;
        this.age = age;
        this.faculty = faculty;
        this.course = course;
        this.scholarship = scholarship;
        this.score = score;
    }

    public Student() throws SQLException, ClassNotFoundException {

    }

    public void createStudent(Integer stdid, String namest, Integer agest, String facultyst, Integer coursest, int scholarshipst, double scorest) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO student (id, name, age, faculty, course, scholarship, score) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, stdid);
        preparedStatement.setString(2, namest);
        preparedStatement.setInt(3, agest);
        preparedStatement.setString(4, facultyst);
        preparedStatement.setInt(5, coursest);
        preparedStatement.setInt(6, scholarshipst);
        preparedStatement.setDouble(7, scorest);
        preparedStatement.executeUpdate();
    }

    public Map<Integer, Student> fillListOfStudents() throws SQLException, ClassNotFoundException {
        Map<Integer, Student> allStudents = new HashMap<Integer, Student>();
        Statement statement = con.createStatement();
        String sql_send = "select * from student order by id";
        ResultSet resultSet = statement.executeQuery(sql_send);
        while (resultSet.next()) {
            try{
                Student st = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("faculty"), resultSet.getInt("course"), resultSet.getInt("scholarship"), resultSet.getDouble("score"));
                allStudents.put(resultSet.getInt("id"), st);
            }catch (Exception e){

            }

        }
        return allStudents;
    }

    public Student getStudent(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM student WHERE id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Student student = new Student();
        try{
            Student st = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"), resultSet.getString("faculty"), resultSet.getInt("course"), resultSet.getInt("scholarship"), resultSet.getDouble("score"));
            return st;
        }catch (Exception e){

        }

        return student;
    }

    public void writeStudentInBD(Student student) throws SQLException {
        String sqlsend = "INSERT INTO student (id, name, age, faculty, course, scholarship, score) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sqlsend);
        preparedStatement.setInt(1, student.id);
        preparedStatement.setString(2, student.name);
        preparedStatement.setInt(3, student.age);
        preparedStatement.setString(4, student.faculty);
        preparedStatement.setInt(5, student.course);
        preparedStatement.setInt(6, student.scholarship);
        preparedStatement.setDouble(7, student.score);
        preparedStatement.executeUpdate();
    }

    public void deleteStudentFromBD(int id) throws SQLException {
        String sql = "DELETE from student where id = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }

    public void updateInfoInBD(String action,String meaning, int id) throws Exception {
        String sql = "";
        if( action.equals("name") ){
            sql = "update student set name = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,meaning);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
        else if( action.equals("age") ){
            sql = "update student set age = ? where id = ?";
            int i = Integer.parseInt(meaning);
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,i);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
        else if( action.equals("faculty") ){
            sql = "update student set faculty = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,meaning);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
        else if( action.equals("course") ){
            sql = "update student set course = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int i = Integer.parseInt(meaning);
            preparedStatement.setInt(1,i);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
        else if( action.equals("scholarship") ){
            sql = "update student set scholarship = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            int i = Integer.parseInt(meaning);
            preparedStatement.setInt(1,i);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }
        else if( action.equals("score") ){
            sql = "update student set score = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            double i = Double.parseDouble(meaning);
            preparedStatement.setDouble(1,i);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        }else{
            Exception exception;
            throw new Exception("error");
        }
    }

}
