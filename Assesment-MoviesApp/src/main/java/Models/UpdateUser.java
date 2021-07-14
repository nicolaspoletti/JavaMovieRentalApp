package Models;

import DBConnection.ConnectionDetails;
import FrontEnd.SearchGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UpdateUser extends JFrame implements ActionListener
{
    private int selectedRow;
    JButton btnUpdate = new JButton("Update User");

    JTextField txtUserID = new JTextField();
    JTextField txtUserName = new JTextField();
    JTextField txtUserMail= new JTextField();
    JTextField txtUserCountry= new JTextField();

    JPanel pnlData = new JPanel(new GridLayout(4,2));
    JPanel pnlButtons = new JPanel();

    Container c = getContentPane();

    SearchGUI parent;
    UserTableModel userTableModel;

    public UpdateUser(SearchGUI sg, UserTableModel model, User s)
    {
        this.setTitle("Update User");
        this.setVisible(true);
        this.setBounds(500,350,300,150);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        parent = sg;
        userTableModel = model;

        pnlData.add(new JLabel("User ID: "));
        pnlData.add(txtUserID);
        txtUserID.setText(Integer.toString(s.getUserID()));
        txtUserID.setEditable(false);
        txtUserID.setToolTipText("Cannot change the ID");

        pnlData.add(new JLabel("Username: "));
        pnlData.add(txtUserName);
        txtUserName.setText(s.getUserName());

        pnlData.add(new JLabel("User mail: "));
        pnlData.add(txtUserMail);
        txtUserMail.setText(s.getUserMail());

        pnlData.add(new JLabel("Country: "));
        pnlData.add(txtUserCountry);
        txtUserCountry.setText(s.getUserCountry());

        c.add(pnlData, BorderLayout.CENTER);
        pnlButtons.add(btnUpdate);
        c.add(pnlButtons, BorderLayout.SOUTH);

        btnUpdate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Connection con  = null;
        Statement stmt = null;

        String sql = "UPDATE `subscription_db`.`tbl_users` SET `user_name` = '"
                + txtUserName.getText() + "', `user_email` = '"
                + txtUserMail.getText() + "', `user_country` = '"
                +txtUserCountry.getText() + "' WHERE (`user_id` = '"
                +txtUserID.getText() + "');";

        try
        {
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "User updated");

            stmt.close();
            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        userTableModel.getDataFromDB();
        userTableModel.fireTableDataChanged();
        this.dispose();
        parent.setVisible(true);
    }
}
