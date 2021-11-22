import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;


public class App {
    public static void main(String[] args) throws Exception {
        Display display = new Display();
        Shell shell = new Shell(display);

        shell.pack();
        shell.open();

        while (!shell.isDisposed()) {
            if (display.readAndDispatch()) {
                display.sleep();
            }
        }

        display.dispose();

    }
}
