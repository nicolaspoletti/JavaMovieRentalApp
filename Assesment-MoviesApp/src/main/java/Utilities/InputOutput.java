package Utilities;

import Models.User;

import java.io.*;
import java.util.ArrayList;

public class InputOutput
{
    public static void writeData(String fileName, ArrayList<User> list) throws
            FileNotFoundException, NotSerializableException, IOException
    {
        FileOutputStream fos = new FileOutputStream(fileName, true);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(list);

        if(oos != null)
            oos.close();
    }

    public static ArrayList<User> readData(String fileName) throws
            FileNotFoundException, NotSerializableException, IOException, ClassNotFoundException
    {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        ArrayList<User> list = null;

        list = (ArrayList<User>) ois.readObject();

        ois.close();

        return list;
    }
}
