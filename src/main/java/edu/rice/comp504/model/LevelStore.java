package edu.rice.comp504.model;

/**
 * A store containing the different level.
 */
public class LevelStore {
    private static String easy = "W W W W W W W W W W W W W W W W W W W W W W W W W W W W\n" +
            "W L D D D D D D D D D D D W W D D D D D D D D D D D L W\n" +
            "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
            "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
            "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
            "W D D D D D D D D D D D D D D D D D D D D D D D D D D W\n" +
            "W D W W W W D W W D W W W W W W W W D W W D W W W W D W\n" +
            "W D W W W W D W W D W W W W W W W W D W W D W W W W D W\n" +
            "W D D D D D D W W D D D D W W D D D D W W D D D D D D W\n" +
            "W W W W W W D W W W W W D W W D W W W W W D W W W W W W\n" +
            "W W W W W W D W W W W W E W W E W W W W W D W W W W W W\n" +
            "W W W W W W D W W E E E E E E E E E E W W D W W W W W W\n" +
            "W W W W W W D W W E W E W W W W E W E W W D W W W W W W\n" +
            "W W W W W W D W W E W E 1 E 3 E E W E W W D W W W W W W\n" +
            "E E E S E E D E E E W E E 2 E 4 E W E E E D E E E E E E\n" +
            "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
            "W W W W W W D W W E E E E E E E E E E W W D W W W W W W\n" +
            "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
            "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
            "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
            "W D D D D D D D D D D D D W W D D D D D D D D D D D D W\n" +
            "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
            "W D W W W W D W W W W W D W W D W W W W W D W W W W D W\n" +
            "W D D D W W D D D D D D D D D D D D D D D D W W D D D W\n" +
            "W W W D W W D W W D W W W W W W W W D W W D W W D W W W\n" +
            "W W W D W W D W W D W W W W W W W W D W W D W W D W W W\n" +
            "W D D D D D D W W D D D D W W D D D D W W D D D D D D W\n" +
            "W D W W W W W W W W W W D W W D W W W W W W W W W W D W\n" +
            "W L D D D D D D D D D D D D D P D D D D D D D D D D L W\n" +
            "W W W W W W W W W W W W W W W W W W W W W W W W W W W W";

    private static String hard = "W W W W W W W W W W W W W W W W W W W W W W W W W W W W\n" +
            "W D D D D D D D D D W D D D W W D D D W D L b D D D D W\n" +
            "W D W W W D W W W D D D W D D W D b D D D b b D W W W W\n" +
            "E D W 2 W D W E W W D W W W D D D b b D b b b D E E E E\n" +
            "W D W E E D W E E W D W D W W W D b b b b b b D W W W W\n" +
            "W D W W W D W E E W D W D W W W W E b b E b b D W L D W\n" +
            "W D D D D D W E E W D W D W W E E B B b b b b D W W D W\n" +
            "W W W W W D W E E W D D D D D B B B b b b b b D W W D W\n" +
            "W D D D D D W E W W D W W W D B b B b b b b b D D D D W\n" +
            "W D W W W D W W W D D W E E D B b b B b b b b D W W D W\n" +
            "W D W D D D D D D D W W E W D D D S b b b b b D W D D W\n" +
            "W D W D W W W W W D W 1 E W D W W D D b b b D D W D W W\n" +
            "W D W D D D D D D D W E 4 W D W W W D D D D D W W D W W\n" +
            "W D W W W D W W W D W E W W D W W W W W W D W W D D W W\n" +
            "W D D D D D D D W D E E W D D D D D D W W D D D D W W W\n" +
            "W D b b B D B D W D W W W D W W W W D D D D W W W W D W\n" +
            "W D b B B D B D D D D D D D W W W W D W W D W W D D D W\n" +
            "W D D D b b b b b W W W W D W D D D D D D D D D D W D W\n" +
            "W W W D b E b b b b b D D D W D W W D R R D R R D W D W\n" +
            "W W W D b b b b B B B D W D W D W W D R R R R R D W D W\n" +
            "W L D D b b b b B E E W W D W D L W D R D P D R D W D W\n" +
            "W W D b B b b b b b W W W D W W W W D R R D R D D W D W\n" +
            "W W D b B B B B S D D W W D W W W D D D R D D D W W D W\n" +
            "W W D D D D D D D W W W W D D D D D W D R R D W W D D W\n" +
            "W W W W W W D W W W D D W D W W D W W D D R D W W W D W\n" +
            "W W W W W W D W D D D W W D W W D W W W D D D E E W D W\n" +
            "E E E E E E D D D W D D D D D W D W W W W W D W 3 W D E\n" +
            "W W W W W W D W W W W W W W D W D D W D W W D W W W D W\n" +
            "W L D D D D D D D D D D D D D W W D D D D D D D D D D W\n" +
            "W W W W W W W W W W W W W W W W W W W W W W W W W W W W";

    /**
     * Get the level.
     * @param level the level
     * @return the string of map
     */
    public static String getTheLevel(String level) {
        if (level.equals("easy")) {
            return easy;
        } else {
            return hard;
        }
    }


}
