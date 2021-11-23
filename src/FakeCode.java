import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;


public class FakeCode {

    static int char_index = 0;
    static int line_index = 0;

    static final int max_lines = 20;
    public int speed = 20;

    static ArrayList<String> fake_code;

    public Label textfield;


    public static ArrayList<String> fileToList(String fileName) throws Exception {
        ArrayList<String> list = new ArrayList<String>();
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            list.add(st);

        return list;
    }

    public static void addCharToTextField(Label textfield, int speed) {

        String curr_line = fake_code.get(line_index);

        if (line_index == fake_code.size() - 1) {
            line_index = 0;
            char_index = 0;
        }

        if (curr_line.length() == 0) {

            line_index++;
            textfield.setText(textfield.getText() + "\n");

            if (line_index > max_lines) {
                String tmp = textfield.getText();
                textfield.setText(tmp.substring(tmp.indexOf("\n") + 1));
            }


        } else {

            textfield.setText(textfield.getText() + curr_line.substring(char_index, Math.min(char_index + speed, curr_line.length())));
            char_index += speed;

            if (char_index >= curr_line.length()) {

                line_index++;
                char_index = 0;

                if (line_index > max_lines) {
                    String tmp = textfield.getText();
                    textfield.setText(tmp.substring(tmp.indexOf("\n") + 1));
                }

                textfield.setText(textfield.getText() + "\n");

                

            }
        }

        
        
    }

    public FakeCode(Label label) {
        textfield = label;
        
    }

    public static void main(String[] args) {
        try {
            fake_code = fileToList("C:\\Users\\aoyag\\Desktop\\Codes\\Java\\clicker\\assets\\fakecode.txt");
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        
    }

}
