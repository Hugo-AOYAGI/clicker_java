

import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*; 
import org.eclipse.swt.widgets.*; 




public class Shopmenu {

    static String description;
    static int price;
    static Runnable callback;
    static String name;
    static Shell parent_shell;
    static Display display;

    static Shell pop_shell; 


    public Shopmenu(Display _display, Shell _parent_shell, String _description, int _index, String _name, Runnable _callback, int type) {
        description = _description;
        if (type ==0) {price = App.powerups.prices[_index];}
        else {price = App.factories.prices[_index];};
        
        name = _name;
        parent_shell = _parent_shell;
        display = _display;
        callback = _callback;

        pop_shell = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        pop_shell.setSize(600, 400);
        
        pop_shell.setText(name);
        pop_shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        setUpWindow();

        
    }

    public static void setUpWindow() {
        Font font = new Font(display, "Consolas", 15, SWT.NONE);

        Label bodylabel = new Label(pop_shell, SWT.NONE);
        bodylabel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        bodylabel.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        bodylabel.setText(name + " :\n" + description);
        bodylabel.setSize(600, 180);
        bodylabel.setFont(font);

        Label pricelabel = new Label(pop_shell, SWT.NONE);
        pricelabel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        pricelabel.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        pricelabel.setText("Price : "+price);
        pricelabel.setSize(600, 60);
        pricelabel.setLocation(0,180);
        pricelabel.setFont(font);

        Button buttonbuy = new Button(pop_shell, 0);
        buttonbuy.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttonbuy.setBackground(new Color(display, 40,40,40));
        buttonbuy.setText("BUY");
        buttonbuy.setBounds(0, 250 ,291, 100);
        buttonbuy.setFont(font);
        buttonbuy.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                callback.run();
                pop_shell.close();
            }
        });

        Button buttoncancel = new Button(pop_shell, 0);
        buttoncancel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        buttoncancel.setBackground(new Color(display, 40,40,40));
        buttoncancel.setText("CANCEL");
        buttoncancel.setBounds(291, 250, 291, 100);
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

    
}
