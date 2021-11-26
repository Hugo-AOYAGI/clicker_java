
import java.awt.Color;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.*;


import javazoom.jl.player.*;
import java.io.FileInputStream;

public class App {
    static Boolean gameStarted = false;
    static long score = 0L;
    static int mousemodifier = 1;
    public static void main(String[] args) throws Exception {
        Display display = new Display();
        Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        shell.setText("dev_clicker");
        shell.setSize(1600, 900);
        shell.setLocation(0, 0);

        SideMenu sidemenu = new SideMenu();
        sidemenu.show(display, shell);
        sidemenu.updateScore(score);

        Integer[] power_up_n = {3, 4, 4};
        String[] power_up_names = {"p1cursor", "p10fact", "antivirus"};
        Integer[] power_up_prices = {1,10,100};
        String[] power_up_descriptions = {"a", "b", "c"};
        String[] power_up_rnames = {"a", "b", "c"};

        BottomMenu powerups = new BottomMenu(power_up_names, power_up_n, "lvl", power_up_prices,power_up_descriptions,power_up_rnames );
        powerups.show(display, shell);

        Integer[] factories_n = {10, 12, 9, 23, 2, 0, 0, 0, 0};
        String[] factories_names = {"urbutt", "factyou", "factdev", "facttea", "factcat", "factcom", "redditor", "facthac", "factai"};
        Integer[] factories_prices = {1,10,100,1000,10000,1000000,10000,10000,100000};
        String[] factories_rnames = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
        String[] factories_descriptions = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};

        BottomMenu factories = new BottomMenu(factories_names, factories_n, "x",factories_prices,factories_descriptions,factories_rnames);
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
      

        FakeCode fakecode = new FakeCode();
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

        shell.open();
        class Play extends Thread  {
            public Play() {
                super();
            }
            public void run() {
                try {    
                    Music music = new Music();
                    music.playMP3.play();
                    while (!shell.isDisposed()) {
                        if (music.playMP3.isComplete()) {
                            music = new Music();
                            music.playMP3.play();
                        }
                        
                    }
                     music.playMP3.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        Play playmusic = new Play();
        playmusic.start();

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

    }
}
