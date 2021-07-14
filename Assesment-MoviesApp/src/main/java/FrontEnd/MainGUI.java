package FrontEnd;

import Models.User;
import Models.UserTableModel;
import Utilities.InputOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainGUI extends JFrame implements ActionListener
{
    ArrayList<User>list = createList();

    JPanel pnlMain, pnlTop, pnlButtons, pnlRight, pnlLeft;

    //Elements for pnlTitle;
    JPanel pnlImage, pnlTitle;
    JLabel lblTitle, lblImage;

    // Elements for pnlRight:
    JPanel pnlRight1, pnlRight2, pnlRight3;
    JButton btnAddPlan, btnSearchUser, btnBackup;

    // Elements for pnlLeft:
    JPanel pnlLeft1, pnlLeft2, pnlLeft3;
    JButton btnAddUser, btnHelp, btnRestore;

    ImageIcon background;

    public MainGUI(){
        super("Movies App");

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
        pnlTitle.setLayout(new FlowLayout());

        pnlButtons = new JPanel();
        pnlButtons.setLayout(new BorderLayout());

        pnlRight = new JPanel();
        pnlRight.setLayout(new GridLayout(3,1));

        pnlLeft = new JPanel();
        pnlLeft.setLayout(new GridLayout(3,1));

        pnlRight1 = new JPanel();
        pnlRight1.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlRight2 = new JPanel();
        pnlRight2.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlRight3 = new JPanel();
        pnlRight3.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlLeft1 = new JPanel();
        pnlLeft1.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlLeft2 = new JPanel();
        pnlLeft2.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlLeft3 = new JPanel();
        pnlLeft3.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Labels:
        lblTitle = new JLabel("Membership Registration");
        lblTitle.setFont(new Font("Arial", Font.BOLD,16 ));

        // Buttons:
        btnAddPlan = new JButton("New Plan");
        btnAddPlan.setPreferredSize(new Dimension(150, 30));
        btnAddPlan.addActionListener(this);

        btnSearchUser = new JButton("Search Member");
        btnSearchUser.setPreferredSize(new Dimension(150, 30));
        btnSearchUser.addActionListener(this);

        btnBackup = new JButton("Backup DB");
        btnBackup.setPreferredSize(new Dimension(150, 30));
        btnBackup.addActionListener(this);

        btnAddUser = new JButton("Add a user");
        btnAddUser.setPreferredSize(new Dimension(150, 30));
        btnAddUser.addActionListener(this);

        btnHelp = new JButton("Help");
        btnHelp.setPreferredSize(new Dimension(150, 30));
        btnHelp.addActionListener(this);

        btnRestore = new JButton("Restore DB");
        btnRestore.setPreferredSize(new Dimension(150, 30));
        btnRestore.addActionListener(this);

        // Build the panels:
        pnlTop.add(pnlImage, BorderLayout.NORTH);
        pnlTop.add(pnlTitle, BorderLayout.SOUTH);

        pnlTitle.add(lblTitle, BorderLayout.CENTER);
        pnlImage.add(lblImage);

        pnlLeft1.add(btnAddPlan);
        pnlLeft2.add(btnSearchUser);
        pnlLeft3.add(btnBackup);

        pnlRight1.add(btnAddUser);
        pnlRight2.add(btnHelp);
        pnlRight3.add(btnRestore);

        pnlLeft.add(pnlLeft1);
        pnlLeft.add(pnlLeft2);
        pnlLeft.add(pnlLeft3);

        pnlRight.add(pnlRight1);
        pnlRight.add(pnlRight2);
        pnlRight.add(pnlRight3);

        pnlMain.add(pnlTop, BorderLayout.NORTH);
        pnlMain.add(pnlLeft, BorderLayout.WEST);
        pnlMain.add(pnlRight, BorderLayout.EAST);

        con.add(pnlMain, BorderLayout.CENTER);



    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddPlan)
        {
            AddPlanGUI app = new AddPlanGUI();
            app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            app.setSize(600,400);
            app.setVisible(true);
        }
        if(e.getSource() == btnAddUser)
        {
            AddUserGUI app = new AddUserGUI();
            app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            app.setSize(400,600);
            app.setVisible(true);
        }
        if(e.getSource() == btnSearchUser)
        {
            SearchGUI app = new SearchGUI();
            app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            app.setSize(400,700);
            app.setVisible(true);
        }
        if(e.getSource() == btnBackup)
        {
            System.out.println("btnBackup clicked");
            System.out.println("list" + list.size());
            for (User user : list)
            {
                System.out.println(user.toString());
            }
            try
            {
                InputOutput.writeData("file_usersDB.bin", list);
                JOptionPane.showMessageDialog(null,"File: file_usersDB.bin created successfully");

            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
        }
        if(e.getSource() == btnHelp)
        {
            String document = "user_help.pdf";
            try{
                File f = new File(document);
                Desktop.getDesktop().open(f);
            }
            catch(IOException ioE){
                System.err.print(ioE.getMessage());
            }
        }
        if(e.getSource() == btnRestore)
        {
            try {
                showMembers();
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public ArrayList<User> createList()
    {
        ArrayList<User>list = new ArrayList<>();

        UserTableModel model = new UserTableModel();
        model.getDataFromDB();

        list = model.getList();

        return list;
    }

    public static void showMembers() throws IOException, ClassNotFoundException {
        String output = "Members: \n";
        ArrayList<User> userlist;
        userlist =  InputOutput.readData("file_usersDB.bin");

        for (User user: userlist)
            output += "\n" + user.toString() + "\n";

        System.out.println(userlist);
        System.out.println("Elements: " + userlist.size());
        System.out.println(output);

        JOptionPane.showMessageDialog(null,output);
    }
}

