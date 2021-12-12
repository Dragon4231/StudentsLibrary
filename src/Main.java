import Database.DataBase;
import Persons.Student;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Student student = new Student();
        Map<Integer,Student> allStudents = new HashMap<Integer,Student>();
        allStudents = student.fillListOfStudents();
        for (Map.Entry entry : allStudents.entrySet()) {
            System.out.println(entry.getValue()+" "+ entry.getKey());
        }
    }
}
