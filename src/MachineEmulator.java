import java.awt.*;

class MachineEmulator extends Emulator{
    ElevatorMachine machine;
    int speed;
    double doorSpeed;
    int doorControl;
    int liftControl;

    public MachineEmulator(Component c, int t, ElevatorMachine m, int s, double ds){
        super(c, t);
        machine = m;
        speed = s;
        doorSpeed = ds;
        doorControl = 0;
        liftControl = 1;
    }

    public void drawFrame(Graphics g){
        if( doorControl == 0 ){
            if( liftControl > 0 ){
                if( !liftUp() ){
                    liftControl = -1;
                    doorControl = 1;
                }
            }else{
                if( !liftDown() ){
                    liftControl = 1;
                    doorControl = 1;
                }
            }
        }else{
            if( doorControl > 0 ){
                if( !doorOpen() ){
                    doorControl = -1;
                }
            }else{
                if( !doorClose() ){
                    doorControl = 0;
                }
            }
        }
        machine.step();
        g.drawString("frame = " + frame, 10, 20);
    }

    public boolean liftUp(){
        int pos = machine.getPosition();
        if( (pos + speed) > machine.getHeight() ){
            machine.setSpeed(0);
            return false;
        }else{
            machine.setSpeed(speed);
            return true;
        }
    }

    public boolean liftDown(){
        int pos = machine.getPosition();
        if( (pos - speed) < 0 ){
            machine.setSpeed(0);
            return false;
        }else{
            machine.setSpeed(-speed);
            return true;
        }
    }

    public boolean doorOpen(){
        machine.setDoorSpeed(doorSpeed);
        return !(machine.getDoorAperture() >= 1.0);
    }

    public boolean doorClose(){
        machine.setDoorSpeed(-doorSpeed);
        return !(machine.getDoorAperture() <= 0.0);
    }
}
