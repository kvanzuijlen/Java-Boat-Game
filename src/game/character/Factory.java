package game.character;

import game.GameEngine;
import game.movement.*;
import game.Renderer;
import game.sprite.Sprite;
import game.sprite.Buoy;
import game.sprite.SpriteImage;
import game.Util;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

public class Factory {

    public Factory() {
    }

    private static Area createAreaFromLocations(Location[] locations, GeneralPath generalPath) {
        int count = 0;
        int x;
        int y;
        x = ((int) locations[count].getX());
        y = ((int) locations[count].getY());
        generalPath.moveTo(x, y);
        count++;
        while (count < locations.length) {
            x = ((int) locations[count].getX());
            y = ((int) locations[count].getY());
            count++;
            generalPath.lineTo(x, y);
        }
        generalPath.closePath();
        Area area = new Area(generalPath);



        return area;
    }

    private Boat createBoat() {
        Boat boat = new Boat();
        Renderer renderer = Renderer.getInstance();

        int x = 10;
        int y = Renderer.getInstance().getHeight() - 30;
        Location location = new Location(x, y);

        boat.setLocation(location);

        Image[] boatImages = new Image[2];
        boatImages[0] = Util.imageResources.get("BOAT");
        boatImages[1] = Util.imageResources.get("BOAT_EXPLODE");

        SpriteImage boatSprite = new SpriteImage(boatImages, boat);

        game.movement.Location l = boat.getLocation();
        boatSprite.setTransformation(x, y, Util.getBoatArea(boatImages[0]));


        boat.setLocation(new Location(30, renderer.getHeight()));



        boat.setSprite(boatSprite);

        Movement move = Util.getBoatMovePresets();

        //Add a swaying motion to the boat
        Movement m = new Swaying((AngledAcceleration) move, new SwayParams(0, 0, Math.random(), boat, 0.2, 0.3));
        boat.setMoveBehaviour(m);
        return boat;
    }

    private EnemyBoat createComputerBoat() {

        EnemyBoat computerBoat = new EnemyBoat();


        Renderer renderer = Renderer.getInstance();

        computerBoat.setLocation(
                Math.random() * renderer.getWidth(),
                Math.random() * renderer.getHeight());



        Image[] boatImages = new Image[2];
        boatImages[0] = Util.imageResources.get("BOAT2");
        boatImages[1] = Util.imageResources.get("BOAT_EXPLODE");


        SpriteImage computerBoatSprite = new SpriteImage(boatImages, computerBoat);
        computerBoatSprite.setShowSprite(true);

        
        int x = 450;
        int y = 400;

        computerBoatSprite.setTransformation(x, y, Util.getBoatArea(boatImages[0]));
        computerBoat.setSprite(computerBoatSprite);

        Movement computerBoatMove = Util.angledAccelerationPresets();
        computerBoat.setMoveBehaviour(computerBoatMove);

        return computerBoat;
    }

    private static Harbour createHarbour() {


        Renderer renderer = Renderer.getInstance();


        Harbour harbour = new Harbour();
        game.sprite.Harbour harbourSprite = new game.sprite.Harbour(harbour);

        GeneralPath generalPath = new GeneralPath();


        Location[] locations = Util.getHarbourData();
        Area area = createAreaFromLocations(locations, generalPath);


        harbour.setLocation(new Location(0, 0));

        harbourSprite.setUntransformedArea(area);
        AffineTransform t = new AffineTransform();
        t.setToTranslation(0, 0);
        harbourSprite.setTransform(t);
        harbourSprite.setTransformedArea(area.createTransformedArea(t));

        harbourSprite.setShowSprite(false);


        harbour.setSprite(harbourSprite);

        return harbour;
    }

