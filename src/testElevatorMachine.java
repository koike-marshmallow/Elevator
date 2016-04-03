import java.awt.*;
import java.applet.*;

public class testElevatorMachine extends Applet{
    ElevatorMachine machine;
    Emulator thread1 = null;
    ElevatorDrawer drawer;

    public void start(){
        machine = new ElevatorMachine(600);
        drawer = new ElevatorDrawer(machine, 150, 200);
        thread1 = new MachineEmulator(
            this, 1000, machine, 60, 0.2);
        thread1.start();
    }

    public void stop(){
        thread1.end();
        thread1 = null;
    }

    public void paint(Graphics g){
        if( thread1 != null ){
            thread1.drawFrame(g);
            String params = String.format(
                "height: %d, position: %d, door: %f, spd: %d, dspd: %f",
                machine.getHeight(), machine.getPosition(),
                machine.getDoorAperture(), machine.getSpeed(),
                machine.getDoorSpeed()
            );
            g.drawString(params, 170, 40);
            if( machine.isError() ){
                g.setColor(Color.red);
                g.drawString("ERROR (CODE:" + machine.getErrorCode() + ")", 170, 60);
                g.setColor(Color.black);
            }
            drawer.draw(g, 10, 30);
        }
    }
}
