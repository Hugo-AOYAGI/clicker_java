
import java.awt.Color;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.MessageBox;


public class App {
    static Boolean gameStarted = false;
    static long score = 0L;
    static int mousemodifier = 3;
    static int factories = 0;
    static SideMenu sidemenu;
    static FakeCode fakecode;

    static Integer[] factories_n = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    static String[] factories_names = {"urbutt", "factyou", "factdev", "facttea", "factcat", "factcom", "redditor", "facthac", "factai"};
    static Integer[] factories_prices = {10,100,500,1000,2000,10000,100000,100000000,100000000};
    static String[] factories_rnames = {"Ur Butt", "A clone of you", "A simple dev", "A team of devs", "My cat", "A Software company", "A redditor", "A Master Hacker", "A next gen AI"};
    static String[] factories_descriptions = {"Stop using your butt to write code.\nProduces 0 chars per seconds.", "A clone of you that writes code as\nfast as you do (slowly).\nProduces 1 char per seconds.", "The dev is more efficient.\nProduces 2 chars per seconds.", "A Team of 3 devs, but writes more code than the sum of its parts.\nProduces 10 chars per seconds.", "My cat is better than a team of devs.\nProduces 30 chars per seconds.", "f", "g", "h", "i"};
    static Runnable[] factories_callbacks = {() -> {addFactory(0, 10, 0);}, () -> {addFactory(1, 100, 1);}, () -> {addFactory(2, 500, 2);}, () -> {addFactory(8, 1000, 3);}, () -> {addFactory(10, 2000, 4);}, () -> {addFactory(100, 10000, 5);}, () -> {addFactory(500, 100000, 6);}, () -> {addFactory(1000, 100000000, 7);}, () -> {addFactory(100000, 1000000000, 8);}};

    static Integer[] power_up_n = {0, 0, 0};
     
    static String[] power_up_names = {"p1cursor", "p10fact", "antivirus"};
    static Integer[] power_up_prices = {1,10,100};
    static String[] power_up_descriptions = {"Each click you do earns you one more character", "Your factories are 10% more efficient.", "You have 10% less chances of getting a virus while shopping."};
    static String[] power_up_rnames = {"New Cursor", "Upgraded factories", "Antivirus"};
    static Runnable[] power_up_callbacks = {() -> {}, () -> {}, () -> {}};


    static Shell shell;


    public static void addFactory(int cps, int price, int index) {
        if ((long) price <= score) {
            factories += cps;
            score -= price;
            sidemenu.updateScore(score);
        } else {
            MessageBox message_box = new MessageBox(shell, SWT.CLOSE | SWT.ICON_WARNING);
            message_box.setText("Shop");
            message_box.setMessage("You do not have enough lines of code to buy this !");
            message_box.open();
            
        }
        

    }

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    public static void factoriesLoop() {
        System.out.println("Loop");
        if (factories == 0) {
            setTimeout(() -> {factoriesLoop();}, 1000);
            return;
        }
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                if (factories < 100) {
            
                    int delay = 1000/factories;
                    score += (long) (factories / (1000 / delay));
                    fakecode.addCharToTextField((factories / (1000 / delay)));
                    sidemenu.updateScore(score);
                    setTimeout(() -> {factoriesLoop();}, delay);
                } else {
                    score += (long) (factories / 100);
                    fakecode.addCharToTextField((factories / 100));
                    sidemenu.updateScore(score);
                    setTimeout(() -> {factoriesLoop();}, 10);
                }
            }
        });
        
        
    }

    public static void main(String[] args) throws Exception {
        Display display = new Display();
        shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        shell.setText("dev_clicker");
        shell.setSize(1600, 900);
        shell.setLocation(0, 0);

        sidemenu = new SideMenu();
        sidemenu.show(display, shell);
        sidemenu.updateScore(score);

        

        BottomMenu powerups = new BottomMenu(power_up_names, power_up_n, "lvl", power_up_prices,power_up_descriptions,power_up_rnames, power_up_callbacks );
        powerups.show(display, shell);

      
        BottomMenu factories = new BottomMenu(factories_names, factories_n, "x",factories_prices,factories_descriptions,factories_rnames, factories_callbacks);
        factories.show(display, shell);

        sidemenu.buttonfactory.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                powerups.setVisible(false);
            }
        });

        sidemenu.buttonclick.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event e) {
                powerups.setVisible(true);
            }
        });
      

        fakecode = new FakeCode();
        fakecode.show(display, shell);

        fakecode.textfield.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (!gameStarted) {
                    gameStarted = true;
                    fakecode.textfield.setText("");
                }
                super.mouseUp(e);
                score += mousemodifier;
                fakecode.addCharToTextField(mousemodifier);
                sidemenu.updateScore(score);
            }
        });

        factoriesLoop();

        shell.open();

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }

        System.exit(0);
        display.dispose();

    }
}
