
import java.io.*;

import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*; 
import org.eclipse.swt.widgets.*; 

public class BottomMenu {
    

    public void show(Display display, Shell shell) {
        Font font = new Font(display,"Consolas", 25, SWT.BOLD);

        Composite composite = new Composite(shell, SWT.BORDER); 
        composite.setBackground(new Color(display,0,0,0)); 
        composite.setSize(1200,200);
        composite.setLocation(400, 650);
        
        Slider slider = new Slider(composite, SWT.BORDER);
        slider.setSize(1180,30);
        slider.setBackground(new Color(display,20,20,20));
        slider.setLocation(0,170);
        slider.setMinimum(1);
        slider.setMaximum(100);
        

        String[] lmenu = {"factory 1","factory 2","factory 3","factory 4","factory 5","factory 6","factory 7","factory 8","factory 9"};
        
        Image image1 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\logo.png")); 
        Image image2 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\factyou.png")); 
        Image image3 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\factdev.png")); 
        Image image4 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\facttea.png")); 
        Image image5 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\factcat.png")); 
        Image image6 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\factcom.png")); 
        Image image7 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\logo.png")); 
        Image image8 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\facthac.png")); 
        Image image9 = new Image(display, (new File("").getAbsolutePath()).concat("\\assets\\factai.png")); 
        
        Image[] limage = {image1,image2,image3,image4,image5,image6,image7,image8,image9};
        int buttonnb = lmenu.length;
        Button[] lbutton = new Button[buttonnb];
        Label[] llabel = new Label[buttonnb];
        int[] lquantity = {0,0,0,0,0,0,0,0,0};
        int scrollsize =(int) 100*1200/(200*buttonnb);
        slider.setThumb(scrollsize);
        

        for (int i=0;i<buttonnb;i++) {
            lbutton[i] = new Button(composite, SWT.BORDER); 
            lbutton[i].setImage(limage[i]);
            lbutton[i].setForeground(display.getSystemColor(SWT.COLOR_GREEN));
            lbutton[i].setBackground(new Color(display, 40,40,40));
            lbutton[i].setFont(font);
            lbutton[i].setBounds(200*i, 0, 200, 120); 

            llabel[i] = new Label(composite,SWT.None);
            llabel[i].setText("x"+lquantity[i]); 
            llabel[i].setFont(font);
            llabel[i].setForeground(display.getSystemColor(SWT.COLOR_GREEN));
            llabel[i].setBackground(new Color(display, 0,0,0));
            llabel[i].setLocation(200*i+60,120);
            llabel[i].setSize(100,50);

        }

        slider.addListener(SWT.Selection,new Listener() {
            public void handleEvent(Event e) {
                for (int i=0;i<buttonnb;i++) {
                    lbutton[i].setBounds(200*i-(200*buttonnb-1180)*(slider.getSelection()-1)/(99-scrollsize), 0, 200, 120); 
                    llabel[i].setLocation(200*i-(200*buttonnb-1180)*(slider.getSelection()-1)/(99-scrollsize)+60,120);
                }
            }
        });

        
    }

}
