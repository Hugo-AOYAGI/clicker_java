import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*; 
import org.eclipse.swt.widgets.*; 
import java.io.*;



public class SideMenu {

    Label txtscore;

    public String getFormattedScore(long score) {

        String[] ltaille = {"K","M","G","T","P"};
        String printablescore = Long.toString(score);

        for (int i=0;i<ltaille.length;i++) {
            if (Math.pow(10,3+3*i)<=score && score<Math.pow(10,3*i+6)) {
                double nbval = ((int) score/Math.pow(10,3+3*i));
                int nbint = Integer.toString((int) nbval).length();
                printablescore = printablescore.substring(0,nbint)+","+printablescore.substring(nbint,4)+ltaille[i];
            }
        }

        while (printablescore.length()<6) {printablescore = "0"+printablescore;}

        if (score>Math.pow(10,18)) {printablescore="????";}

        return printablescore;
    }

    public void show(Display display, Shell shell) {
        Font font = new Font(display,"Consolas", 25, SWT.BOLD);

        Composite composite = new Composite(shell, SWT.BORDER); 
        composite.setBackground(new Color(display,0,0,0)); 
        composite.setSize(400,900);
        

        txtscore = new Label(composite, SWT.NONE);
        txtscore.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        txtscore.setBackground(new Color(display, 0,0,0));
        txtscore.setSize(300,90);
        txtscore.setFont(new Font(display,"Consolas", 40, SWT.BOLD));
        txtscore.setLocation(70,50);

        Label icon = new Label(composite,SWT.NONE);
        Image image = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\logo.png")); 
        icon.setImage(image);
        icon.setLocation(0,0);
        icon.setSize(400,220);

        Button buttonfactory = new Button(composite, SWT.NONE); 
        buttonfactory.setText("Factories"); 
        buttonfactory.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonfactory.setBackground(new Color(display, 40,40,40));
        buttonfactory.setFont(font);
        buttonfactory.setBounds(2, 300, 390, 150); 

        Button buttonclick = new Button(composite, SWT.NONE); 
        buttonclick.setText("Power ups"); 
        buttonclick.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonclick.setBackground(new Color(display, 40,40,40));
        buttonclick.setFont(font);
        buttonclick.setBounds(2, 450 , 390, 150);

        Button buttonbonus = new Button(composite, SWT.NONE); 
        buttonbonus.setText("Upgrades"); 
        buttonbonus.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonbonus.setBackground(new Color(display, 40,40,40));
        buttonbonus.setFont(font);
        buttonbonus.setBounds(2, 600, 390, 150);
         
    }

    public void updateScore(long score) {
        txtscore.setText(getFormattedScore(score));
    }

    public void updateScore(int score) {
        txtscore.setText(getFormattedScore((long) score));
    }

}