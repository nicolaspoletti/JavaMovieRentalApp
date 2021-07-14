package Program;

import FrontEnd.MainGUI;

import javax.swing.*;

/**
 * @author Nicolas Poletti
 * @subject: Apply Skills in Object-Oriented Design [15441]
 *
 * This program allows to connect to a DB and perform CRUD operations on the records
 * using a java.swing front-end.
 */


public class MoviesApp
{
    public static void main(String[] args) {
        MainGUI app = new MainGUI();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(600,400);
        app.setVisible(true);
    }
}
