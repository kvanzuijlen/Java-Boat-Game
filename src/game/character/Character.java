package game.character;

import game.InputController;

import game.movement.Location;
import game.movement.Movement;
import game.sprite.Sprite;

import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;

public abstract class Character {

    private Location myLocation;
    private Movement moveBehaviour;
    private Sprite sprite;
    private InputController controller = InputController.getInstance();

    public abstract void collide();

    public Location getLocation() {
        return myLocation;
    }

    public double getX() {
        return myLocation.getX();
    }

    public double getY() {
        return myLocation.getY();
    }

    public double centreY() {
        return getHeight() / 2;
    }

    public double centreX() {
        return getWidth() / 2;
    }

    public double getHeight() {
        return sprite.getBounds().getHeight();
    }

    public double getWidth() {
        return sprite.getBounds().getWidth();
    }

    public void setTransform(Location rotateCentre) {
        double centreHeight;
        double centreWidth;

        AffineTransform temp = AffineTransform.getTranslateInstance(
                getLocation().getX(), getLocation().getY());

        if (rotateCentre == null) {
            centreWidth = centreX();
            centreHeight = centreY();
        } else {
            centreWidth = rotateCentre.getX();
            centreHeight = rotateCentre.getY();
        }

        temp.rotate(getMoveBehaviour().getAngle(),
                centreWidth,
                centreHeight);


        sprite.setTransform(temp);
        sprite.setTransformedArea(sprite.getUntransformedArea().createTransformedArea(temp));

    }

    public abstract void update();

    public double x(){
        return sprite.getBounds().getCenterX();

    }
        
    public double y(){
        return sprite.getBounds().getCenterY();

    }
        
    public boolean collides(Character c) {
        if (c.equals(this)) {
            return false;
        }

        Area intersectArea = new Area(sprite.getTransformedArea());
        Area b = c.getSprite().getTransformedArea();

        intersectArea.intersect(b);

        return !intersectArea.isEmpty();
    }

    /**
     *
     * @param data an ArrayList<CharacterBase> used to check for collisions
     * @return true if this Character collided with one of characters
     */
    public boolean detectCollision(ArrayList<Character> data) {
        ArrayList<Character> moving = data;
        boolean collision = false;

        int length = moving.size();
        for (int i = 0; i < length; i++) {
            Character c = moving.get(i);

            if (collision = collides(c)) {
                c.collide();
            }
        }

        return collision;
    }

    public InputController getController() {
        return controller;
    }

    public void setLocation(Location location) {
        myLocation = location;

    }

    /**
     * Creates a new instance of Character
     */
    public Character() {
        myLocation = new Location(0.0, 0.0);
    }

    public Movement getMoveBehaviour() {
        return moveBehaviour;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setMoveBehaviour(Movement moveBehaviour) {
        this.moveBehaviour = moveBehaviour;
    }

    void setLocation(double x, double y) {
        if (myLocation == null) {
            myLocation = new Location(x, y);
        } else {
            this.myLocation.setLocation(x, y);
        }
    }

    public abstract String onzin ();
}
