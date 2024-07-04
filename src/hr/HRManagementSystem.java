package hr;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

public class HRManagementSystem {

    private static HashMap<String, Employee> employees = DataStorage.loadData();
    private static Scanner scanner = new Scanner(System.in);
    private static final String Admin_Username = "Melek";
    private static final String Admin_Password = "Melek123";
    private static AdminService adminService = new AdminService(employees, scanner);

    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> DataStorage.saveData(employees)));

        while (true) {
            System.out.println("1-Yeni Kullanıcı Oluştur : ");
            System.out.println("2-Kullanıcı Girişi : ");
            System.out.println("3-Yönetici Girişi : ");
            System.out.println("4-Çıkış : ");
            System.out.println("seçiminizi griiniz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();



            switch (choice) {
                case 1:
                    createUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    loginAdmin();
                    break;
                case 4:
                    System.out.println("Çıkış yapılıyor.");
                    return;
                default:
                    System.out.println("Geçersz İşlem.");
            }
        }
    }
    private static void createUser() {
        System.out.println("İsim: ");
        String firstName = scanner.nextLine();
        System.out.println("Soyisim : ");
        String lastName = scanner.nextLine();
        System.out.println("Departman : ");
        String department = scanner.nextLine();
        System.out.println("Telefon numarası : ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Şifre Oluştur : ");
        String password = scanner.nextLine();

        String key = firstName+ " " + lastName;
        if(employees.containsKey(key)) {
            System.out.println("Bu kullanıcı zaten mevcut.");
        }
        else{
            Employee employee = new Employee(firstName, lastName, department, phoneNumber, password);
            employees.put(key, employee);
            System.out.println("Kullanıcı oluşturuldu.");
        }
    }

    private static void loginUser() {
        System.out.println("İsim: ");
        String firstName = scanner.nextLine();
        System.out.println("Soyisim : ");
        String lastName = scanner.nextLine();
        System.out.println("Şifre: ");
        String password = scanner.nextLine();

        String key = firstName+ " " + lastName;
        if(employees.containsKey(key)&& employees.get(key).getPassword().equals(password)) {
            System.out.println("Giriş Başarılı.");
            showEmployeeMenu(employees.get(key));
        }
        else{
            System.out.println("Geçersiz kullanıcı adı veya şifre.");
        }
    }

    private static void loginAdmin() {
        System.out.println("Yönetici Kullanıcı Adı: ");
        String username = scanner.nextLine();
        System.out.println("Yönetici Şifresi : ");
        String password = scanner.nextLine();

        if(username.equals(Admin_Username) && password.equals(Admin_Password)) {
            System.out.println("Yönetici Girişi Başarılı.");
            showAdminMenu();
        }
        else{
            System.out.println("Geçersiz yönetici adı veya şifre.");
        }
    }

    private static void showEmployeeMenu(Employee employee) {
        while(true){
            System.out.println("1- İzin almak istediğin Günleri Gir:");
            System.out.println("2- Çalışan Bilgilerini Gör");
            System.out.println("3- Çıkış yap");
            System.out.println("Seçiminizi giriniz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    requestLeave(employee);
                    break;
                case 2:
                    viewEmployeeDetails(employee);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Geçersiz İşlem.");

            }

        }
    }

    private static void showAdminMenu() {
        while(true){
            System.out.println("1- Tüm çalışanları Listele");
            System.out.println("2- Çalışan Sil");
            System.out.println("3- Çıkış Yap");
            System.out.println("Seçinizi giriniz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminService.listAllEmployees();
                    break;
                case 2:
                    adminService.deleteEmployee();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Geçersiz Seçim.");
            }
        }
    }

    private static void requestLeave(Employee employee) {
        System.out.println("Kaç gün izin almak istersiniz:");
        double leaveDays = scanner.nextDouble();
        scanner.nextLine();

        if(leaveDays >= employee.getRemainingLeaveDays()) {
            System.out.println("Maalesef yeterli izin gününüz yok. Kalan izin günleriniz:" + employee.getRemainingLeaveDays());
        }
        else{
            employee.setRemainingLeavedays(employee.getRemainingLeaveDays()- leaveDays);
            System.out.println("İzin günleriniz güncellendi. Kalan izin günleriniz:" + employee.getRemainingLeaveDays());
        }
    }

    private static void viewEmployeeDetails(Employee employee) {
        System.out.println("Ad: " + employee.getFirstName());
        System.out.println("Soyad: " + employee.getLastName());
        System.out.println("Departman: " + employee.getDepartment());
        System.out.println("Telefon Numarası: " + employee.getPhoneNumber());
        System.out.println("Kalan İzin Günleri: " + employee.getRemainingLeaveDays());
    }
}
