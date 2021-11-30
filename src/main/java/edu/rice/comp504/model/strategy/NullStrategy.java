package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

/**
 * Use for initialize.
 */
public class NullStrategy implements IUpdateStrategy{

    private static IUpdateStrategy singleton;

    /**
     * Make the only random chase strategy.
     *
     * @return A random chase strategy
     */
    public static IUpdateStrategy make() {
        if (singleton == null) {
            singleton = new NullStrategy();
        }
        return singleton;
    }

    @Override
    public String getName() {
        return "null";
    }


    @Override
    public void updateState(ACharacter pacman, ACharacter context) {

    }
}
