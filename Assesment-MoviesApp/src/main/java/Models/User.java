package Models;

import java.io.Serializable;

public class User implements Serializable
{
    private int userID;
    private String userName;
    private String userMail;
    private String userCountry;
    private int userPlanID;

    public User() {

    }

    public User(int userID, String userName, String userMail, String userCountry, int userPlanID) {
        this.userID = userID;
        this.userName = userName;
        this.userMail = userMail;
        this.userCountry = userCountry;
        this.userPlanID = userPlanID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public int getUserPlanID() {
        return userPlanID;
    }

    public void setUserPlanID(String userCountry) {
        this.userPlanID = userPlanID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userCountry='" + userCountry + '\'' +
                '}';
    }
}
