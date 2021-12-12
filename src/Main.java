import Database.DataBase;
import Persons.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Требуется авторизация для дальнеших действий(Введите 0 для выхода).");
        String sign;
        sign = scanner.nextLine(); // password = iwanttexas
        Student studentIn = new Student();
        studentIn = studentIn.getStudent(0);
        if((sign.hashCode()*33-555) == studentIn.age()){
            System.out.println("Вход успешно выполнен");
        }else{
            System.out.println("Неверный пароль");
            System.exit(0);
        };
        while(true){
            System.out.println("========================");
            System.out.println("Консольное меню.");
            System.out.println("Введите 0 для выхода из приложения.");
            System.out.println("Введите 1 для добавления студента.");
            System.out.println("Введите 2 для изменения информации о студенте.");
            System.out.println("Введите 3 для вывода информации о студентах.");
            System.out.println("Введите 4 для вывода информации о студенте по студенческому.");
            System.out.println("Введите 5 для удаления информации о студенте по студенческому.");
            System.out.println("========================");
            int action = scanner.nextInt();
            switch (action){
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Введите № студенческого билета студента:");
                    int id = scanner.nextInt();
                    System.out.println("Введите имя студента:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.println("Введите возраст студента:");
                    int age = scanner.nextInt();
                    System.out.println("Введите факультет студента:");
                    scanner.nextLine();
                    String faculty = scanner.nextLine();
                    System.out.println("Введите курс студента:");
                    int course = scanner.nextInt();
                    System.out.println("Введите 0,если у студента нет стипендии, введите 1, если у студента есть стипендия:");
                    int scholarship = scanner.nextInt();
                    System.out.println("Введите средний балл студента:");
                    double score = scanner.nextDouble();
                    try{
                        Student student = new Student(id,name,age,faculty,course,scholarship,score);
                        student.writeStudentInBD(student);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        System.exit(0);
                    }
                    break;
                case 2:
                    System.out.println("Введите номер студенческого студента:");
                    int find = scanner.nextInt();
                    System.out.println("Введите значение, которое хотите изменить:");
                    System.out.println("name/age/faculty/course/scholarship/score");
                    scanner.nextLine();
                    String act = scanner.nextLine();
                    System.out.println("Введите значение на которое хотите изменить.");
                    String mean = scanner.nextLine();
                    Student student = new Student();
                    student.updateInfoInBD(act,mean,find);
                    break;
                case 3:
                    Student student1 = new Student();
                    student1.writeAllStudents();
                    break;
                case 4:
                    System.out.println("Введите номер студенческого.");
                    int number = scanner.nextInt();
                    if(number == 0){
                        System.out.println("error id");
                        System.exit(0);
                    }
                    Student student2 = new Student();
                    student2 = student2.getStudent(number);
                    if(student2.getId() == 0) throw new Exception("error, id not found");
                    System.out.println(student2);
                    break;
                case 5:
                    System.out.println("Введите номер студенческого для удаления.");
                    int temp = scanner.nextInt();
                    Student student3 = new Student();
                    student3.deleteStudent(temp);
                    break;
            }
        }
    }
}
