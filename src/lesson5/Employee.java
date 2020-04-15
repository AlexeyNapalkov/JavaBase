package lesson5;

import java.util.Scanner;

public class Employee {
    public String name; //ФИО
    public String position; //Должность
    public String email;
    public String phone;
    public int salary;
    public int age;

    public Employee(String name, String position, String email, String phone, int salary, int age){
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age =age;
    }

    public Employee(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ФИО:");
        this.name = scan.nextLine();
        System.out.print("Введите должность:");
        this.position = scan.nextLine();
        System.out.print("Введите e-mail:");
        this.email = scan.nextLine();
        System.out.print("Введите телефон:");
        this.phone = scan.nextLine();
        System.out.print("Введите оклад:");
        this.salary = scan.nextInt();
        System.out.print("Введите возраст:");
        this.age = scan.nextInt();
    }
    public void printInfo(){
        System.out.println("-------------------------------------");
        System.out.println("ФИО........ " + name);
        System.out.println("должность.. " + position);
        System.out.println("E-mail..... " + email);
        System.out.println("телефон.... " + phone);
        System.out.println("оклад...... " + salary);
        System.out.println("возраст.... " + age);
        System.out.println("-------------------------------------");
    }
}
