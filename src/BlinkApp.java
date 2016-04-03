import java.awt.*;
import java.applet.*;

public class BlinkApp extends Applet{
    Blink thread1 = null;

    public void start(){
        thread1 = new Blink(this, 500, 100, 100);
        thread1.start();
    }

    public void stop(){
        thread1.end();
        thread1 = null;
    }

    public void paint(Graphics g){
        if( thread1 != null ){
            thread1.drawFrame(g);
        }
    }
}