    private static Island createIsland() {

        GameEngine ge = GameEngine.getInstance();
        Renderer renderer = Renderer.getInstance();


        Island island = new Island();


        game.sprite.Island islandSprite = new game.sprite.Island(island);

        GeneralPath generalPath = new GeneralPath();
        island.setLocation(new Location(0, 0));
        Location[] locations = Util.getIslandData();
        Area area = createAreaFromLocations(locations, generalPath);

        islandSprite.setUntransformedArea(area);
        AffineTransform t = new AffineTransform();
        t.setToIdentity();
        islandSprite.setTransform(t);

        islandSprite.setTransformedArea(area.createTransformedArea(t));

        islandSprite.setShowSprite(false);


        island.setSprite(islandSprite);

        return island;

    }

    private Character createOctopus() {


        Renderer renderer = Renderer.getInstance();

        Character octopus = new Obstacle();

        Image[] images = new Image[1];
        images[0] = Util.imageResources.get("OCTOPUS");
        SpriteImage octopusSprite = new SpriteImage(images, octopus);

        Ellipse2D boundingEllipse = new Ellipse2D.Double(0, 0,
                images[0].getWidth(renderer),
                images[0].getHeight(renderer));

        Area area = new Area(boundingEllipse);

        //MoveAngledAccelerate move, double x, double y, double pRandomPhase, CharacterBase owner,
        //double swayH, double swayV)
        Swaying move = new Swaying(null, new SwayParams(0, 0, Math.random(), octopus, 100.0, 1.0));
        octopus.setMoveBehaviour(move);

        octopusSprite.setUntransformedArea(area);
        AffineTransform t = new AffineTransform();

        t.setToIdentity();

        octopusSprite.setTransform(t);
        octopusSprite.setTransformedArea(area.createTransformedArea(t));
        octopusSprite.setSqueezeImageIntoTransformedArea(true);
        octopusSprite.setShowSprite(true);
        octopus.setSprite(octopusSprite);

        return octopus;

    }

    private game.character.Goal createGoal() {


        Renderer renderer = Renderer.getInstance();

        game.character.Goal goal = new game.character.Goal();


        game.sprite.Goal goalSprite = new game.sprite.Goal(goal);

        Area area = new Area(new Rectangle(0, 0, 100, 50));

        goalSprite.setUntransformedArea(area);
        AffineTransform t = new AffineTransform();
        t.setToTranslation(renderer.getWidth() - 100, renderer.getHeight() - 50);
        goalSprite.setTransform(t);

        goalSprite.setTransformedArea(area.createTransformedArea(t));

        goalSprite.setShowSprite(true);


        goal.setSprite(goalSprite);

        return goal;

    }

    public Obstacle createBuoy() {
        Renderer renderer = Renderer.getInstance();
        Obstacle buoy = new Obstacle();
        Sprite sprite = new Buoy(buoy);
        double randomX = (Math.random() * renderer.getWidth());
        double randomY = (Math.random() * renderer.getHeight());

        buoy.setLocation(randomX, randomY);

        Movement sway = new game.movement.Swaying(null, new SwayParams(randomX, randomY, randomY, buoy, 1, 2));
        buoy.setMoveBehaviour(sway);
        int size = Util.getObstacleSize();
        Area a = new Area(new java.awt.geom.Ellipse2D.Double(randomX - size / 2, randomY - size / 2, size, size));
        sprite.setUntransformedArea(a);
        AffineTransform t = new AffineTransform();
        t.setToTranslation(randomX, randomY);

        sprite.setTransformedArea(a);
        sprite.setShowSprite(true);
        sprite.setHeight(size);
        sprite.setWidth(size);

        buoy.setSprite(sprite);

        return buoy;

    }

    public Character createCharacter(String type) {


        Character character = null;
        switch (type) {
            case "BOAT":
                character = createBoat();
                break;
            case "COMPUTER_BOAT":
                character = createComputerBoat();
                break;
            case "HARBOUR":
                character = createHarbour();
                break;
            case "BUOY":
                character = createBuoy();
                break;
            case "ISLAND":
                character = createIsland();
                break;
            case "OCTOPUS":
                character = createOctopus();
                break;
            case "GOAL":
                character = createGoal();
                break;

        }

        return character;
    }
}
