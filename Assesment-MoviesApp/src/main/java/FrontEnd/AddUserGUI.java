package FrontEnd;

import DBConnection.ConnectionDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddUserGUI extends JFrame implements ActionListener
{
    private String[] dropdownItems = SearchGUI.getDropMenuItems();
    String[] countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};
    String planID;
    String country;
    JPanel pnlMain, pnlTop, pnlButtons, pnlCentral;

    //Elements for pnlTitle;
    JPanel pnlImage, pnlTitle;
    JLabel lblTitle, lblImage;

    // Elements for pnlCentral:
    JPanel pnlCentral1, pnlCentral2, pnlCentral3, pnlCentral4, pnlCentral5;
    JLabel lblUserID, lblUserName, lblUserMail, lblUserCountry, lblUserPlan;
    JTextField txtUserID, txtUserName, txtUserMail, txtUserCountry;
    JComboBox comboUserPlans;
    JComboBox comboUserCountries;

    // Elements for pnlButtons
    JButton btnSaveUser, btnClear, btnBackToMenu;

    ImageIcon background;

    public AddUserGUI(){
        super("Add User");

        final Container con = getContentPane();
        con.setLayout(new BorderLayout());

        // Image:
        background = new ImageIcon("src/main/resources/Images/image3.png");

        Image img = background.getImage();
        Image temp = img.getScaledInstance(600,200,Image.SCALE_SMOOTH);

        background = new ImageIcon(temp);
        lblImage = new JLabel(background);
        lblImage.setLayout(null);
        lblImage.setBounds(0,0,500,600);

        //Panels:
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());

        pnlTop = new JPanel();
        pnlTop.setLayout(new BorderLayout());

        pnlImage = new JPanel();
        pnlImage.setLayout(new BorderLayout());

        pnlTitle = new JPanel();
        pnlTitle.setBackground(new Color(192,192,192));
        pnlTitle.setLayout(new FlowLayout());

        pnlButtons = new JPanel();
        pnlButtons.setBackground(new Color(192,192,192));
        pnlButtons.setLayout(new FlowLayout());

        pnlCentral = new JPanel();
        pnlCentral.setLayout(new GridLayout(5,1));

        pnlCentral1 = new JPanel();
        pnlCentral1.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentral2 = new JPanel();
        pnlCentral2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentral3 = new JPanel();
        pnlCentral3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentral4 = new JPanel();
        pnlCentral4.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentral5 = new JPanel();
        pnlCentral5.setLayout(new FlowLayout(FlowLayout.RIGHT));


        // Labels:
        lblTitle = new JLabel("Add User");
        lblTitle.setFont(new Font("Arial", Font.BOLD,16 ));

        lblUserID = new JLabel("User ID: ");
        lblUserName = new JLabel("Username: ");
        lblUserMail = new JLabel("E-Mail:");
        lblUserCountry = new JLabel("Country: ");
        lblUserPlan = new JLabel("Plan: ");

        // Textboxes:
        txtUserID = new JTextField();
        txtUserID.setPreferredSize(new Dimension(200,30));
        txtUserName = new JTextField();
        txtUserName.setPreferredSize(new Dimension(200,30));
        txtUserMail = new JTextField();
        txtUserMail.setPreferredSize(new Dimension(200,30));
        txtUserCountry = new JTextField();
        txtUserCountry.setPreferredSize(new Dimension(200,30));

        // Buttons:
        btnSaveUser = new JButton("Save User");
        btnSaveUser.setPreferredSize(new Dimension(150, 30));
        btnSaveUser.addActionListener(this);

        btnClear = new JButton("Clear details");
        btnClear.setPreferredSize(new Dimension(150, 30));
        btnClear.addActionListener(this);

        btnBackToMenu = new JButton("Back");
        btnBackToMenu.setPreferredSize(new Dimension(70, 30));
        btnBackToMenu.setBackground(new Color(192,192,192));
        btnBackToMenu.addActionListener(this);

        comboUserPlans = new JComboBox(dropdownItems);
        comboUserPlans.setPreferredSize(new Dimension(200,30));

        comboUserCountries = new JComboBox(countries);
        comboUserCountries.setPreferredSize(new Dimension(200,30));

        // Build the panels:
        pnlTop.add(pnlImage, BorderLayout.NORTH);
        pnlTop.add(pnlTitle, BorderLayout.SOUTH);

        pnlCentral1.add(lblUserID);
        pnlCentral1.add(txtUserID);

        pnlCentral2.add(lblUserName);
        pnlCentral2.add(txtUserName);

        pnlCentral3.add(lblUserMail);
        pnlCentral3.add(txtUserMail);

        pnlCentral4.add(lblUserCountry);
        pnlCentral4.add(comboUserCountries);

        pnlCentral5.add(lblUserPlan);
        pnlCentral5.add(comboUserPlans);

        pnlCentral.add(pnlCentral1);
        pnlCentral.add(pnlCentral2);
        pnlCentral.add(pnlCentral3);
        pnlCentral.add(pnlCentral4);
        pnlCentral.add(pnlCentral5);

        pnlButtons.add(btnSaveUser);
        pnlButtons.add(btnClear);
        pnlButtons.add(btnBackToMenu);

        pnlTitle.add(lblTitle, BorderLayout.CENTER);
        pnlImage.add(lblImage);

        pnlMain.add(pnlTop, BorderLayout.NORTH);
        pnlMain.add(pnlCentral, BorderLayout.CENTER);
        pnlMain.add(pnlButtons, BorderLayout.SOUTH);



        con.add(pnlMain, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnClear)
        {
            txtUserID.setText("");
            txtUserName.setText("");
            txtUserMail.setText("");
            txtUserCountry.setText("");
        }
        if(e.getSource() == btnBackToMenu)
        {
            this.dispose();
        }
        if(e.getSource() == btnSaveUser)
        {
            if(!validateUserID(txtUserID.getText()))
            {
                JOptionPane.showMessageDialog(null,"Invalid ID");
                txtUserID.setText("");
            }
            else if(!validateUserName(txtUserName.getText()))
            {
                JOptionPane.showMessageDialog(null,"Invalid Username, no special chars");
                txtUserName.setText("");
            }
            else
            {
                country = comboUserCountries.getSelectedItem().toString();
                planID = getPlanID(comboUserPlans.getSelectedItem().toString());
                AddNewUser(txtUserID.getText(), txtUserName.getText(), txtUserMail.getText(), country, planID);
            }

        }
    }

    public void AddNewUser(String userID, String name, String mail, String country, String planID)
    {
        Connection con;
        Statement stmt;

        String strSQL = "" +
                "INSERT INTO `subscription_db`.`tbl_users` (`user_id`, `user_name`, `user_email`, `user_country`, `fk_plan_id`) " +
                "VALUES ('"+userID+"', '"+name+"', '"+mail+"', '"+country+"', '"+planID+"');";

        try {
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(strSQL);
            JOptionPane.showMessageDialog(null, "Username: " + name + " - Added to DB");
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Existent Username, cannot add to DB");
        }
    }
    public static String getPlanID(String planName)
            // This method gets the ID of the plan the user requested.
    {
        List planNames = new ArrayList<>();
        List planIDs = new ArrayList<>();

        String strPlanID = null;

        Connection con;
        Statement stmt;
        ResultSet rs;

        try
        {
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tbl_plans");

            while (rs.next())
            {
                planNames.add(rs.getString("plan_name"));
                planIDs.add(rs.getInt("plan_id"));
            }

            strPlanID = String.valueOf(planNames.indexOf(planName));

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return strPlanID;
    }
    public static boolean validateUserID(String s)
    {
        boolean isValid = false;

        if (s == null || s.trim().isEmpty()) {
            System.out.println("ID is null or empty");
            return isValid;
        }

        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(s);

        boolean b = m.find();
        if (b)
            isValid = true;
        else
            isValid = false;
        System.out.println("Username invalid!");

        return isValid;
    }
    public static boolean validateUserName(String s)
    {
        boolean isValid = false;

        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return isValid;
        }

        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b)
            System.out.println("There is a special character in the  Username.");
        else
            isValid = true;
        System.out.println("Username validated!");

        return isValid;
    }
    public static boolean validateUserCountry(String s)
    {
        boolean isValid = false;

        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return isValid;
        }

        Pattern p = Pattern.compile("[^A-Za-z]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b)
            System.out.println("There is a special character in the  country.");
        else
            isValid = true;
        System.out.println("country validated!");

        return isValid;
    }
}
