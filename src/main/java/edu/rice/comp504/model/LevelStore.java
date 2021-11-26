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
            "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
            "W W W W W W D W W E W E 1 2 3 4 E W E W W D W W W W W W\n" +
            "T E E S E E D E E E E E E E E E E W E E E D E E E E E T\n" +
            "W W W W W W D W W E W W W W W W W W E W W D W W W W W W\n" +
            "W W W W W W D W W E E E E P E E E E E W W D W W W W W W\n" +
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
            "W L D D D D D D D D D D D D D D D D D D D D D D D D L W\n" +
            "W W W W W W W W W W W W W W W W W W W W W W W W W W W W";

    private static String hard = "W W W W W W W W W W W W W W W W W W W W W W W W W W W W\n" +
            "W L D E E E D D D D W D D D W W D D D W D D D E D E L W\n" +
            "W D W W W D W W W D D D W D D W D b D D D b b D W W D W\n" +
            "T D W E W D W E W W D W W W D D D b b D b b b D E E E T\n" +
            "W D W E E D W E E W D W D W W W D b b b b b b D W W W W\n" +
            "W D W W W D W E E W D W D W W W W E b b E b b D W E D W\n" +
            "W D D D D D W E E W D W D W W E E B B b b b b E W W D W\n" +
            "W W W W W D W E E W D D D D D B B B b b b b b D W W D W\n" +
            "W D D E D D W E W W D W W W D B b B b b b b b D D D D W\n" +
            "W D W W W D W W W D D W E E D B b b B b b b b D W W D W\n" +
            "W D W D D D D D D D W W W W D D D S b b b b b D W D D W\n" +
            "W D W D W W W W W D W 1 2 W D W W D E b b b D D W E W W\n" +
            "W D W D E E E E D D W 3 4 W D W W W E D D E D W W D W W\n" +
            "W D W W W D W W W D W E W W D W W W W W W D W W D D W W\n" +
            "W D D D D D D D W D E E W D D D D D D W W D D D D W W W\n" +
            "W D b b B D B D W D W W W D W W W W D D D D W W W W D W\n" +
            "W D b B B D B D D D D D D D W W W W D W W D W W D D D W\n" +
            "W D D D b b b b b W W W W D W D D D D D D D D D D W D W\n" +
            "W W W D b E b b b b b D D D W D W W D R R D R R D W D W\n" +
            "W W W D b b b b B B B D W D W D W W D R R R R R D W D W\n" +
            "W D D D b b b b B E E E W D W D E W D R D P D R D W D W\n" +
            "W W D b B b b b b b E W W D W W W W D R R D R D D W D W\n" +
            "T E D b B B B B S D D W W D W W W D D D R D D D W W D T\n" +
            "W W L D D D D D D W W W W D D D D D W D R R D W W D D W\n" +
            "W W W W W W D W W W W W W D W W D W W D D E D W W W L W\n" +
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
