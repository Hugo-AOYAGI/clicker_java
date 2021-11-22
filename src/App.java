<<<<<<< HEAD
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

=======
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;

public class App {
    
    public static void centrerSurEcran(Display display, Shell shell) {
		Rectangle rect = display.getClientArea();
		Point size = shell.getSize();
		int x = (rect.width - size.x)/2;
		int y = (rect.height - size.y)/2;
		shell.setLocation(new Point(x,y));
>>>>>>> 7c1536197a7594e8369cc88518a29ebfb3f1e729
    }
    public static void main(String[] args) {
        
	};
}
