package Models;

import DBConnection.ConnectionDetails;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class SearchUserTableModel extends AbstractTableModel
{
    private ArrayList<User> list = new ArrayList<>();
    private String[] columnNames = {"ID", "Username", "Email", "Country", "Plan ID", "Subscription Type", "Rented Movie"};


    public SearchUserTableModel(String username) {
        getSearchDataFromDB(username);
    }



    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        User user = list.get(rowIndex);

        if (user instanceof CasualUser)
        {
            switch (columnIndex)
            {
                case 0: return user.getUserID();
                case 1: return user.getUserName();
                case 2: return user.getUserMail();
                case 3: return user.getUserCountry();
                case 4: return user.getUserPlanID();
                case 5: return "Casual";
                case 6: return ((CasualUser) user).getOwnedMovies();
            }
        }

        if (user instanceof SubscriptionUser)
        {
            switch (columnIndex)
            {
                case 0: return user.getUserID();
                case 1: return user.getUserName();
                case 2: return user.getUserMail();
                case 3: return user.getUserCountry();
                case 4: return user.getUserPlanID();
                case 5: return ((SubscriptionUser) user).getSubscriptionType();
                case 6: return "All";
            }
        }


        return null;
    }

    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }

    public User getRow(int row)
    {
        User user = list.get(row);
        return user;
    }

    public void getSearchDataFromDB(String username)
    {
        Connection con;
        Statement stmt;
        ResultSet rs = null;

        try
        {

            System.out.println("SearchUserTableModel.getSearchDataFromDB("+ username +")");
            con = ConnectionDetails.getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String strSQL = "SELECT * FROM tbl_users WHERE user_name = '"+ username +"'";
            rs = stmt.executeQuery(strSQL);

            list.clear();

            if(rs.next() == false)
            {
                JOptionPane.showMessageDialog(null, "No user found");
            }

            rs.beforeFirst();

            while (rs.next())
            {
                if(rs.getInt("fk_plan_id") != 0)
                {
                    list.add(new SubscriptionUser(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_country"), 1, "On Plan" ));
                }
                else
                    list.add(new CasualUser(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_email"), rs.getString("user_country"), rs.getInt("fk_plan_id"), "StarWars"));

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
