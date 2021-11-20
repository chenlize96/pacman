package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

/**
 * Use A*. This is for the red ghost.
 * Use the location of the current pacman and the ghost.
 * Death strategy is the same as this strategy - back to the born strategy
 */
public class ChaseStrategy implements IUpdateStrategy{
    @Override
    public String getName() {
        return "chase";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter pacman, ACharacter context) {

    }
}
