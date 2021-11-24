
import java.awt.Color;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;


public class App {
    static Boolean gameStarted = false;
    public static void main(String[] args) throws Exception {
        Display display = new Display();
        Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN);
        shell.setText("dev_clicker");
        shell.setSize(1600, 900);
        shell.setLocation(0, 0);

        SideMenu sidemenu = new SideMenu();
        long score = 25L;
        sidemenu.show(display, shell);
        sidemenu.updateScore(score);

        BottomMenu bottommenu = new BottomMenu();
        bottommenu.show(display, shell);

        FakeCode fakecode = new FakeCode();
        fakecode.show(display, shell);

        shell.open();

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

    }
}
