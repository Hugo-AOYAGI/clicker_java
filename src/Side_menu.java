import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*; 
import org.eclipse.swt.widgets.*; 
import java.io.*;



public class Side_menu {
    public String setscore(long score) {

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

    public void show(Display display, Shell shell, String score) {
        Font font = new Font(display,"Consolas", 30, SWT.BOLD);

        Composite composite = new Composite(shell, SWT.BORDER); 
        composite.setBackground(new Color(display,0,0,0)); 
        composite.setSize(400,1080);
        

        Label txtscore = new Label(composite, SWT.NONE);
        txtscore.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        txtscore.setBackground(new Color(display, 0,0,0));
        txtscore.setText(score);
        txtscore.setSize(300,90);
        txtscore.setFont(new Font(display,"Consolas", 50, SWT.BOLD));
        txtscore.setLocation(70,50);

        Label imgscore = new Label(composite,SWT.NONE);
        Image image = new Image(display, (new File("").getAbsolutePath()).concat("\\Sans titre 1.png")); 
        imgscore.setImage(image);
        imgscore.setLocation(0,0);
        imgscore.setSize(400,220);

        Button buttonfactory = new Button(composite, SWT.BORDER); 
        buttonfactory.setText("Factories"); 
        buttonfactory.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonfactory.setBackground(new Color(display, 40,40,40));
        buttonfactory.setFont(font);
        buttonfactory.setBounds(2, 220, 390, 250); 

        Button buttonclick = new Button(composite, SWT.BORDER); 
        buttonclick.setText("Power ups"); 
        buttonclick.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonclick.setBackground(new Color(display, 40,40,40));
        buttonclick.setFont(font);
        buttonclick.setBounds(2, 500 , 390, 250);

        Button buttonbonus = new Button(composite, SWT.BORDER); 
        buttonbonus.setText("Upgrades"); 
        buttonbonus.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonbonus.setBackground(new Color(display, 40,40,40));
        buttonbonus.setFont(font);
        buttonbonus.setBounds(2, 780, 390, 250);
         
    }


    public static void main(String[] args) throws Exception {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Menu Example");
        shell.setSize(300, 200);
    
        Side_menu sidemenu = new Side_menu();

        long score = 25L;
        String printablescore = sidemenu.setscore(score);
        sidemenu.show(display, shell,printablescore);

        shell.pack(); 
        shell.open();
        

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();
    }
}