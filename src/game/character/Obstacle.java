package game.character;


import java.awt.geom.*;


public class Obstacle extends Character
{
    private double angMomentum=0.06;
    private double xAngMomentum;
    private double phase=0.0;
    private double yOffset = 0.0;
    private double xOffset = 0.0;
    private double randomMomentum;
    private double randomPhase;
    /** Creates a new instance of CharacterObstacle */
    public Obstacle()
    {
	xAngMomentum = getLocation().getX();
	phase+=randomPhase;
    }
    @Override
    public void update()
    {
	
	setLocation(getMoveBehaviour().go(getLocation()));
	AffineTransform t = new AffineTransform();
	t.setToTranslation(getX(),getY());
	Area a = new Area(getSprite().getUntransformedArea());
	a.transform(t);
	getSprite().setTransformedArea(a);
	
    }
    
    @Override
    public void collide()
    {
    }

    public void collide(Character c) {
    }

    public String onzin() {
        return "onzin";
    }
}

