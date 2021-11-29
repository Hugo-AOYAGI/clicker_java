import org.eclipse.swt.*;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;


public class FakeCode {

    static int char_index = 0;
    static int line_index = 0;

    static final int max_lines = 25;
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

        br.close();
        return list;
    }

    public void addCharToTextField(int speed) {
        String curr_line = fake_code.get(line_index);
        if (char_index + speed >= curr_line.length()) {
            writeChars(curr_line.length()-char_index);
            int lspeed = (int) (char_index + speed)/15;
            if (lspeed>5) {lspeed = 5;};
            
            writeLines(lspeed);
        } else {
            writeChars(speed);
        }

    }

    public void writeLines(int lspeed) {
        
        
        if (lspeed+line_index>=fake_code.size() - 1) {
            this.textfield.setText("");
            line_index = 0;
            char_index = 0;
        }
        String tmp = this.textfield.getText();
        for (int i=0;i<lspeed;i++) {
            if (line_index+1>max_lines) {
            tmp = tmp.substring(tmp.indexOf("\n") + 1);
            }
            tmp+= fake_code.get(line_index)+  "\n";
            line_index++;
        }
        this.textfield.setText(tmp);
    }
    public void writeChars(int speed) {
        
        String curr_line = fake_code.get(line_index);

        if (line_index == fake_code.size() - 1) {
            this.textfield.setText("");
            line_index = 0;
            char_index = 0;
        }

        String tmp = this.textfield.getText();
        tmp += curr_line.substring(char_index, char_index + speed);
        this.textfield.setText(tmp);
        char_index += speed;

        if (char_index >= curr_line.length()) {

            line_index++;
            char_index = 0;

            if (line_index > max_lines) {
                tmp = tmp.substring(tmp.indexOf("\n") + 1);
            }

            this.textfield.setText(tmp + "\n");
        }
        

        
        
    }

    public void show(Display display, Shell shell) throws Exception {

        textfield = new Label(shell, SWT.NONE);
        textfield.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        textfield.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        textfield.setText(Files.readString(Path.of(new File("").getAbsolutePath().concat("\\assets\\console_welcome.txt"))));

        Font font = new Font(display, "Consolas", 12, SWT.NONE);
        textfield.setFont(font);
        textfield.setSize(1200, 650);
        textfield.setLocation(400, 0);

    }

    public FakeCode() {
        try {
            fake_code = fileToList((new File("").getAbsolutePath()).concat("\\assets\\fakecode.txt"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }


}
