package FrontEnd;

import DBConnection.ConnectionDetails;
import Models.SearchUserTableModel;
import Models.UpdateUser;
import Models.User;
import Models.UserTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchGUI extends JFrame implements ActionListener
{
    private int selecteRow;
    private String[] columnNames = {"ID", "Username", "Email", "Country", "Plan"};
    private String[] dropdownItems = getDropMenuItems();

    JPanel pnlMain, pnlTop, pnlButtons, pnlCentral, pnlCentralTop, pnlCentralBot;

    //Elements for pnlTitle;
    JPanel pnlImage, pnlTitle;
    JLabel lblTitle, lblImage;

    // Elements for pnlCentral:
    JPanel pnlCentralTop1, pnlCentralTop2, pnlCentralTop3, pnlCentralTop4, pnlCentralTop5, pnlCentralTop6, pnlCentralTop7;
    JLabel lblUserName, lblUserPlan, lblUserMail, lblUserCountry;
    JTextField txtUserName;
    JComboBox comboUserPlans;
    JTable tblUsers;
    JScrollPane scroll;

    UserTableModel userTblModel;
    SearchUserTableModel searchUserTblModel;

    // Elements for pnlButtons
    JButton btnUpdate, btnClear, btnBackToMenu, btnSearch, btnDelete;

    ImageIcon background;

    public SearchGUI(){
        super("Search User");

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
        pnlCentral.setLayout(new BorderLayout());

        pnlCentralTop = new JPanel();
        pnlCentralTop.setLayout(new GridLayout(5,1));

        pnlCentralBot = new JPanel();
        pnlCentralBot.setLayout(new BorderLayout());

        pnlCentralTop1 = new JPanel();
        pnlCentralTop1.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentralTop2 = new JPanel();
        pnlCentralTop2.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentralTop3 = new JPanel();
        pnlCentralTop3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentralTop4 = new JPanel();
        pnlCentralTop4.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentralTop5 = new JPanel();
        pnlCentralTop5.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentralTop6 = new JPanel();
        pnlCentralTop6.setLayout(new FlowLayout(FlowLayout.RIGHT));

        pnlCentralTop7 = new JPanel();
        pnlCentralTop7.setLayout(new FlowLayout(FlowLayout.RIGHT));



        // Labels:
        lblTitle = new JLabel("Search User");
        lblTitle.setFont(new Font("Arial", Font.BOLD,16 ));

        lblUserName = new JLabel("Username");
        lblUserMail = new JLabel("Mail");
        lblUserCountry = new JLabel("Country");
        lblUserPlan= new JLabel("Plan");

        // Textboxes:
        txtUserName = new JTextField();
        txtUserName.setPreferredSize(new Dimension(200,30));

        // Buttons:
        btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(200, 30));
        btnSearch.addActionListener(this);

        btnDelete = new JButton("Delete");
        btnDelete.setPreferredSize(new Dimension(150, 30));
        btnDelete.addActionListener(this);

        btnUpdate = new JButton("Update User");
        btnUpdate.setPreferredSize(new Dimension(200, 30));
        btnUpdate.addActionListener(this);


        btnBackToMenu = new JButton("Back");
        btnBackToMenu.setPreferredSize(new Dimension(70, 30));
        btnBackToMenu.setBackground(new Color(192,192,192));
        btnBackToMenu.addActionListener(this);

        comboUserPlans = new JComboBox(dropdownItems);
        comboUserPlans.setPreferredSize(new Dimension(200,30));

        // Table
        userTblModel = new UserTableModel();
        tblUsers = new JTable(userTblModel);
        tblUsers.getTableHeader().setFont(new Font("Arial", Font.BOLD,13));
        tblUsers.setModel(userTblModel);
        tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSM = tblUsers.getSelectionModel();

        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                selecteRow = lsm.getMinSelectionIndex();
            }
        });

        scroll = new JScrollPane(tblUsers,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setPreferredSize(new Dimension(-1, 100));

        // Build the panels:
        pnlTop.add(pnlImage, BorderLayout.NORTH);
        pnlTop.add(pnlTitle, BorderLayout.SOUTH);

        pnlCentralTop1.add(lblUserName);
        pnlCentralTop1.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.CENTER);
        pnlCentralTop1.add(txtUserName);

        pnlCentralTop2.add(lblUserPlan);
        pnlCentralTop2.add(comboUserPlans);

        pnlCentralTop3.add(btnSearch);

        pnlCentralTop4.add(btnUpdate);

        pnlCentralTop.add(pnlCentralTop1);
        pnlCentralTop.add(pnlCentralTop2);
        pnlCentralTop.add(pnlCentralTop3);
        pnlCentralTop.add(pnlCentralTop4);

        pnlCentralBot.add(scroll, BorderLayout.CENTER);


        pnlCentral.add(pnlCentralTop, BorderLayout.NORTH);
        pnlCentral.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.CENTER);
        pnlCentral.add(pnlCentralBot, BorderLayout.SOUTH);


        pnlButtons.add(btnDelete);
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
        if(e.getSource() == btnDelete)
        {
            if(JOptionPane.showConfirmDialog(null,"You are about to delete a record, proceed?") == 0)
                deleteRecord();
        }
        if(e.getSource() == btnBackToMenu)
        {
            this.dispose();
        }
        if(e.getSource() == btnUpdate)
        {
            try
            {
                User user = userTblModel.getRow(selecteRow);
                System.out.println(user.toString());
                UpdateUser updateFrame = new UpdateUser(this, userTblModel, user);
            }
            catch (IndexOutOfBoundsException iob)
            {
                iob.printStackTrace();
                JOptionPane.showMessageDialog(null, "No user is selected");
            }

        }
        if(txtUserName.getText().equals("") && e.getSource() == btnSearch)
        {
            userTblModel = new UserTableModel();
            tblUsers.setModel(userTblModel);
        }
        else if(e.getSource() == btnSearch)
        {
            System.out.println("search btn clicked");

            searchUserTblModel = new SearchUserTableModel(txtUserName.getText());
            tblUsers.setModel(searchUserTblModel);
        }
    }

    public void deleteRecord()
    {
        User user = userTblModel.getRow(selecteRow);
        Connection con = null;

        try
        {
            con = ConnectionDetails.getConnection();
            Statement stmt = con.createStatement();

            String sql = "DELETE FROM `subscription_db`.`tbl_users` WHERE (`user_id` = '" + user.getUserID() + "');";

            stmt.executeUpdate(sql);

            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        userTblModel.getDataFromDB();
        userTblModel.fireTableRowsDeleted(selecteRow,selecteRow);
    }

    public static String[] getDropMenuItems()
    {
        Connection con;
        Statement stmt;
        ResultSet rs;


        ArrayList<String>list = new ArrayList<>();

        String strSQL = "SELECT * FROM tbl_plans";


        try {
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(strSQL);

            while(rs.next())
            {
                list.add(rs.getString("plan_name"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "getDropMenuItems() ERROR");
        }
        String[] list1 = list.toArray(new String[0]);

        return list1;
    }
}
