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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", course=" + course +
                ", age=" + age +
                ", scholarship=" + scholarship +
                ", score=" + score +
                ", name='" + name + '\'' +
                ", faculty='" + faculty ;
    }

    public Student(Integer id, String name, Integer age, String faculty, Integer course, int scholarship, double score) throws SQLException, ClassNotFoundException {
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
        preparedStatement.setInt(1,stdid);
        preparedStatement.setString(2,namest);
        preparedStatement.setInt(3,agest);
        preparedStatement.setString(4,facultyst);
        preparedStatement.setInt(5,coursest);
        preparedStatement.setInt(6,scholarshipst);
        preparedStatement.setDouble(7,scorest);
        preparedStatement.executeUpdate();
    }

    public Map<Integer,Student> fillListOfStudents() throws SQLException, ClassNotFoundException {
        Map<Integer,Student> allStudents = new HashMap<Integer,Student>();
        Statement statement = con.createStatement();
        String sql_send = "select * from student order by id";
        ResultSet resultSet = statement.executeQuery(sql_send);
        while(resultSet.next()){
            Student st = new Student(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("faculty"),resultSet.getInt("course"),resultSet.getInt("scholarship"),resultSet.getDouble("score"));
            allStudents.put(resultSet.getInt("id"),st);
        }
        return allStudents;
    }
}
