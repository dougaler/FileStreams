import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileMaster {

    static String objName;
    static String objDesc;
    static String objID;
    static Double objCost;
    static RandomAccessFile randAccFile;
    static Boolean isWritten;
    static File objFile = new File("C:\\Users\\legol\\IdeaProjects\\FileStreams\\src\\product.txt");

    public static void setRAF(String name, String description, String id, Double cost) {
        objName = name;
        objDesc = description;
        objID = id;
        objCost = cost;
        do{
            objName = objName + " ";
        }while (objName.length() < 35);

        do{
            objDesc = objDesc + " ";
        }while (objDesc.length() < 75);

        do{
            objID = objID + " ";
        }while (objID.length() < 6);


    }

    public static Boolean getRAF()
    {
        try {
            randAccFile = new RandomAccessFile(objFile, "rw");
            randAccFile.seek(randAccFile.length());
            randAccFile.writeBytes(objID  + objName + objDesc + String.format("%.2f", objCost) + "\n");
            randAccFile.close();
            isWritten = true;
        } catch (IOException e) {
            e.printStackTrace();
            isWritten = false;
        }
        return isWritten;
    }

}
