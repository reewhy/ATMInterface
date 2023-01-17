import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.Year;
import java.util.Objects;

public class ATMInterface {
    JFrame atm;
    Account loggedAccount;
    public ATMInterface(){
        atm = new JFrame();
        mainForm();
        atm.setSize(400, 600);
        atm.setLayout(null);
        atm.setVisible(true);
        atm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mainForm(){
        atm.getContentPane().removeAll();

        JLabel welcome = new JLabel("Login or Register");
        welcome.setFont(new Font(null, Font.BOLD, 20));
        welcome.setBounds(-10, 0, 400, 40);
        welcome.setHorizontalAlignment(JLabel.CENTER);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(30, 100, 300, 50);
        loginBtn.setFont(new Font(null, Font.BOLD, 20));
        loginBtn.addActionListener(e -> loginForm());

        JLabel or = new JLabel("or");
        or.setBounds(-20, 165, 400, 20);
        or.setHorizontalAlignment(JLabel.CENTER);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(30, 200, 300, 50);
        registerBtn.setFont(new Font(null, Font.BOLD, 20));
        registerBtn.addActionListener(e -> registerForm());

        atm.add(welcome);
        atm.add(loginBtn);
        atm.add(or);
        atm.add(registerBtn);
        atm.validate();
        atm.repaint();
    }

    private void loginForm() {
        atm.getContentPane().removeAll();

        JLabel nameTxt = new JLabel("Name");
        JLabel pinTxt = new JLabel("Pin");
        JLabel forgotPin = new JLabel("I forgot my PIN...");

        JTextField nameIn = new JTextField();
        JPasswordField pinIn = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JButton cancelBtn = new JButton("Cancel");

        nameTxt.setBounds(40, 20, 50, 10);
        nameIn.setBounds(40, 40, 300, 25);
        pinTxt.setBounds(40, 70, 70, 15);
        pinIn.setBounds(40, 90, 300, 25);
        forgotPin.setBounds(-100, 340, 600, 15);

        forgotPin.setHorizontalAlignment(SwingConstants.CENTER);
        forgotPin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                forgotMyPassWord();
            }
        });

        loginBtn.setBounds(40, 220, 300, 50);
        loginBtn.setFont(new Font(null, Font.BOLD, 20));
        loginBtn.addActionListener(e -> loginAccount(nameIn.getText(), pinIn.getText()));
        cancelBtn.setBounds(40, 280, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener((e -> mainForm()));

        atm.add(forgotPin);
        atm.add(nameIn);
        atm.add(loginBtn);
        atm.add(nameTxt);
        atm.add(pinIn);
        atm.add(pinTxt);
        atm.add(cancelBtn);

        atm.validate();
        atm.repaint();
    }

    private void forgotMyPassWord(){
        atm.getContentPane().removeAll();

        JLabel nameTxt = new JLabel("Name");
        JLabel lastNameTxt = new JLabel("Last name");
        JLabel cityTxt = new JLabel("City");
        JLabel dateTxt = new JLabel("Date of birth");

        JTextField nameIn = new JTextField();
        JTextField lastNameIn = new JTextField();
        JTextField cityIn = new JTextField();
        JTextField dateIn = new JTextField();

        JToggleButton emailOrSMSBtn = new JToggleButton("EMAIL");
        emailOrSMSBtn.setBounds(40, 220, 300, 50);
        emailOrSMSBtn.setFont(new Font(null, Font.BOLD, 20));
        emailOrSMSBtn.addActionListener(e -> {
            if(emailOrSMSBtn.isSelected()){
                emailOrSMSBtn.setText("SMS");
            } else {
                emailOrSMSBtn.setText("EMAIL");
            }
        });

        JButton confirmBtn = new JButton("Change");
        confirmBtn.setBounds(40, 280, 300, 50);
        confirmBtn.setFont(new Font(null, Font.BOLD, 20));
        confirmBtn.addActionListener(e ->{
            if(nameIn.getText().equals("") || lastNameIn.getText().equals("") || cityIn.getText().equals("") || dateIn.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Error: one text field is empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Account account = Account.getAccount(TagGenerator.generateTag(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText()));
                int i = 1;
                if(!Objects.equals(account.name, ""))
                    i  = account.forgotPin(emailOrSMSBtn.isSelected());
                if(i == 0){
                    JOptionPane.showMessageDialog(null, "PIN changed successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                    mainForm();
                }
            }
        });
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(40, 340, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener((e -> loginForm()));



        /*registerBtn.addActionListener(e -> registerAccount(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText()));*/

        nameTxt.setBounds(40, 20, 50, 10);
        nameIn.setBounds(40, 40, 300, 25);
        lastNameTxt.setBounds(40, 70, 70, 15);
        lastNameIn.setBounds(40, 90, 300, 25);
        cityTxt.setBounds(40, 120, 50, 15);
        cityIn.setBounds(40, 140, 300, 25);
        dateTxt.setBounds(40, 170, 70, 15);
        dateIn.setBounds(40, 190, 300, 25);


        atm.add(nameIn);
        atm.add(nameTxt);
        atm.add(lastNameIn);
        atm.add(lastNameTxt);
        atm.add(cityIn);
        atm.add(cityTxt);
        atm.add(dateIn);
        atm.add(dateTxt);
        atm.add(cancelBtn);
        atm.add(emailOrSMSBtn);
        atm.add(confirmBtn);
        atm.validate();
        atm.repaint();
    }
    private void userForm(){
        atm.getContentPane().removeAll();

        JLabel withdrawTxt = new JLabel("Withdraw");
        withdrawTxt.setBounds(40, 20, 80, 10);
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(40, 40, 300, 50);
        withdrawBtn.setFont(new Font(null, Font.BOLD, 20));
        withdrawBtn.addActionListener(e -> withdraw());
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(40, 100, 300, 50);
        depositBtn.setFont(new Font(null, Font.BOLD, 20));
        depositBtn.addActionListener(e -> deposit());
        JButton sendBtn = new JButton("Send");
        sendBtn.setBounds(40, 160, 300, 50);
        sendBtn.setFont(new Font(null, Font.BOLD, 20));
        sendBtn.addActionListener(e -> send());
        JButton viewBtn = new JButton("View");
        viewBtn.setBounds(40, 220, 300, 50);
        viewBtn.setFont(new Font(null, Font.BOLD, 20));
        viewBtn.addActionListener(e -> view());
        JButton settingsBtn = new JButton("Settings");
        settingsBtn.setBounds(40, 280, 300, 50);
        settingsBtn.setFont(new Font(null, Font.BOLD, 20));
        settingsBtn.addActionListener(e -> setting());
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(40, 340, 300, 50);
        logoutBtn.setFont(new Font(null, Font.BOLD, 20));
        logoutBtn.addActionListener(e -> {
            loggedAccount = null;
            mainForm();
        });

        if(loggedAccount.admin){
            JButton adminBtn = new JButton("Admin");
            adminBtn.setBounds(40, 400, 300, 50);
            adminBtn.setFont(new Font(null, Font.BOLD, 20));
            adminBtn.addActionListener(e -> adminForm());

            atm.add(adminBtn);
        }

        atm.add(settingsBtn);
        atm.add(logoutBtn);
        atm.add(viewBtn);
        atm.add(sendBtn);
        atm.add(withdrawTxt);
        atm.add(withdrawBtn);
        atm.add(depositBtn);
        atm.validate();
        atm.repaint();
    }

    private void setting() {
        atm.getContentPane().removeAll();

        JLabel emailTxt = new JLabel("Email");
        JLabel pinTxt = new JLabel("Pin");
        JLabel numberTxt = new JLabel("Phoner");

        JTextField emailIn = new JTextField();
        JPasswordField pinIn = new JPasswordField();
        JTextField numberIn = new JTextField();

        emailIn.setText(loggedAccount.email);
        pinIn.setText(String.valueOf(loggedAccount.pin));
        numberIn.setText(loggedAccount.phone);

        JButton applyBtn = new JButton("Apply");
        JButton cancelBtn = new JButton("Cancel");

        emailTxt.setBounds(40, 20, 50, 10);
        emailIn.setBounds(40, 40, 300, 25);
        numberTxt.setBounds(40, 70, 70, 15);
        numberIn.setBounds(40, 90, 300, 25);
        pinTxt.setBounds(40, 120, 50, 15);
        pinIn.setBounds(40, 140, 300, 25);

        applyBtn.setBounds(40, 320, 300, 50);
        applyBtn.setFont(new Font(null, Font.BOLD, 20));
        applyBtn.addActionListener(e -> {
            if(pinIn.getText().equals("") || emailIn.getText().equals("") || numberIn.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Error: one text field is empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int pin = Integer.parseInt(pinIn.getText());
                int i = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if(i == JOptionPane.YES_OPTION){
                    if(Account.pinExists(pin)){
                        JOptionPane.showMessageDialog(null, "Error: pin already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (pin>9999 || pin < 0){
                        JOptionPane.showMessageDialog(null, "Error: pin is too big or too short", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        loggedAccount.phone = numberIn.getText();
                        loggedAccount.email = emailIn.getText();
                        loggedAccount.pin = Integer.parseInt(pinIn.getText());
                        loggedAccount.resetVerification();
                        mainForm();
                    }
                }
            }
        });
        cancelBtn.setBounds(40, 380, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener(e -> {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(i == JOptionPane.YES_OPTION){
                userForm();
            }
        });

        atm.add(applyBtn);
        atm.add(cancelBtn);
        atm.add(emailIn);
        atm.add(emailTxt);
        atm.add(pinIn);
        atm.add(pinTxt);
        atm.add(numberTxt);
        atm.add(numberIn);

        atm.validate();
        atm.repaint();
    }

    private void adminForm() {
        atm.getContentPane().removeAll();

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(40, 460, 300, 50);
        exitBtn.setFont(new Font(null, Font.BOLD, 20));
        exitBtn.addActionListener(e -> userForm());

        JButton showBtn = new JButton("Show");
        showBtn.setBounds(40, 40, 300, 50);
        showBtn.setFont(new Font(null, Font.BOLD, 20));
        showBtn.addActionListener(e -> show());

        JButton createBtn = new JButton("Create");
        createBtn.setBounds(40, 100, 300, 50);
        createBtn.setFont(new Font(null, Font.BOLD, 20));
        createBtn.addActionListener(e -> create());

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(40, 160, 300, 50);
        deleteBtn.setFont(new Font(null, Font.BOLD, 20));
        deleteBtn.addActionListener(e -> delete());

        JButton changeBtn = new JButton("Change");
        changeBtn.setBounds(40, 220, 300, 50);
        changeBtn.setFont(new Font(null, Font.BOLD, 20));
        changeBtn.addActionListener(e -> change());

        JButton resetBtn = new JButton("Reset");
        resetBtn.setBounds(40, 280, 300, 50);
        resetBtn.setFont(new Font(null, Font.BOLD, 20));
        resetBtn.addActionListener(e -> reset());
        
        JButton settingsBtn = new JButton("Settings");
        settingsBtn.setBounds(40, 340, 300, 50);
        settingsBtn.setFont(new Font(null, Font.BOLD, 20));
        settingsBtn.addActionListener(e -> settings());

        atm.add(settingsBtn);
        atm.add(resetBtn);
        atm.add(changeBtn);
        atm.add(deleteBtn);
        atm.add(exitBtn);
        atm.add(showBtn);
        atm.add(createBtn);
        atm.validate();
        atm.repaint();
    }

    private void settings() {
        atm.getContentPane().removeAll();

        JLabel databaseTxt = new JLabel("Database name");
        JLabel nameTxt = new JLabel("SQL user name");
        JLabel passTxt = new JLabel("SQL user pass");
        JLabel tableTxt = new JLabel("Table name");

        JTextField databaseIn = new JTextField();
        JTextField nameIn = new JTextField();
        JTextField passIn = new JTextField();
        JTextField tableIn = new JTextField();

        databaseIn.setText(Settings.dataname);
        nameIn.setText(Settings.name);
        passIn.setText(Settings.pass);
        tableIn.setText(Settings.table);

        JButton applyBtn = new JButton("Apply");
        JButton cancelBtn = new JButton("Cancel");

        databaseTxt.setBounds(40, 20, 50, 10);
        databaseIn.setBounds(40, 40, 300, 25);
        nameTxt.setBounds(40, 70, 70, 15);
        nameIn.setBounds(40, 90, 300, 25);
        passTxt.setBounds(40, 120, 50, 15);
        passIn.setBounds(40, 140, 300, 25);
        tableTxt.setBounds(40, 170, 70, 15);
        tableIn.setBounds(40, 190, 300, 25);

        applyBtn.setBounds(40, 320, 300, 50);
        applyBtn.setFont(new Font(null, Font.BOLD, 20));
        applyBtn.addActionListener(e -> {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(i == JOptionPane.YES_OPTION){
                Settings.setSettings(databaseIn.getText(), nameIn.getText(), passIn.getText(), tableIn.getText());
                Settings.applySettings();
                adminForm();
            }
        });
        cancelBtn.setBounds(40, 380, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener(e -> {
            int i = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(i == JOptionPane.YES_OPTION){
                adminForm();
            }
        });

        atm.add(applyBtn);
        atm.add(cancelBtn);
        atm.add(databaseIn);
        atm.add(databaseTxt);
        atm.add(nameIn);
        atm.add(nameTxt);
        atm.add(passIn);
        atm.add(passTxt);
        atm.add(tableIn);
        atm.add(tableTxt);

        atm.validate();
        atm.repaint();
    }

    private void reset() {
        int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Confirm the PIN",
                "PIN",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        if(loggedAccount.pin == pin){
            int i = 0;
            Account[] accounts = Account.getAccounts(loggedAccount.pin);
            for(Account account: accounts){
                i += account.deleteAccount();
            }

            if(i>0){
                JOptionPane.showMessageDialog(null, "Database resetted succesfully", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: PIN wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void change() {
        String tag = (String) JOptionPane.showInputDialog(
                null,
                "Insert person's ID",
                "ID",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        );
        Account acc = Account.getAccount(tag);
        changeForm(acc);
    }


    private void changeForm(Account accToChange) {
        atm.getContentPane().removeAll();

        JLabel nameTxt = new JLabel("Name");
        JLabel lastNameTxt = new JLabel("Last name");
        JLabel cityTxt = new JLabel("City");
        JLabel dateTxt = new JLabel("Date of birth");
        JLabel balanceTxt = new JLabel("Balance");

        JTextField nameIn = new JTextField();
        JTextField lastNameIn = new JTextField();
        JTextField cityIn = new JTextField();
        JTextField dateIn = new JTextField();
        JTextField balanceIn = new JTextField();

        JButton registerBtn = new JButton("Change");
        JButton cancelBtn = new JButton("Cancel");
        JToggleButton adminBtn = new JToggleButton("Admin");
        JToggleButton enabledBtn = new JToggleButton("Enabled");

        nameTxt.setBounds(40, 20, 50, 10);
        nameIn.setBounds(40, 40, 300, 25);
        lastNameTxt.setBounds(40, 70, 70, 15);
        lastNameIn.setBounds(40, 90, 300, 25);
        cityTxt.setBounds(40, 120, 50, 15);
        cityIn.setBounds(40, 140, 300, 25);
        dateTxt.setBounds(40, 170, 70, 15);
        dateIn.setBounds(40, 190, 300, 25);
        balanceTxt.setBounds(40, 220, 70, 15);
        balanceIn.setBounds(40, 240, 300, 25);

        nameIn.setText(accToChange.name);
        lastNameIn.setText(accToChange.lastName);
        cityIn.setText(accToChange.city);
        dateIn.setText(accToChange.date);
        balanceIn.setText(String.valueOf(accToChange.balance));

        registerBtn.setBounds(40, 460, 300, 50);
        registerBtn.setFont(new Font(null, Font.BOLD, 20));
        registerBtn.addActionListener(e -> {
            accToChange.name = nameIn.getText();
            accToChange.lastName = lastNameIn.getText();
            accToChange.city = cityIn.getText();
            accToChange.date = dateIn.getText();
            accToChange.balance = Integer.parseInt(balanceIn.getText());
            accToChange.tag = TagGenerator.generateTag(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText());
            accToChange.admin = adminBtn.isSelected();
            accToChange.enabled = enabledBtn.isSelected();
            int i = accToChange.updateAccount();
            if(i == 0){
                JOptionPane.showMessageDialog(null, "Account changed successfully", "Success", JOptionPane.PLAIN_MESSAGE);
                adminForm();
            }

        });
        cancelBtn.setBounds(40, 400, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener((e -> adminForm()));
        adminBtn.setBounds(40, 280, 300, 50);
        adminBtn.setFont(new Font(null, Font.BOLD, 20));
        enabledBtn.setBounds(40, 340, 300, 50);
        enabledBtn.setFont(new Font(null, Font.BOLD, 20));

        enabledBtn.setSelected(accToChange.enabled);
        adminBtn.setSelected(accToChange.admin);

        atm.add(enabledBtn);
        atm.add(balanceTxt);
        atm.add(balanceIn);
        atm.add(adminBtn);
        atm.add(nameTxt);
        atm.add(nameIn);
        atm.add(lastNameIn);
        atm.add(lastNameTxt);
        atm.add(cityTxt);
        atm.add(cityIn);
        atm.add(dateTxt);
        atm.add(dateIn);
        atm.add(cancelBtn);
        atm.add(registerBtn);

        atm.validate();
        atm.repaint();
    }

    private void delete() {
        atm.getContentPane().removeAll();

        JLabel nameTxt = new JLabel("Name");
        JLabel lastNameTxt = new JLabel("Last name");
        JLabel cityTxt = new JLabel("City");
        JLabel dateTxt = new JLabel("Date of birth");

        JTextField nameIn = new JTextField();
        JTextField lastNameIn = new JTextField();
        JTextField cityIn = new JTextField();
        JTextField dateIn = new JTextField();

        JButton registerBtn = new JButton("Delete");
        JButton cancelBtn = new JButton("Cancel");

        nameTxt.setBounds(40, 20, 50, 10);
        nameIn.setBounds(40, 40, 300, 25);
        lastNameTxt.setBounds(40, 70, 70, 15);
        lastNameIn.setBounds(40, 90, 300, 25);
        cityTxt.setBounds(40, 120, 50, 15);
        cityIn.setBounds(40, 140, 300, 25);
        dateTxt.setBounds(40, 170, 70, 15);
        dateIn.setBounds(40, 190, 300, 25);

        registerBtn.setBounds(40, 220, 300, 50);
        registerBtn.setFont(new Font(null, Font.BOLD, 20));
        registerBtn.addActionListener(e -> {
            int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Confirm the PIN",
                    "PIN",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    loggedAccount.balance
            ));
            deleteAccount(TagGenerator.generateTag(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText()), loggedAccount.pin == pin);
        });/*registerAccount(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText(), adminBtn.isSelected())*/
        cancelBtn.setBounds(40, 280, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener((e -> adminForm()));

        atm.add(nameTxt);
        atm.add(nameIn);
        atm.add(lastNameIn);
        atm.add(lastNameTxt);
        atm.add(cityTxt);
        atm.add(cityIn);
        atm.add(dateTxt);
        atm.add(dateIn);
        atm.add(cancelBtn);
        atm.add(registerBtn);

        atm.validate();
        atm.repaint();
    }

    private void create() {
        atm.getContentPane().removeAll();

        JLabel nameTxt = new JLabel("Name");
        JLabel lastNameTxt = new JLabel("Last name");
        JLabel cityTxt = new JLabel("City");
        JLabel dateTxt = new JLabel("Date of birth");
        JLabel emailTxt = new JLabel("Email");
        JLabel phoneTxt = new JLabel("Phone");

        JTextField nameIn = new JTextField();
        JTextField lastNameIn = new JTextField();
        JTextField cityIn = new JTextField();
        JTextField dateIn = new JTextField();
        JTextField emailIn = new JTextField();
        JTextField phoneIn = new JTextField();

        JToggleButton adminBtn = new JToggleButton("Admin");

        JButton registerBtn = new JButton("Register");
        JButton cancelBtn = new JButton("Cancel");

        nameTxt.setBounds(40, 20, 50, 10);
        nameIn.setBounds(40, 40, 300, 25);
        lastNameTxt.setBounds(40, 70, 70, 15);
        lastNameIn.setBounds(40, 90, 300, 25);
        cityTxt.setBounds(40, 120, 50, 15);
        cityIn.setBounds(40, 140, 300, 25);
        dateTxt.setBounds(40, 170, 70, 15);
        dateIn.setBounds(40, 190, 300, 25);
        emailTxt.setBounds(40, 320, 70, 15);
        emailIn.setBounds(40, 340, 300, 25);
        phoneTxt.setBounds(40, 370, 70, 15);
        phoneIn.setBounds(40, 390, 300, 25);

        registerBtn.setBounds(40, 480, 300, 50);
        registerBtn.setFont(new Font(null, Font.BOLD, 20));
        registerBtn.addActionListener(e -> registerAccount(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText(), adminBtn.isSelected(), emailIn.getText(), phoneIn.getText()));
        cancelBtn.setBounds(40, 540, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener((e -> adminForm()));
        adminBtn.setBounds(40, 420, 300, 50);
        adminBtn.setFont(new Font(null, Font.BOLD, 20));

        atm.add(emailTxt);
        atm.add(phoneTxt);
        atm.add(emailIn);
        atm.add(phoneIn);
        atm.add(adminBtn);
        atm.add(nameTxt);
        atm.add(nameIn);
        atm.add(lastNameIn);
        atm.add(lastNameTxt);
        atm.add(cityTxt);
        atm.add(cityIn);
        atm.add(dateTxt);
        atm.add(dateIn);
        atm.add(cancelBtn);
        atm.add(registerBtn);

        atm.validate();
        atm.repaint();
    }

    private void show() {
        JFrame frame = new JFrame();
        JTable table = new JTable(Account.getAllAccounts());
        table.setDefaultEditor(Object.class, null);
        frame.add(table);
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void view() {
        int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Confirm the PIN",
                "PIN",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        if(pin == loggedAccount.pin){
            JOptionPane.showMessageDialog(null, "Balance: " + loggedAccount.balance, "Balance", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error: PIN wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void send() {
        int amount = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "How much to do you want send?",
                "Amount",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Confirm the PIN",
                "PIN",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        String tag = (String) JOptionPane.showInputDialog(
                null,
                "Insert person's ID",
                "ID",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        );
        Account sent = Account.getAccount(tag);

        if(pin == loggedAccount.pin && !Objects.equals(sent.name, "")){
            loggedAccount.balance -= amount;
            sent.balance += amount;
            loggedAccount.updateAccount();
            sent.updateAccount();
        } else {
            JOptionPane.showMessageDialog(null, "Error: PIN or ID wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deposit() {
        int amount = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "How much to do you want deposit?",
                "Amount",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "Confirm the PIN",
                "PIN",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        if(pin == loggedAccount.pin){
            loggedAccount.balance += amount;
            loggedAccount.updateAccount();
        }else {
            JOptionPane.showMessageDialog(null, "Error: PIN wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(loggedAccount.balance);
    }

    private void withdraw() {
        int amount = Integer.parseInt((String) JOptionPane.showInputDialog(
                null,
                "How much to do you want withdraw?",
                "Amount",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                loggedAccount.balance
        ));
        if(amount > loggedAccount.balance){
            JOptionPane.showMessageDialog(null, "The amount selected is too big!", "Errore", JOptionPane.ERROR_MESSAGE);
        } else if (amount < 0){
            JOptionPane.showMessageDialog(null, "The amount selected is too little!", "Errore", JOptionPane.ERROR_MESSAGE);
        } else {
            int pin = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Confirm the PIN",
                    "PIN",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    loggedAccount.balance
            ));
            if(pin == loggedAccount.pin){
                loggedAccount.balance -= amount;
                loggedAccount.updateAccount();
            }else {
                JOptionPane.showMessageDialog(null, "Error: PIN wrong", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loginAccount(String name, String pin) {
        loggedAccount = Account.getAccount(name, pin);
        if(loggedAccount.name.equals("")){
            JOptionPane.showMessageDialog(null, "Errore: account non trovato", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        } else if (!loggedAccount.enabled){
            int code = Integer.parseInt((String) JOptionPane.showInputDialog(
                    null,
                    "Confirm the code we sent in your email",
                    "PIN",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    loggedAccount.balance
            ));
            System.out.println(loggedAccount.code);
            if(code == loggedAccount.code){
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.PLAIN_MESSAGE);
                loggedAccount.enabled = true;
                loggedAccount.updateAccount();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Code is wrong", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        System.out.println("accesso eseguito con successo: " + loggedAccount.tag );
        userForm();
    }

    private void registerForm(){
        atm.getContentPane().removeAll();

        JLabel nameTxt = new JLabel("Name");
        JLabel lastNameTxt = new JLabel("Last name");
        JLabel cityTxt = new JLabel("City");
        JLabel dateTxt = new JLabel("Date of birth");
        JLabel emailTxt = new JLabel("Email");
        JLabel phoneTxt = new JLabel("Phone");

        JTextField nameIn = new JTextField();
        JTextField lastNameIn = new JTextField();
        JTextField cityIn = new JTextField();
        JTextField dateIn = new JTextField();
        JTextField emailIn = new JTextField();
        JTextField phoneIn = new JTextField();

        JButton registerBtn = new JButton("Register");
        JButton cancelBtn = new JButton("Cancel");

        nameTxt.setBounds(40, 20, 50, 10);
        nameIn.setBounds(40, 40, 300, 25);
        lastNameTxt.setBounds(40, 70, 70, 15);
        lastNameIn.setBounds(40, 90, 300, 25);
        cityTxt.setBounds(40, 120, 50, 15);
        cityIn.setBounds(40, 140, 300, 25);
        dateTxt.setBounds(40, 170, 70, 15);
        dateIn.setBounds(40, 190, 300, 25);
        emailTxt.setBounds(40, 220, 70, 15);
        emailIn.setBounds(40, 240, 300, 25);
        phoneTxt.setBounds(40, 270, 300, 15);
        phoneIn.setBounds(40, 290, 300, 25);

        registerBtn.setBounds(40, 320, 300, 50);
        registerBtn.setFont(new Font(null, Font.BOLD, 20));
        registerBtn.addActionListener(e -> {
            if(nameIn.getText().equals("") || lastNameIn.getText().equals("") || cityIn.getText().equals("") || dateIn.getText().equals("") || emailIn.getText().equals("") || phoneIn.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Error: one text field is empty", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (emailIn.getText().contains("@")){
                JOptionPane.showMessageDialog(null, "Error: email isn't valid", "Error", JOptionPane.ERROR_MESSAGE);
            } else
                registerAccount(nameIn.getText(), lastNameIn.getText(), cityIn.getText(), dateIn.getText(), emailIn.getText(), phoneIn.getText());

        });
        cancelBtn.setBounds(40, 380, 300, 50);
        cancelBtn.setFont(new Font(null, Font.BOLD, 20));
        cancelBtn.addActionListener((e -> mainForm()));

        atm.add(emailTxt);
        atm.add(phoneTxt);
        atm.add(emailIn);
        atm.add(phoneIn);
        atm.add(nameTxt);
        atm.add(nameIn);
        atm.add(lastNameIn);
        atm.add(lastNameTxt);
        atm.add(cityTxt);
        atm.add(cityIn);
        atm.add(dateTxt);
        atm.add(dateIn);
        atm.add(cancelBtn);
        atm.add(registerBtn);

        atm.validate();
        atm.repaint();
    }

    private void registerAccount(String name, String lastName, String city, String date, String email, String phone){
        String[] dateDivided = date.split("/");

        if(Integer.parseInt(dateDivided[2]) > Year.now().getValue()){
            JOptionPane.showMessageDialog(null, "Errore: anno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean meseGrande = false, bisestile = false;
        switch (dateDivided[1]) {
            case "02" -> {
                if (Integer.parseInt(dateDivided[1]) % 4 == 0) {
                    bisestile = true;
                }
            }
            case "01", "03", "05", "07", "08", "10", "12" -> meseGrande = true;
            case "04", "06", "09", "11" -> {
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Errore: mese non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        switch(dateDivided[0]){
            case "01":
            case "02":
            case "03":
            case "04":
            case "05":
            case "06":
            case "07":
            case "08":
            case "09":
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
            case "21":
            case "22":
            case "23":
            case "24":
            case "25":
            case "26":
            case "27":
            case "28":
                break;
            case "29":
                if(!(dateDivided[1].equals("02") && bisestile)){
                    JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "30":
                if(dateDivided[1].equals("02")){
                    JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "31":
                if(dateDivided[1].equals("02") || !meseGrande){
                    JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
        }

        Account newPerson = new Account(name, lastName, date, city, 1000, 0, "", false, email, phone, 0, false);
        int pin = newPerson.createAccount();
        if(pin==1000 || pin==1001)
            JOptionPane.showMessageDialog(null, "Errore", "Errore", JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(null, "Il suo pin è: " + pin, "Pin", JOptionPane.PLAIN_MESSAGE);
            JOptionPane.showMessageDialog(null, "We'll send you a verification code in the email", "Code", JOptionPane.PLAIN_MESSAGE);
            mainForm();
        }
    }

    private void registerAccount(String name, String lastName, String city, String date, boolean admin, String email, String phone){
        String[] dateDivided = date.split("/");

        if(Integer.parseInt(dateDivided[2]) > Year.now().getValue()){
            JOptionPane.showMessageDialog(null, "Errore: anno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean meseGrande = false, bisestile = false;
        switch (dateDivided[1]) {
            case "02" -> {
                if (Integer.parseInt(dateDivided[1]) % 4 == 0) {
                    bisestile = true;
                }
            }
            case "01", "03", "05", "07", "08", "10", "12" -> meseGrande = true;
            case "04", "06", "09", "11" -> {
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Errore: mese non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        switch(dateDivided[0]){
            case "01":
            case "02":
            case "03":
            case "04":
            case "05":
            case "06":
            case "07":
            case "08":
            case "09":
            case "10":
            case "11":
            case "12":
            case "13":
            case "14":
            case "15":
            case "16":
            case "17":
            case "18":
            case "19":
            case "20":
            case "21":
            case "22":
            case "23":
            case "24":
            case "25":
            case "26":
            case "27":
            case "28":
                break;
            case "29":
                if(!(dateDivided[1].equals("02") && bisestile)){
                    JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "30":
                if(dateDivided[1].equals("02")){
                    JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            case "31":
                if(dateDivided[1].equals("02") || !meseGrande){
                    JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Errore: giorno non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
        }

        Account newPerson = new Account(name, lastName, date, city, 1000, 0, "", admin, email, phone, 0, false);
        int pin = newPerson.createAccount();
        if(pin==1000 || pin==1001)
            JOptionPane.showMessageDialog(null, "Errore", "Errore", JOptionPane.ERROR_MESSAGE);
        else {
            JOptionPane.showMessageDialog(null, "Il suo pin è: " + pin, "Pin", JOptionPane.PLAIN_MESSAGE);
            adminForm();
        }
    }

    private void deleteAccount(String tag, boolean confirm){
        if(confirm){
            Account accountToDelete = Account.getAccount(tag);
            if(accountToDelete.name.equals("")){
                JOptionPane.showMessageDialog(null, "Error: Account doesn't exist", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int i = accountToDelete.deleteAccount();
            if(i == 1){
                JOptionPane.showMessageDialog(null, "Account deleted successfully", "Error", JOptionPane.PLAIN_MESSAGE);
            } else{
                JOptionPane.showMessageDialog(null, "Couldn't delete the account", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else{
            JOptionPane.showMessageDialog(null, "Error: PIN wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
        adminForm();
    }
}
