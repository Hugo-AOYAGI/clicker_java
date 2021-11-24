
import java.awt.Color;

import org.eclipse.swt.*;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.*;


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

        BottomMenu bottommenu = new BottomMenu();
        bottommenu.show(display, shell);

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

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

    }
}
