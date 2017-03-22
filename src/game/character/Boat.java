package game.character;

import game.GameEngine;
import game.InputController;
import game.GameWindow;
import game.movement.Location;
import game.movement.AngledAcceleration;
import game.sprite.Sprite;
import game.Util;
import java.awt.geom.*;

public class Boat {

    private final MyMoveable moveable = new MyMoveable();
    Location pivotPoint;
    private int energy = 100;

    public void setEnergy(int energy) {
        Boat.this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    private void reduceEnergy() {
        int e = getEnergy();
        e--;

        setEnergy(e);

        if (e <= 0) {

            GameEngine.getInstance().gameOver();
        } else {

            GameWindow.getInstance().setEnergyBarLevel(e);
        }

    }

    public void collision(Character c) {
        reduceEnergy();
    }

    public Boat() {
    }

    private double pinAngle(double value) {
        if (Math.abs(value) > Math.PI) {
            while (value > Math.PI) {
                value = value - (2 * Math.PI);
            }
            while (value < -Math.PI) {
                value = value + (2 * Math.PI);
            }
        }
        return value;

    }

    private void processMouse() {
        Point2D p = moveable.getController().getMouseLocation();

        Location dest = new Location(p.getX(), p.getY());

        double dy = dest.getY() - moveable.y();
        double dx = dest.getX() - moveable.x();

        double destinationAngle = Math.atan2(dy, dx);

        AngledAcceleration m = (AngledAcceleration) moveable.getMoveBehaviour();
        double angleDelta = destinationAngle - m.getAngle();

        angleDelta = pinAngle(angleDelta);
        
        if (Math.abs(angleDelta) < (Math.PI / 2.0)) {
            if ((angleDelta < Math.PI) && (angleDelta > 0)) {
                moveable.setLocation(m.goRight(moveable.getLocation()));
            }

            if ((angleDelta < 0) && (angleDelta > -Math.PI)) {
                moveable.setLocation(m.goLeft(moveable.getLocation()));
            }
            //accelerate
            moveable.setLocation(m.goUp(moveable.getLocation()));
        } else {
            m.setVelocity(m.getVelocity() * 0.95);

            if ((angleDelta > 0)) {

                moveable.setLocation(m.goRight(moveable.getLocation()));
            }

            if ((angleDelta < 0)) {
                moveable.setLocation(m.goLeft(moveable.getLocation()));
            }
        }
        moveable.setLocation(m.goUp(moveable.getLocation()));
    }

    private void processKeyPressSquare(InputController.Control keypress) {
        handleKeyPress(keypress);
    }

    private void processKeyPressRotating(InputController.Control keypress) {
        handleKeyPress(keypress);
    }

    private void handleKeyPress(InputController.Control keypress) {
        switch (keypress) {
            case UP:
                moveable.setLocation(moveable.getMoveBehaviour().goUp(moveable.getLocation()));
                break;
            case DOWN:
                moveable.setLocation(moveable.getMoveBehaviour().goDown(moveable.getLocation()));
                break;
            case LEFT:
                moveable.setLocation(moveable.getMoveBehaviour().goLeft(moveable.getLocation()));
                break;
            case RIGHT:
                moveable.setLocation(moveable.getMoveBehaviour().goRight(moveable.getLocation()));
                break;
            case BRAKE:
                moveable.setLocation(moveable.getMoveBehaviour().brake(moveable.getLocation()));
                break;
            case STORM:
                break;
            case PAUSE: //don't update
                //GameEngine.getInstance().togglePause();
                break;
            default:
                //do nothing
                break;
        }
    }

    protected String onzin() {
        return "onzin";
    };

    public Moveable getMoveable() {
        return moveable;
    }

    private class MyMoveable extends Moveable {
        @Override
        public void update() {
            InputController controller = getController();
            if (controller.keyPressEventsPending()) {
                InputController.Control pressedControl = controller.getPressedControl();
                processKeyPressRotating(pressedControl);

            } else {
                setLocation(getMoveBehaviour().go(getLocation()));
            }

            if (controller.keyHeldEventsPending()) {
                int count = 0;
                while (count <= controller.getNumberOfHeldControls()) {
                    InputController.Control c = controller.getHeldControl(count);
                    processKeyPressRotating(c);
                    count++;
                }
            }

            if (controller.isMouseHeld()) {
                processMouse();
            }

            setTransform(pivotPoint);

            if (checkScreenEdge()) {
                this.getMoveBehaviour().setVelocity(getMoveBehaviour().getVelocity() / 10);
            }

            GameWindow.getInstance()
                    .updateControlPanel(Boat.this);
        }

        @Override
        public void setSprite(Sprite sprite) {
            super.setSprite(sprite);
            pivotPoint = Util.getBoatPivotPoint(sprite);
        }

        public String onzin() {
            return Boat.this.onzin();
        }
    }
}
