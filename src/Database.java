import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.*;
import java.time.*;
import java.util.*;

public class Database {
    private File file=null;
    private FileWriter fWrite=null; private FileReader fRead=null; private Scanner scan=null;
    private Vector<String> row, column;
    public Database() {
    }
    /**parameterized constructor w/c sets the filename of a textfile
     * @author martzel baste
     * @param filename */
    public Database(String filename) {
        file = new File(filename);
    }
    /**another option for setting a filename * @author martzel baste
    * @param filename*/
    public void setFilename(String filename) {
        new Database(filename);
    }
    /**when you want to read or get the name of the file * @return String - name of the file */
    public String getFilename() { return file.getName(); }
    /**universal error message * @param msg */
    public void errorMessage(String msg){
        JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
    }
    /**Store information to a file
     * @param records - the information to be stored */
    public void storeToFile(String records){
        try {
            fWrite=new FileWriter(file);
            fWrite.write(records);
            fWrite.flush();
        } catch (Exception e) {
            errorMessage("Error 101: storeToFile\n" + e.getMessage());
        }
    }//end of storeToFile

    public void displayRecords(DefaultTableModel model){
        try {
            fRead = new FileReader(file);
            scan = new Scanner(fRead);
            //array object use to extract data
            String data[];
            while (scan.hasNext()) {
                //extract each value via # delimiter
                data = scan.nextLine().split("#");
                row = new Vector<String>();
                for (int i = 0; i < model.getColumnCount(); i++) {
                    //read each record based on column count of a table-store to a vector
                    row.add(data[i]);
                } //end of for
                model.addRow(row); //adds record to table
            } //end of while
        }catch (Exception e) {
            errorMessage("Error 102: displayRecords\n" + e.getMessage());
        }

    }

    public void fillToComboBox (JComboBox<String> combo) {
        try {
            fRead=new FileReader(file);
            scan=new Scanner(fRead);
            while(scan.hasNext()) {
                combo.addItem(scan.nextLine());
            }//end of while
        } catch (Exception e) {
            errorMessage ("Error 103: fillToCOmboBox\n"+e.getMessage());
        }
    }//end of try
}
