
import java.io.*;
import java.util.ArrayList;

import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*; 

public class BottomMenu {

    public ArrayList<Image> item_images = new ArrayList<Image>();
    public String[] item_images_names;
    public String[] descriptions;
    public String[] names;
    public Runnable[] callbacks;
    public Integer[] item_n;
    public Integer[] prices;
    public String n_prefix;
    public Slider slider;

    public double virus_chances = 0.3;

    public Composite composite;
    public Boolean isVisible = true;

    public Button[] lbutton;
    public Label[] llabel;

    public BottomMenu(String[] _item_images_names, Integer[] _item_n, String _n_prefix, Integer[] _prices, String[] _descriptions,String[] _names, Runnable[] _callbacks) {
        
        item_images_names = _item_images_names;
        item_n = _item_n;
        n_prefix = _n_prefix;
        prices = _prices;
        descriptions = _descriptions;
        names = _names;
        callbacks = _callbacks;
    }

    public void updateLabel(int n, int index) {
        llabel[index].setText(n_prefix + " "  + n); 
    }

    

    public void show(Display display, Shell shell) {

        Font font = new Font(display,"Consolas", 15, SWT.BOLD);

        composite = new Composite(shell, SWT.BORDER); 
        composite.setBackground(new Color(display,0,0,0));
        composite.setSize(1200,200);
        composite.setLocation(400, 650);

        for (int i=0; i<item_images_names.length; i++) {
            Image tmp = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\"+ item_images_names[i] + ".png"));
            item_images.add(tmp);
        } 

        lbutton = new Button[item_n.length];
        llabel = new Label[item_n.length];
        
        

        for (int i=0;i<item_n.length;i++) {
            lbutton[i] = new Button(composite, SWT.BORDER); 
            lbutton[i].setImage(item_images.get(i));
            lbutton[i].setForeground(display.getSystemColor(SWT.COLOR_GREEN));
            lbutton[i].setBackground(new Color(display, 40,40,40));
            lbutton[i].setFont(font);
            lbutton[i].setBounds(200*i, 0, 200, 120); 
            String name = names[i];
            int price = prices[i];
            String description = descriptions[i];
            Runnable callback = callbacks[i];
            lbutton[i].addListener(SWT.Selection, new Listener() {
                public void handleEvent(Event e) {
                    Shopmenu shopmenu = new Shopmenu(display, shell, description, price , name, callback);
                    shopmenu.open(() -> {});
                    if (Math.random() < virus_chances) {
                        VirusPopup viruspopup = new VirusPopup(display, shell);
                        viruspopup.setBodyText("Your system has been affected by a virus. \nYou are losing precious lines of code as you\nread this. \nFight it before the damage is too big !");
                        viruspopup.setUpGame();
                        viruspopup.open(() -> {});
                    }
                }
            });


            llabel[i] = new Label(composite,SWT.None);
            llabel[i].setText(n_prefix + " "  + item_n[i]); 
            llabel[i].setFont(font);
            llabel[i].setForeground(display.getSystemColor(SWT.COLOR_GREEN));
            llabel[i].setBackground(new Color(display, 0,0,0));
            llabel[i].setLocation(200*i+60,120);
            llabel[i].setSize(100,50);

        }

        if (item_n.length > 6) {
            slider = new Slider(composite, SWT.BORDER);
            slider.setSize(1180,30);
            slider.setBackground(new Color(display,20,20,20));
            slider.setLocation(0,170);
            slider.setMinimum(1);
            slider.setMaximum(100);
    
            int scrollsize =(int) 100*1200/(200*item_n.length);
            slider.setThumb(scrollsize);
    
            slider.addListener(SWT.Selection,new Listener() {
                public void handleEvent(Event e) {
                    for (int i=0;i<item_n.length;i++) {
                        lbutton[i].setBounds(200*i-(200*item_n.length-1180)*(slider.getSelection()-1)/(99-scrollsize), 0, 200, 120); 
                        llabel[i].setLocation(200*i-(200*item_n.length-1180)*(slider.getSelection()-1)/(99-scrollsize)+60,120);
                    }
                }
            });
        }

        

        
    }

    public void setVisible(Boolean bool) {
        composite.setVisible(bool);

        for (int i=0;i<item_n.length;i++) {
            lbutton[i].setVisible(bool); 
            llabel[i].setVisible(bool); 
        }

    }

}
