package game.movement;

import game.character.Character;

public class Swaying extends AngledAcceleration
{
    public Swaying(AngledAcceleration move, SwayParams swayParams)
    {
	this.move = move;
	this.owner = swayParams.getOwner();
	xAnchor = swayParams.getX();
	yAnchor = swayParams.getY();
	this.randomPhase = swayParams.getpRandomPhase();
	phase += randomPhase;
	this.swaySizeHorizontal= swayParams.getSwayH();
	this.swaySizeVertical= swayParams.getSwayV();
    }
    //Holds instance of superclass (used as a decorator class)
    private AngledAcceleration move;
    private Character owner;
    private double angMomentum = 0.06;
    private double phase = 0.0;
    private double yOffset = 0.0;
    private double xOffset = 0.0;
    private double randomPhase;
    private double swaySizeHorizontal;
    private double swaySizeVertical;
    private double xAnchor;
    private double yAnchor;
    
    public void setOwner(Character owner)
    {
	this.owner = owner;
    }
    
    public Character getOwner()
    {
	return owner;
    }
    
    @Override
    public Location go(Location location)
    {
	xOffset = Math.sin(phase + (Math.PI * location.getX() / (2 * game.Renderer.width())));
	
	yOffset = 0.5 * Math.sin(phase);//+Math.abs(xOffset);
	
	phase += angMomentum;
	
	if (move != null)
	{
	    double newX = (owner.getX() + xOffset * swaySizeHorizontal );
	    double newY = (owner.getY() + yOffset * swaySizeVertical );
	    
	    location.setLocation(newX, newY);
	    location = move.go(location);
	    return location;
	}
	else
	{
	    double newX = (this.xAnchor + xOffset * 10 - (swaySizeHorizontal / 2));
	    double newY = (this.yAnchor + yOffset * 20 / (3.0 - (swaySizeVertical / 2)));
	    
	    location.setLocation(newX, newY);
	    
	    return location;
	}
	
    }
    
    @Override
    public Location goDown(Location location)
    {
	
	if (move != null)
	{
	    
	    return move.goDown(location);
	}
	else
	{
	    return super.goDown(location);
	    
	}
    }
    
    @Override
    public Location goLeft(Location location)
    {
	
	if (move != null)
	{
	    
	    return move.goLeft(location);
	}
	else
	{
	    return super.goLeft(location);
	    
	}
	
    }
    
    @Override
    public Location goRight(Location location)
    {
	if (move != null)
	{
	    
	    return move.goRight(location);
	}
	else
	{
	    return super.goRight(location);
	    
	}
	
    }
    
    
    @Override
    public void setYVelocity(double yVelocity)
    {
	move.setYVelocity(yVelocity);
    }
    
    @Override
    public void setXVelocity(double xVelocity)
    {
	move.setXVelocity(xVelocity);
    }
    
    @Override
    public void setVelocity(double velocity)
    {
	move.setVelocity(velocity);
    }
    
    public void setSwaySizeVertical(double swaySizeVertical)
    {
	this.swaySizeVertical = swaySizeVertical;
    }
    
    public void setSwaySizeHorizontal(double swaySizeHorizontal)
    {
	this.swaySizeHorizontal = swaySizeHorizontal;
    }
    
    public void setRandomPhase(double randomPhase)
    {
	this.randomPhase = randomPhase;
    }
    
    @Override
    public void setMaxVelocity(double x)
    {
	move.setMaxVelocity(x);
    }
    
    @Override
    public void setFriction(double friction)
    {
	move.setFriction(friction);
    }
    
    @Override
    public void setBrake(double brake)
    {
	move.setBrake(brake);
    }
    
    @Override
    public void setAngularVelocity(double angularVelocity)
    {
	move.setAngularVelocity(angularVelocity);
    }
    
    @Override
    public void setAngularMaxVelocity(double angularMaxVelocity)
    {
	move.setAngularMaxVelocity(angularMaxVelocity);
    }
    
    @Override
    public void setAngularFriction(double angularFriction)
    {
	move.setAngularFriction(angularFriction);
    }
    
    @Override
    public void setAngularAcceleration(double angularAcceleration)
    {
	move.setAngularAcceleration(angularAcceleration);
    }
    
    @Override
    public void setAngle(double angle)
    {
	move.setAngle(angle);
    }
    
    @Override
    public void setAcceleration(double acceleration)
    {
	move.setAcceleration(acceleration);
    }
    
    @Override
    public double getYVelocity()
    {
	return move.getYVelocity();
    }
    
    @Override
    public double getXVelocity()
    {
	return move.getXVelocity();
    }
    
    @Override
    public Location turn(Location location)
    {
	return move.turn(location);
    }
    
    @Override
    public double getVelocity()
    {
	return move.getVelocity();
    }
    
    @Override
    public double getMaxVelocity()
    {
	return move.getMaxVelocity();
    }
    
    @Override
    public double getFriction()
    {
	return move.getFriction();
    }
    
    @Override
    public double getBrake()
    {
	return move.getBrake();
    }
    
    @Override
    public double getAngularVelocity()
    {
	return move.getAngularVelocity();
    }
    
    @Override
    public double getAngularMaxVelocity()
    {
	return move.getAngularMaxVelocity();
    }
    
    @Override
    public double getAngularFriction()
    {
	return move.getAngularFriction();
    }
    
    @Override
    public double getAngularAcceleration()
    {
	return move.getAngularAcceleration();
    }
    
    @Override
    public double getAngle()
    {
	return move.getAngle();
    }
    
    public double getAngMomentum()
    {
	return angMomentum;
    }
    
    @Override
    public double getAcceleration()
    {
	return move.getAcceleration();
    }
    @Override
    public Location goUp(Location location)
    {
	if (move != null)
	{
	    
	    return move.goUp(location);
	}
	else
	{
	    return move.goUp(location);
	    
	}
    }
}
