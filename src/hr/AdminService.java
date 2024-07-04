package hr;

import java.util.HashMap;
import java.util.Scanner;

public class AdminService {

    private HashMap<String,Employee> employees;
    private Scanner scanner;

    public AdminService(HashMap<String,Employee> employees,Scanner scanner) {
        this.employees=employees;
        this.scanner=scanner;
    }

    public void listAllEmployees() {
        for(Employee employee:employees.values()) {
            System.out.println("İsim:" + employee.getFirstName()+", Soyisim:"+employee.getLastName()+ ", Telefon:"+employee.getPhoneNumber()+ ", Kalan İzin Günleri:"+employee.getRemainingLeaveDays());
        }
    }

    public void deleteEmployee(){
        System.out.println("Silinecek Çalışanın Adı:");
        String firstName=scanner.nextLine();
        System.out.println("Silinecek Çalışanın Soyadı:");
        String lastName=scanner.nextLine();
        String key = firstName+ " "+ lastName;

        if(employees.containsKey(key)) {
            employees.remove(key);
            System.out.println("Çalışan Silindi.");
        }
        else{
            System.out.println("Çalışan Bulunamadı.");
        }
    }
}
