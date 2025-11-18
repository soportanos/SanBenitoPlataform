package org.example;

//Eliminar Import
import com.mongodb.client.MongoDatabase;
//
import org.example.dao.core.ConnectionCore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends ConnectionCore {
    public static void main(String[] args) {
        //Test Connection MONGODB
        try {
            Main main = new Main();
            MongoDatabase db = main.getConnection();
            System.out.println("Conectado a: " + db.getName());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}