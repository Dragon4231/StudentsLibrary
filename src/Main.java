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
        while(true){
            System.out.println("========================");
            System.out.println("Консольное меню.");
            System.out.println("Введите 0 для выхода из приложения.");
            System.out.println("Введите 1 для добавления студента.");
            System.out.println("Введите 2 для изменения информации о студенте.");
            System.out.println("========================");
            int action = scanner.nextInt();
            clearScreen();
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
                case 4:
            }
        }
    }
    public static void clearScreen() {
        for(int i = 0; i < 60; i++){
            System.out.println();
        }
    }
}
