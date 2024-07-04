package hr;

import java.io.*;
import java.util.HashMap;

public class DataStorage {
    private static final String FILE_NAME = "employees.dat";

    public static void saveData(HashMap<String, Employee> employees){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(employees);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<String, Employee> loadData(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))){
            return (HashMap<String, Employee>) ois.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

}
