package FrontEnd;

import DBConnection.ConnectionDetails;
import com.sun.jdi.ClassType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddPlanGUI extends JFrame implements ActionListener
{
    JPanel pnlMain, pnlTop, pnlButtons, pnlCentral;

    //Elements for pnlTitle;
    JPanel pnlImage, pnlTitle;
    JLabel lblTitle, lblImage;

    // Elements for pnlCentral:
    JPanel pnlCentral1, pnlCentral2, pnlCentral3;
    JLabel lblPlanName, lblPlanType;
    JTextField txtPlanName;
    JRadioButton radBtn1, radBtn2;
    // Elements for pnlButtons
    JButton btnSavePlan, btnClear, btnBackToMenu;

    ImageIcon background;

    public AddPlanGUI(){
        super("Add a plan");

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
        pnlCentral.setLayout(new GridLayout(3,1));

        pnlCentral1 = new JPanel();
        pnlCentral1.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlCentral2 = new JPanel();
        pnlCentral2.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlCentral3 = new JPanel();
        pnlCentral3.setLayout(new FlowLayout(FlowLayout.CENTER));


        // Labels:
        lblTitle = new JLabel("Add a new Plan");
        lblTitle.setFont(new Font("Arial", Font.BOLD,16 ));

        lblPlanName = new JLabel("Plan name:");
        lblPlanType = new JLabel("Choose a plan type: ");

        // Textboxes:

        txtPlanName = new JTextField();
        txtPlanName.setPreferredSize(new Dimension(200,30));

        // Buttons:
        radBtn1 = new JRadioButton("Monthly");
        radBtn2 = new JRadioButton("Yearly");

        btnSavePlan = new JButton("Save Plan");
        btnSavePlan.setPreferredSize(new Dimension(150, 30));
        btnSavePlan.addActionListener(this);

        btnClear = new JButton("Clear details");
        btnClear.setPreferredSize(new Dimension(150, 30));
        btnClear.addActionListener(this);

        btnBackToMenu = new JButton("Back");
        btnBackToMenu.setPreferredSize(new Dimension(70, 30));
        btnBackToMenu.setBackground(new Color(192,192,192));
        btnBackToMenu.addActionListener(this);

        // Build the panels:
        pnlTop.add(pnlImage, BorderLayout.NORTH);
        pnlTop.add(pnlTitle, BorderLayout.SOUTH);

        pnlCentral1.add(lblPlanName);
        pnlCentral1.add(txtPlanName);

        pnlCentral2.add(lblPlanType);
        pnlCentral2.add(radBtn1);
        pnlCentral2.add(radBtn2);

        pnlCentral.add(pnlCentral1);
        pnlCentral.add(pnlCentral2);
        pnlCentral.add(pnlCentral3);

        pnlButtons.add(btnSavePlan);
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
            txtPlanName.setText("");
        }
        if(e.getSource() == btnBackToMenu)
        {
            this.dispose();
        }
        if(e.getSource() == btnSavePlan)
        {
            if (validatePlanName(txtPlanName.getText()))
            {
                addNewPlan(txtPlanName.getText(), radBtn1.isSelected() ? "Monthly" : "Anual");
                getLastPlanID();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Wrong Plan name, pls try again.");
            }
        }
    }

    public void addNewPlan(String planName, String planType ){


        int planID = getLastPlanID() + 1;
        String plan = String.valueOf(planName);
        String type = String.valueOf(planType);

        Connection con;
        Statement stmt;

        String strSQL = "INSERT INTO `subscription_db`.`tbl_plans` (`plan_id`, `plan_name`, plan_type) "
                + "VALUES ('"+ planID +"', '" + plan + "','" + type + "');";

        try {
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(strSQL);
            JOptionPane.showMessageDialog(null, "Plan: " + plan + " - Added to DB");
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Existent plan, cannot add to DB");
        }
    }

    public boolean validatePlanName(String s) {

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
            System.out.println("There is a special character in the plan name.");
        else
            isValid = true;
            System.out.println("Plan name validated!");

        return isValid;
    }

    public int getLastPlanID()
    {
        int lastID = 0;

        Connection con;
        Statement stmt;
        ResultSet rs;

        try
        {
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `tbl_plans`");

            while(rs.next())
            {
                rs.getString("plan_id");
                System.out.println("getLastPlanID()" + rs.getString("plan_id"));
                lastID = Integer.parseInt(rs.getString("plan_id"));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("getLastPlanID() - Returns: " + lastID);
        return lastID;
    }
}
