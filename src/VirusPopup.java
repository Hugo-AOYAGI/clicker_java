import java.io.File;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class VirusPopup {

    static Shell parent_shell;
    static Display display;

    static int disp_width;
    static int disp_height;

    static Shell pop_shell; 

    static int damage = 0;

    static GC gc;

    public VirusPopup(Display _display, Shell _parent_shell) {
        parent_shell = _parent_shell;
        display = _display;

        pop_shell = new Shell(display, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
        pop_shell.setSize(600, 600);
        
        pop_shell.setText("Virus Detected");
        pop_shell.setBackground(display.getSystemColor(SWT.COLOR_BLACK));

        Monitor monitor = display.getPrimaryMonitor();
        Rectangle rect;
        if (monitor != null) {
            rect = monitor.getClientArea();
        } else {
            rect = display.getBounds();
        }

        disp_width = rect.width;
        disp_height = rect.height;
        
    }

    public void setBodyText(String bodytext) {
        Font font = new Font(display, "Consolas", 12, SWT.NONE);

        Label bodylabel = new Label(pop_shell, SWT.NONE);
        bodylabel.setForeground(display.getSystemColor(SWT.COLOR_GREEN));
        bodylabel.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
        bodylabel.setText(bodytext);
        bodylabel.setSize(600, 120);
        bodylabel.setFont(font);
    }

    public void setUpGame(int enemies_n) {
        Canvas canvas = new Canvas(pop_shell, SWT.NONE);
        gc = new GC(canvas);
        canvas.setBounds(new Rectangle(0, 120, 600, 420));
        canvas.setBackground(display.getSystemColor(SWT.COLOR_BLACK));

        Image virus_image = new Image(display, new File("").getAbsolutePath().concat("\\assets\\virus.png"));
    
        
        canvas.addPaintListener(new PaintListener() {
            public void paintControl(PaintEvent e) {
                // gc = e.gc;
                gc.drawImage(virus_image, 75, 10);
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (damage == 9) {
                    pop_shell.close();
                } else {
                    pop_shell.setLocation((int) (Math.random()*(disp_width - 600)), (int) (Math.random()*(disp_height - 600)));
                    damage++;
                    Image virus_image = new Image(display, new File("").getAbsolutePath().concat("\\assets\\virus_dmg_" + damage + ".png"));
                    gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
                    gc.fillRectangle(0, 0, 600, 400);
                    gc.drawImage(virus_image, 85, 10);
                            

                }
                
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

        VirusPopup viruspopup = new VirusPopup(display, shell);

        viruspopup.setBodyText("Your system has been affected by a virus. \nYou are losing precious lines of code as you\nread this. \nFight it before the damage is too big !");
        viruspopup.setUpGame(2);

        viruspopup.open(() -> {System.out.println("Test");});

    }
}
