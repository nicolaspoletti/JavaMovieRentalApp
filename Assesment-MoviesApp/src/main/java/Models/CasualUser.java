package Models;

public class CasualUser extends User
{
    private String movieTitle;

    public String getOwnedMovies() {
        return movieTitle;
    }

    public void setmovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public CasualUser(int userID, String userName, String userMail, String userCountry, int userPlanID, String movieTitle) {
        super(userID, userName, userMail, userCountry, userPlanID);
        this.movieTitle = movieTitle;
    }
}
