import java.awt.*;

class Blink extends Thread{
    Component component;
    volatile boolean runFlag = false;
    volatile int interval;
    volatile int count = 0;
    int x, y;

    Blink(Component c, int t, int x0, int y0){
        runFlag = true;
        component = c;
        interval = t;
        x = x0; y = y0;
    }

    public void run(){
        while( runFlag ){
            component.repaint();
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){}
            count++;
        }
    }

    public void drawFrame(Graphics g){
        if(count % 2 == 0){
            g.setColor(Color.green);
        }else{
            g.setColor(Color.yellow);
        }
        g.fillOval(x, y, 50, 50);
        g.setFont(new Font("SansSerif", Font.PLAIN, 24));
        drawCounter(g);
    }

    public void drawCounter(Graphics g){
        FontMetrics metrics = g.getFontMetrics();
        String counter = String.valueOf(count);
        int fHeight = metrics.getAscent();
        int fWidth = metrics.stringWidth(counter);
        int dx = (x + (50/2)) - (fWidth / 2);
        int dy = (y + (50/2)) + (fHeight / 2);
        g.setColor(Color.black);
        g.drawString(counter, dx, dy);
    }

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
