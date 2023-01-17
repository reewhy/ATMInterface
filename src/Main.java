import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args){
        Settings.getSettings();
        ATMInterface a = new ATMInterface();
        System.out.println("Avvio... ");
        File config = new File("config.json");
        try{
            if(config.createNewFile()){
                System.out.println("Config file created!");
            }
            JSONObject obj = new JSONObject();
            obj.put("dataname", "bank");
            obj.put("name", "root");
            obj.put("pass", "Luca");
            obj.put("table", "Accounts");
            FileWriter fw = new FileWriter("config.json");
            fw.write(obj.toJSONString());
            fw.flush();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
