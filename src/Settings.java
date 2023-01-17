
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class Settings {
    static String dataname;
    static String name;
    static String pass;
    static String table;
    public static void setSettings(String fdataname, String fname, String fpass, String ftable){
        Settings.dataname = fdataname;
        Settings.name = fname;
        Settings.pass = fpass;
        Settings.table = ftable;
    }

    public static void getSettings(){
        JSONParser json = new JSONParser();
        try(FileReader io = new FileReader("config.json")){
            Object obj = json.parse(io);
            JSONObject jo = (JSONObject) obj;
            setSettings((String) jo.get("dataname"), (String) jo.get("name"), (String) jo.get("pass"), (String) jo.get("table"));
        }catch(Exception ex){
            ex.printStackTrace();
        }
        Account.link = "jdbc:mysql://localhost:3306/" + dataname + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        Account.pass = pass;
        Account.username = name;
        Account.table = table;
    }

    public static void applySettings(){
        JSONObject obj = new JSONObject();
        obj.put("dataname", dataname);
        obj.put("name", name);
        obj.put("pass", pass);
        obj.put("table", table);
        try(FileWriter fw = new FileWriter("config.json")){
            fw.write(obj.toJSONString());
            fw.flush();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        Account.link = "jdbc:mysql://localhost:3306/" + dataname + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
        Account.pass = pass;
        Account.username = name;
        Account.table = table;
    }
}
