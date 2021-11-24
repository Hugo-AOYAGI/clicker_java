
import org.eclipse.swt.*; 
import org.eclipse.swt.graphics.*; 
import org.eclipse.swt.widgets.*; 

public class BottomMenu {
    

    public void show(Display display, Shell shell) {

        Composite composite = new Composite(shell, SWT.BORDER); 
        composite.setBackground(new Color(display,0,0,0)); 
        composite.setSize(1200,200);
        composite.setLocation(400, 650);
    
         
    }

}
