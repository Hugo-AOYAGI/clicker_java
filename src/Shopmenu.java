
import java.io.*;

import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*; 
import org.eclipse.swt.widgets.*; 




public class Shopmenu {

    static String description;
    static int price;
    static String name;
    static Shell parent_shell;
    static Display display;

    static Shell pop_shell; 


    public Shopmenu(Display _display, Shell _parent_shell, String _description, int _price, String _name) {
        description = _description;
        price = _price;
        name = _name;
        parent_shell = _parent_shell;
        display = _display;

        pop_shell = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        pop_shell.setSize(600, 600);
        
        pop_shell.setText(name);
        pop_shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        setUpWindow();

        
    }

    public static void setUpWindow() {
        Font font = new Font(display, "Consolas", 30, SWT.NONE);

        Label bodylabel = new Label(pop_shell, SWT.NONE);
        bodylabel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        bodylabel.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        bodylabel.setText(description);
        bodylabel.setSize(600, 60);
        bodylabel.setFont(font);

        Label pricelabel = new Label(pop_shell, SWT.NONE);
        bodylabel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        bodylabel.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        bodylabel.setText("Price : "+price);
        bodylabel.setSize(600, 60);
        bodylabel.setLocation(0,200);
        bodylabel.setFont(font);

        Button buttonbuy = new Button(pop_shell, 0);
        buttonbuy.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonbuy.setBackground(new Color(display, 40,40,40));
        buttonbuy.setText("BUY");
        buttonbuy.setBounds(0, 353 ,291, 200);
        buttonbuy.setFont(font);
        buttonbuy.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                App.mousemodifier++;
            }
        });

        Button buttoncancel = new Button(pop_shell, 0);
        buttoncancel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttoncancel.setBackground(new Color(display, 40,40,40));
        buttoncancel.setText("CANCEL");
        buttoncancel.setBounds(291, 353, 291, 200);
        buttoncancel.setFont(font);
        buttoncancel.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                pop_shell.close();
            }
        });
    }


    public void open(Runnable callOnClose) {
        pop_shell.open();

        while(!pop_shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }

        callOnClose.run();
    }

    public static void main(String[] args) {

        Display display = new Display();
        Shell shell = new Shell(display, SWT.NONE);

        Shopmenu shopmenu = new Shopmenu(display, shell,"dtddtddtdtddtddtdddtdtddtddtdddtdtd\n dtddtddtddtddtdddtdtddtddt \ndddtdtddtddtdddtdtddtddtddtddtddtdd",10,"name");

        shell.open();

        shopmenu.open(() -> {System.out.println("Test");});

    }
    
}
