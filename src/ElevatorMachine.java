import java.util.*;

class ElevatorMachine {
    //config
    private int height;
    private List<Integer> markers;
    //status
    private int position;
    private double door;
    //status2
    private int speed;
    private double doorSpeed;
    //errorhandle
    public static final int ERR_DEFAULT = 500;
    public static final int ERR_LIMIT = 501;
    private int errCode;

    //constructor
    public ElevatorMachine(int h0){
        height = Math.max(0, h0);
        markers = new ArrayList<Integer>();
        position = 0;
        door = 0.0;
        speed = 0;
        doorSpeed = 0;
        errCode = ERR_DEFAULT;
    }

    //configs
    public int getHeight(){
        return height;
    }

    public void addMerker(int p0){
        if( p0 < 0 ) return;
        markers.add(p0);
    }

    public void addMerkers(int[] pl){
        for(int val : pl){
            addMerker(val);
        }
    }

    public void clearMarkers(){
        markers.clear();
    }

    public Integer[] getMarkerList(){
        return markers.toArray(new Integer[markers.size()]);
    }

    //control
    public void setSpeed(int s0){
        speed = s0;
    }

    public int getSpeed(){
        return speed;
    }

    public void setDoorSpeed(double s0){
        doorSpeed = s0;
    }

    public double getDoorSpeed(){
        return doorSpeed;
    }

    //status
    public int getPosition(){
        return position;
    }

    public double getDoorAperture(){
        return door;
    }

    public int getMarker(){
        for(int i=0; i<markers.size(); i++){
            if( markers.get(i) == position ){
                return i;
            }
        }
        return -1;
    }

    //errorhandle
    public boolean isError(){
        return errCode != ERR_DEFAULT;
    }

    public int getErrorCode(){
        return errCode;
    }

    public boolean errorReturn(){
        errCode = ERR_DEFAULT;
        return true;
    }

    //simulate
    public void step(double rate){
        //errorCheck
        if( errCode != ERR_DEFAULT ) return;

        //car lifting
        if( speed != 0 ){
            int moved = position + (int)(speed * rate);
            position = Math.min(height, Math.max(0, moved));
            if( moved < 0 || moved > height ){
                errCode = ERR_LIMIT;
                speed = 0;
            }
        }

        //door
        if( doorSpeed > 0 ){
            door = Math.min(1.0, Math.max(0.0, door + (doorSpeed * rate)));
            if( door >= 1.0 ){
                doorSpeed = 0.0;
            }
        }else if( doorSpeed < 0 ){
            door = Math.min(1.0, Math.max(0.0, door + (doorSpeed * rate)));
            if( door <= 0.0 ){
                doorSpeed = 0.0;
            }
        }
    }

    public void step(){
        step(1.0);
    }
}
