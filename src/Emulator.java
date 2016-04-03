import java.awt.*;

abstract class Emulator extends Thread{
    Component component;
    volatile boolean runFlag = false;
    volatile int interval;
    volatile int frame = 0;

    Emulator(Component c, int t){
        runFlag = true;
        component = c;
        interval = t;
    }

    public void run(){
        while( runFlag ){
            component.repaint();
            try{
                Thread.sleep(interval);
            }catch(InterruptedException e){}
            frame++;
        }
    }

    abstract public void drawFrame(Graphics g);

    public void end(){
        runFlag = false;
    }

    public void setInterval(int t0){
        if( runFlag ){
            interval = t0;
            interrupt();
        }
    }
}
