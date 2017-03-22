package game;

import game.movement.AngledAcceleration;
import game.movement.Random;
import game.movement.Location;
import game.movement.Movement;
import game.sprite.Sprite;
import java.awt.geom.Area;
import java.util.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author Mark
 */
public class Util {

    private static MediaTracker mt;
    public static HashMap<String, Image> imageResources = new HashMap<>();

    public static Location getBoatPivotPoint(Sprite sprite) {
        return new Location(sprite.getWidth() / 4, sprite.getHeight() / 2);
    }

    public static Area getBoatArea(Image img) {
        Renderer renderer = Renderer.getInstance();
        int boatWidth = img.getWidth(renderer);
        int boatHeight = img.getHeight(renderer);
        Area a = new Area(new Rectangle(0, 0, boatWidth * 2 / 3,
                boatHeight));
        Polygon triangle = new Polygon();

        triangle.addPoint((int) (boatWidth * 4 / 5), 0);
        triangle.addPoint((int) (boatWidth * 4 / 5), boatHeight);
        triangle.addPoint(boatWidth, (int) (boatHeight / 2));

        a.add(new Area(triangle));

        return a;

    }

    public static Movement angledAccelerationPresets() {
        AngledAcceleration angledAcceleration = new AngledAcceleration();
        angledAcceleration.setVelocity(0.414);
        angledAcceleration.setMaxVelocity(1.414);
        angledAcceleration.setAngle(0.0);
        angledAcceleration.setAngularVelocity(0.0);
        angledAcceleration.setAngularAcceleration(0.00237);
        angledAcceleration.setAngularMaxVelocity(0.0424);
        angledAcceleration.setAngularFriction(0.00065);
        angledAcceleration.setVelocity(0.00065);
        angledAcceleration.setAcceleration(0.00326);
        angledAcceleration.setFriction(0.001);
        angledAcceleration.setBrake(0.25);

        return new Random(angledAcceleration);
    }

    public static void loadImages() {
        URL url;

        Renderer renderer = Renderer.getInstance();

        mt = new MediaTracker(renderer);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        url = ClassLoader.getSystemResource("game/images/seaharbour.jpg");
        Image imgBackground = toolkit.getImage(url);

        mt.addImage(imgBackground, 1);

        url = ClassLoader.getSystemResource("game/images/boat_sm.gif");
        Image imgBoat = toolkit.getImage(url);
        imgBoat.setAccelerationPriority(1.0f);
        mt.addImage(imgBoat, 2);

        url = ClassLoader.getSystemResource("game/images/boat_sm2.gif");
        Image imgBoat2 = toolkit.getImage(url);
        imgBoat2.setAccelerationPriority(1.0f);

        mt.addImage(imgBoat2, 3);

        url = ClassLoader.getSystemResource("game/images/night.gif");
        Image imgNight = toolkit.getImage(url);
        mt.addImage(imgNight, 4);


        url = ClassLoader.getSystemResource("game/images/boatexplode_sm.gif");
        Image imgBoatExplode = toolkit.getImage(url);
        mt.addImage(imgBoatExplode, 5);

        url = ClassLoader.getSystemResource("game/images/octopus_sm.gif");
        Image imgOctopus = toolkit.getImage(url);
        mt.addImage(imgOctopus, 7);


        try {
            mt.waitForAll();

            Util.imageResources.put("SEA", imgBackground);
            Util.imageResources.put("NIGHT", imgNight);
            Util.imageResources.put("BOAT", imgBoat);
            Util.imageResources.put("BOAT2", imgBoat2);
            Util.imageResources.put("BOAT_EXPLODE", imgBoatExplode);
            Util.imageResources.put("OCTOPUS", imgOctopus);
            System.out.println("Loaded images");


        } catch (Exception err) {
            System.out.println("Exception while loading");
            System.exit(-1);
        }

    }

    public static Movement getBoatMovePresets() {
        AngledAcceleration boatMove = new AngledAcceleration();
        boatMove.setAcceleration(0.0116);
        boatMove.setMaxVelocity(2.214);
        boatMove.setFriction(0.00448);
        boatMove.setAngle(-0.8);
        boatMove.setAngularVelocity(0.0);
        boatMove.setAngularAcceleration(0.00129);
        boatMove.setAngularMaxVelocity(0.0296);
        boatMove.setAngularFriction(0.000421);

        return boatMove;
    }

    public static Location[] getIslandData() {

        Location[] i = {
                new Location(167, 198),
                new Location(221, 248),
                new Location(218, 312),
                new Location(211, 366),
                new Location(191, 440),
                new Location(184, 500),
                new Location(195, 558),
                new Location(230, 558),
                new Location(224, 499),
                new Location(282, 377),
                new Location(319, 269),
                new Location(399, 240),
                new Location(395, 188),
                new Location(361, 140),
                new Location(306, 108),
                new Location(233, 132),
                new Location(181, 158),
                new Location(164, 192)
        };
        return i;
    }

    public static Location[] getHarbourData() {

        Location[] i = {
                new Location(550, 2),
                new Location(561, 54),
                new Location(569, 77),
                new Location(552, 137),
                new Location(540, 148),
                new Location(558, 175),
                new Location(615, 225),
                new Location(610, 260),
                new Location(625, 380),
                new Location(618, 461),
                new Location(605, 483),
                new Location(676, 484),
                new Location(673, 0),
                new Location(555, 1)
        };
        return i;
    }

    public static int getObstacleSize() {
        return 20;
    }

    static int getMinimumNumberOfObstacles() {
        return 50;
    }

    static int getMaxiumNumberOfObstacles() {
        return 60;
    }

    /**
     * Creates a new instance of Util
     */
    public Util() {
    }
}