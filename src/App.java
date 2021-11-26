
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.MessageBox;


public class App {
    static Boolean gameStarted = false;
    static long score = 0L;
    static int mousemodifier = 1;
    static double factories_modifier = 1;
    static int factories_cps = 0;
    static SideMenu sidemenu;
    static FakeCode fakecode;
    static BottomMenu factories;
    static BottomMenu powerups;
    static boolean virusActive = false;

    

    static List<Integer> factories_n = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);
    static Integer[] factories_prod = {0, 1, 2, 10, 30, 100, 500, 1000, 10000};

    static String[] factories_names = {"urbutt", "factyou", "factdev", "facttea", "factcat", "factcom", "redditor", "facthac", "factai"};
    static Integer[] factories_prices = {10,100,500,1000,2000,10000,100000,100000000,100000000};
    static String[] factories_rnames = {"Ur Butt", "A clone of you", "A simple dev", "A team of devs", "My cat", "A Software company", "A redditor", "A Master Hacker", "A next gen AI"};
    static String[] factories_descriptions = {"Stop using your butt to write code.\nProduces 0 chars per seconds.", "A clone of you that writes code as\nfast as you do (slowly).\nProduces 1 char per seconds.", "The dev is more efficient.\nProduces 2 chars per seconds.", "A Team of 3 devs, but writes more \ncode than the sum of its parts.\nProduces 10 chars per seconds.", "My cat is better than a team of\ndevs.\nProduces 30 chars per seconds.", "The software company produces 100\nchars per seconds.", "The redditor just copies code from\nsubreddits and stackoverflow.\nProduces 500 chars per seconds.", "The master hacker produces 1000\nchars per seconds.", "The AI is taking all the devs jobs\nthanks to its magic SSD.\nProduces 10000 chars per seconds."};
    static Runnable[] factories_callbacks = {() -> {addFactory(0, 10, 0);}, () -> {addFactory(1, 100, 1);}, () -> {addFactory(2, 500, 2);}, () -> {addFactory(8, 1000, 3);}, () -> {addFactory(10, 2000, 4);}, () -> {addFactory(100, 10000, 5);}, () -> {addFactory(500, 100000, 6);}, () -> {addFactory(1000, 100000000, 7);}, () -> {addFactory(100000, 1000000000, 8);}};

    static List<Integer> power_up_n = Arrays.asList(0, 0, 0);
     
    static String[] power_up_names = {"p1cursor", "p10fact", "antivirus"};
    static Integer[] power_up_prices = {100,1000,1000};
    static String[] power_up_descriptions = {"Each click you do earns you one\nmore character", "Your factories are 10% more\nefficient.", "You have 10% less chances of\ngetting a virus while shopping."};
    static String[] power_up_rnames = {"New Cursor", "Upgraded factories", "Antivirus"};
    static Runnable[] power_up_callbacks = {() -> {addCursor();}, () -> {factoriesModify();}, () -> {}};


    static Shell shell;

    public static void addCursor() {
        if (100 <= score) {
            mousemodifier++;
            score -= 100;
            sidemenu.updateScore(score);
            power_up_n.set(0, power_up_n.get(0) + 1);
            powerups.updateLabel(power_up_n.get(0), 0);
        } else {
            MessageBox message_box = new MessageBox(shell, SWT.CLOSE | SWT.ICON_WARNING);
            message_box.setText("Shop");
            message_box.setMessage("You do not have enough lines of code to buy this !");
            message_box.open();
        }
    } 

    public static void addAntiVirus() {
        if (1000 <= score) {
            BottomMenu.virus_chances *= 0.9;
            score -= 1000;
            sidemenu.updateScore(score);
            power_up_n.set(2, power_up_n.get(2) + 1);
            powerups.updateLabel(power_up_n.get(2), 2);
        } else {
            MessageBox message_box = new MessageBox(shell, SWT.CLOSE | SWT.ICON_WARNING);
            message_box.setText("Shop");
            message_box.setMessage("You do not have enough lines of code to buy this !");
            message_box.open();
        }
    } 


    public static void factoriesModify() {
        if (1000 <= score) {
            factories_modifier += 0.1;
            score -= 1000;
            sidemenu.updateScore(score);
            power_up_n.set(1, power_up_n.get(1) + 1);
            powerups.updateLabel(power_up_n.get(1), 1);
        } else {
            MessageBox message_box = new MessageBox(shell, SWT.CLOSE | SWT.ICON_WARNING);
            message_box.setText("Shop");
            message_box.setMessage("You do not have enough lines of code to buy this !");
            message_box.open();
        }
    } 


    public static void addFactory(int cps, int price, int index) {
        if ((long) price <= score) {
            factories_cps += cps;
            score -= price;
            sidemenu.updateScore(score);
            factories_n.set(index, factories_n.get(index) + 1);
            factories.updateLabel(factories_n.get(index), index);
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
        if (factories_cps == 0 || !gameStarted) {
            setTimeout(() -> {factoriesLoop();}, 1000);
            return;
        }
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                if (factories_cps < 100) {
            
                    int delay = 1000/factories_cps;
                    score += (long) ((factories_cps / (1000 / delay))*factories_modifier);
                    fakecode.addCharToTextField((int) ((factories_cps / (1000 / delay))*factories_modifier));
                    sidemenu.updateScore(score);
                    setTimeout(() -> {factoriesLoop();}, delay);
                } else {
                    score += (long) ((factories_cps / 100)*factories_modifier);
                    fakecode.addCharToTextField((int) ((factories_cps / 100)*factories_modifier));
                    sidemenu.updateScore(score);
                    setTimeout(() -> {factoriesLoop();}, 10);
                }
            }
        });
        
        
    }

    public static void main(String[] args) throws Exception {

        // Reading saved data
        ArrayList<String> user_data = new ArrayList<String>();
        File file = new File((new File("").getAbsolutePath()).concat("\\save.txt"));
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
        
            user_data.add(st);


        factories_n = Arrays.asList(user_data.get(0).trim().split(",")).stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());
        power_up_n = Arrays.asList(user_data.get(1).trim().split(",")).stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());
        score = Integer.parseInt(user_data.get(2).trim());
        mousemodifier = Integer.parseInt(user_data.get(3).trim());
        factories_modifier = Double.parseDouble(user_data.get(4).trim());
        BottomMenu.virus_chances = Double.parseDouble(user_data.get(5).trim());
        
        for (int i = 0; i<factories_n.size(); i++) {
            factories_cps += factories_prod[i]*factories_n.get(i);
        }


        br.close();

        
        

        Display display = new Display();
        shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        shell.setText("dev_clicker");
        shell.setSize(1600, 900);
        shell.setLocation(0, 0);

        sidemenu = new SideMenu();
        sidemenu.show(display, shell);
        sidemenu.updateScore(score);

        

        powerups = new BottomMenu(power_up_names, power_up_n, "lvl", power_up_prices,power_up_descriptions,power_up_rnames, power_up_callbacks );
        powerups.show(display, shell);

      
        factories = new BottomMenu(factories_names, factories_n, "x",factories_prices,factories_descriptions,factories_rnames, factories_callbacks);
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

        shell.addListener(SWT.Close, new Listener() {
            public void handleEvent(Event e) {
                try {
                    Files.writeString(Path.of("save.txt"), (String.join(",", Arrays.asList(factories_n).stream().map(i -> i.toString()).collect(Collectors.toList()) ) + "\n" +String.join(",", Arrays.asList(power_up_n).stream().map(i -> i.toString()).collect(Collectors.toList()) ) + "\n" + score + "\n" + mousemodifier + "\n" + factories_modifier + "\n" + BottomMenu.virus_chances).replace("[", "").replace("]", "").replace(" ", ""));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

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