import java.awt.*;

class ElevatorDrawer {
    static final int SHAFT_MARGIN = 3;
    static final int DOOR_MARGIN = 5;
    ElevatorMachine machine;
    int carWidth, carHeight;

    public ElevatorDrawer(ElevatorMachine m, int bw, int bh){
        machine = m;
        carWidth = bw;
        carHeight = bh;
    }

    public void drawShaft(Graphics g, int x, int y){
        int height = machine.getHeight() + carHeight + (SHAFT_MARGIN * 2);
        int width = carWidth + (SHAFT_MARGIN * 2);
        g.setColor(Color.black);
        g.drawRect(x, y, width, height);
    }

    public void drawCar(Graphics g, int x, int y){
        int dx = x + SHAFT_MARGIN;
        int dy = y + (machine.getHeight() - machine.getPosition()) + SHAFT_MARGIN;
        g.setColor(Color.black);
        g.drawRect(dx, dy, carWidth, carHeight);
        drawDoor(g, dx, dy);
    }

    public void drawDoor(Graphics g, int cx, int cy){
        int doorWidth = (carWidth - (DOOR_MARGIN * 2)) / 2;
        int doorHeight = carHeight - (DOOR_MARGIN * 2);
        int aperture = (int)(doorWidth * machine.getDoorAperture());
        g.setColor(Color.black);
        g.fillRect(cx + DOOR_MARGIN, cy + DOOR_MARGIN,
            doorWidth - aperture, doorHeight);
        g.fillRect(cx + DOOR_MARGIN + doorWidth + aperture, cy + DOOR_MARGIN,
            doorWidth - aperture, doorHeight);
    }

    public void draw(Graphics g, int x, int y){
        drawShaft(g, x, y);
        drawCar(g, x, y);
    }
}
