package game.movement;

import game.character.Character;

public class SwayParams {
    private final double x;
    private final double y;
    private final double pRandomPhase;
    private final Character owner;
    private final double swayH;
    private final double swayV;

    public SwayParams(double x, double y, double pRandomPhase, Character owner, double swayH, double swayV) {
        this.x = x;
        this.y = y;
        this.pRandomPhase = pRandomPhase;
        this.owner = owner;
        this.swayH = swayH;
        this.swayV = swayV;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getpRandomPhase() {
        return pRandomPhase;
    }

    public Character getOwner() {
        return owner;
    }

    public double getSwayH() {
        return swayH;
    }

    public double getSwayV() {
        return swayV;
    }
}
