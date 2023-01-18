
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.desktop.SystemEventListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.Date;

import static java.lang.String.valueOf;

public class Account {
    public static String link = "jdbc:mysql://localhost:3306/bank?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    public static String username = "root";
    public static String pass = "Luca";
    public static String table = "accounts";
    String name;
    String lastName;
    Date date;
    String city;
    int balance;
    int pin;
    String tag;
    boolean admin;
    String email;
    String phone;
    int code;
    boolean enabled;
    public Account(String name, String lastName, java.sql.Date date, String city, int balance, int pin, String tag, boolean admin, String email, String phone, int code, boolean enabled){
        this.name = name;
        this.lastName = lastName;
        this.date = date;
        this.city = city;
        this.balance = balance;
        this.pin = pin;
        this.tag = tag;
        this.admin = admin;
        this.email = email;
        this.phone = phone;
        this.code = code;
        this.enabled = enabled;
    }

    public int createAccount(){
        System.out.println("Creating account...");
        int pin = 1001;
        try(Connection conn = DriverManager.getConnection(link, username, pass);
            Statement stmt = conn.createStatement()){
            this.tag = TagGenerator.generateTag(name, lastName, city, date);
            System.out.println("Tag generated: " + this.tag);
            boolean e = true;
            while(e){
                int cPin = TagGenerator.randInt(1000, 9999);
                System.out.println("Pin generated: " + cPin);
                String strSelect = "SELECT pin from " + table;
                ResultSet rset = stmt.executeQuery(strSelect);
                while(rset.next()){
                    int pinq = rset.getInt("pin");
                    if(pinq != cPin){
                        e = false;
                        pin = cPin;
                    }
                }
            }
            this.balance = 1000;
            int confirmPin = TagGenerator.randInt(1000, 9999);
            System.out.println("Verification pin generated: " + confirmPin);
            String strInsert = "insert into " + table + " values('" + this.name + "', '" + this.lastName + "', '" + this.city + "', DATE('" + this.date + "'), 1000, " + pin + ", '" + this.tag + "', " + false + ", '" + this.email + "', '" + this.phone + "', " + confirmPin + ", false)";
            System.out.println(strInsert);
            stmt.executeUpdate(strInsert);
            sendVerification(confirmPin);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        this.pin = pin;
        return pin;
    }

    public void sendVerification(int confirmPin){
        System.out.println("Sending a verification mail...");
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String from = "lucasmegaX0501@gmail.com";
        String pass = "hwlzvzcueoxsxjpw";

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.email));
            message.setSubject("Confirm PIN");
            message.setContent("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://designer.igniteui.com/packages/bootstrap/bootstrap.min.css\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://designer.igniteui.com/css/themes/infragistics/infragistics.theme.css\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://designer.igniteui.com/css/structure/infragistics.css\">\n" +
                    "<!--\n" +
                    "Update the Ignite UI script references to your licensed copies before deploying.\n" +
                    "Ignite UI License: https://www.infragistics.com/legal/license/ultimate/\n" +
                    "-->\n" +
                    "<script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>\n" +
                    "<script src=\"https://code.jquery.com/ui/1.10.4/jquery-ui.min.js\"></script>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.min.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.core.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.lob.js\"></script>\n" +
                    "<script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>\n" +
                    "<script src=\"https://code.jquery.com/ui/1.10.4/jquery-ui.min.js\"></script>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.min.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.core.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.lob.js\"></script>\n" +
                    "<!-- You may remove the datasources.js script if you are not using Designer sample data. -->\n" +
                    "<script src=\"https://designer.igniteui.com/js/datasources.js\"></script>\n" +
                    "<style>\n" +
                    "    .rectangle {\n" +
                    "        background-color: black;\n" +
                    "        height: 100px;\n" +
                    "        widht: 100px;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"rectangle\"></div>\n" +
                    "<div id=\"row1\" class=\"row\">\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "<h1 id=\"heading1\" style=\"text-align: center; font-weight: bold\">Confirm PIN</h1>\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div id=\"row3\" class=\"row\">\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "<h1 id=\"heading2\" style=\"text-align: center\">Your confirm PIN is: "+ confirmPin +"</h1>\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div class=\"rectangle\"></div>\n" +
                    "</body>\n" +
                    "</html>", "text/html");
            Transport.send(message);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static Account getAccount(String name, String pin){
        String aName = "";
        String aLastName = "";
        String aCity = "";
        Date aDate = null;
        int aBalance = -1;
        int aPin = -1;
        String aTag = "";
        boolean aAdmin = false;
        String aEmail = "";
        String aPhone = "";
        int aCode = -1;
        boolean aEnabled = false;
        try(Connection conn = DriverManager.getConnection(Account.link, Account.username, Account.pass);
        Statement stmt = conn.createStatement()){
            String strSelect = "SELECT * FROM " + table + " WHERE name='" + name + "' and pin = " + pin;
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next()){
                aName = rset.getString("name");
                aLastName = rset.getString("lastname");
                aCity = rset.getString("city");
                aDate = rset.getDate("date");
                aBalance = rset.getInt("balance");
                aPin = rset.getInt("pin");
                aTag = rset.getString("tag");
                aAdmin = rset.getBoolean("admin");
                aEmail = rset.getString("email");
                aPhone = rset.getString("phone");
                aCode = rset.getInt("verification_code");
                aEnabled = rset.getBoolean("enabled");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new Account(aName, aLastName, aDate, aCity, aBalance, aPin, aTag, aAdmin, aEmail, aPhone, aCode, aEnabled);
    }

    public static Account getAccount(String tag){
        String aName = "";
        String aLastName = "";
        String aCity = "";
        Date aDate = null;
        int aBalance = -1;
        int aPin = -1;
        String aTag = "";
        boolean aAdmin = false;
        String aEmail = "";
        String aPhone = "";
        int aCode = -1;
        boolean aEnabled = false;
        try(Connection conn = DriverManager.getConnection(Account.link, Account.username, Account.pass);
            Statement stmt = conn.createStatement()){
            String strSelect = "SELECT * FROM " + table +" WHERE tag='" + tag + "'";
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next()){
                aName = rset.getString("name");
                aLastName = rset.getString("lastname");
                aCity = rset.getString("city");
                aDate = rset.getDate("date");
                aBalance = rset.getInt("balance");
                aPin = rset.getInt("pin");
                aTag = rset.getString("tag");
                aAdmin = rset.getBoolean("admin");
                aEmail = rset.getString("email");
                aPhone = rset.getString("phone");
                aCode = rset.getInt("verification_code");
                aEnabled = rset.getBoolean("enabled");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new Account(aName, aLastName, aDate, aCity, aBalance, aPin, aTag, aAdmin, aEmail, aPhone, aCode, aEnabled);
    }

    public Account updateAccount(){
        try(Connection conn = DriverManager.getConnection(link, username, pass);
        Statement stmt = conn.createStatement()) {
            String strInsert = "UPDATE "+ table +" set balance = " + this.balance + ", name = '" + this.name + "', lastname = '" + this.lastName + "', city = '" + this.city + "', date = DATE('" + this.date + "'), tag = '" + this.tag + "', admin = " + this.admin + ", enabled = " + this.enabled + ", email = '" + this.email + "', phone = '" + this.phone +"' where pin = '" + this.pin + "'";
            System.out.println(strInsert);
            stmt.executeUpdate(strInsert);
        }catch(Exception ex){
            ex.printStackTrace();
            return this;
        }
        return null;
    }

    public void resetVerification(){
        try(Connection conn = DriverManager.getConnection(link, username, pass);
        Statement stmt = conn.createStatement()){
            this.code = TagGenerator.randInt(1000, 9999);
            this.enabled = false;
            String strUpdate = "UPDATE " + table + " set verification_code = " + this.code + ", enabled = " + this.enabled + " where tag = '" + this.tag + "'";
            stmt.executeUpdate(strUpdate);
            sendVerification(this.code);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static DefaultTableModel getAllAccounts(){
        String[] columnNames = {"name", "lastname", "city", "date", "balance", "tag", "admin", "email", "phone"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(columnNames);
        try(Connection conn = DriverManager.getConnection(link, username, pass);
        Statement stmt = conn.createStatement()){
            String strSelect = "Select name, lastname, city, date, balance, tag, admin, email, phone from " + table;
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                String aName = rset.getString("name");
                String aLastName = rset.getString("lastname");
                String aCity = rset.getString("city");
                Date aDate = rset.getDate("date");
                String aBalance = Integer.toString(rset.getInt("balance"));
                String aTag = rset.getString("tag");
                boolean aAdmin = rset.getBoolean("admin");
                String aEmail = rset.getString("email");
                String aPhone = rset.getString("phone");

                String[] data = {aName, aLastName, aCity, String.valueOf(aDate), aBalance, aTag, valueOf(aAdmin), aEmail, aPhone};

                tableModel.addRow(data);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return tableModel;
    }

    public static Account[] getAccounts(int pin){
        List<Account> accs = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(link, username, pass);
            Statement stmt = conn.createStatement()){
            String strSelect = "Select tag from " + table + " where not pin = " + pin;
            ResultSet rset = stmt.executeQuery(strSelect);

            while(rset.next()){
                String aTag = rset.getString("tag");

                accs.add(Account.getAccount(aTag));
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return accs.toArray(Account[]::new);
    }

    public int deleteAccount(){
        try(Connection conn = DriverManager.getConnection(link, username, pass);
        Statement stmt = conn.createStatement()){
            String strDelete = "Delete from " + table + " where tag = '" + this.tag + "'";
            stmt.executeUpdate(strDelete);
        }catch (Exception ex){
            ex.printStackTrace();
            return 0;
        }
        return 1;
    }

    public void changePin(int pin){
        try(Connection conn = DriverManager.getConnection(link, username, pass);
            Statement stmt = conn.createStatement()){
            String strUpdate = "update " + table + " set pin = " + pin + " where tag = '" + this.tag + "'";
            stmt.executeUpdate(strUpdate);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int forgotPin(boolean SMS){
        int confirmPin = TagGenerator.randInt(10, 99);
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String from = "lucasmegaX0501@gmail.com";
        String pass = "hwlzvzcueoxsxjpw";

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(this.email));
            message.setSubject("Confirm PIN");
            message.setContent("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://designer.igniteui.com/packages/bootstrap/bootstrap.min.css\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://designer.igniteui.com/css/themes/infragistics/infragistics.theme.css\">\n" +
                    "<link rel=\"stylesheet\" href=\"https://designer.igniteui.com/css/structure/infragistics.css\">\n" +
                    "<!--\n" +
                    "Update the Ignite UI script references to your licensed copies before deploying.\n" +
                    "Ignite UI License: https://www.infragistics.com/legal/license/ultimate/\n" +
                    "-->\n" +
                    "<script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>\n" +
                    "<script src=\"https://code.jquery.com/ui/1.10.4/jquery-ui.min.js\"></script>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.min.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.core.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.lob.js\"></script>\n" +
                    "<script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>\n" +
                    "<script src=\"https://code.jquery.com/ui/1.10.4/jquery-ui.min.js\"></script>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.min.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.core.js\"></script>\n" +
                    "<script src=\"https://designer.igniteui.com/js/infragistics.lob.js\"></script>\n" +
                    "<!-- You may remove the datasources.js script if you are not using Designer sample data. -->\n" +
                    "<script src=\"https://designer.igniteui.com/js/datasources.js\"></script>\n" +
                    "<style>\n" +
                    "    .rectangle {\n" +
                    "        background-color: black;\n" +
                    "        height: 100px;\n" +
                    "        widht: 100px;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"rectangle\"></div>\n" +
                    "<div id=\"row1\" class=\"row\">\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "<h1 id=\"heading1\" style=\"text-align: center; font-weight: bold\">Confirm PIN</h1>\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div id=\"row3\" class=\"row\">\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "<h1 id=\"heading2\" style=\"text-align: center\">Your confirm PIN is: "+ confirmPin +"</h1>\n" +
                    "</div>\n" +
                    "<div class=\"col-md-4\">\n" +
                    "</div>\n" +
                    "</div>\n" +
                    "<div class=\"rectangle\"></div>\n" +
                    "</body>\n" +
                    "</html>", "text/html");
                Transport.send(message);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Confirm the PIN",
                "PIN",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        ));
        if(pin == confirmPin){

            int newPin = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Create a new pin (4 digits)",
                    "PIN",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    this.pin
            ));

            while(newPin < 1000 || newPin > 9999 || pinExists(newPin)){
                JOptionPane.showMessageDialog(null, "Error: pin already exists", "Error", JOptionPane.ERROR_MESSAGE);
                newPin = Integer.parseInt((String) JOptionPane.showInputDialog(
                        null,
                        "Create a new pin (4 digits)",
                        "PIN",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        this.pin
                ));
            }

            this.changePin(newPin);
        }
        return 0;
    }

    public static boolean pinExists(int pin){
        boolean e = false;
        try(Connection conn = DriverManager.getConnection(link, username, pass);
        Statement stmt = conn.createStatement()){
            String strSelect = "SELECT pin from " + table;
            ResultSet rset = stmt.executeQuery(strSelect);
            while(rset.next()){
                if(rset.getInt("pin") == pin){
                    e = true;
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return e;
    }
}
