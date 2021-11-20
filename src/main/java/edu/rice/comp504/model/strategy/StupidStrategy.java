package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

/**
 * Use for orange. Within 8 units, go back to the corner; otherwise use chaser.
 */
public class StupidStrategy implements IUpdateStrategy{
    @Override
    public String getName() {
        return "stupid";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter pacman, ACharacter context) {

    }
}
