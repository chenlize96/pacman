package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

/**
 * Use for initialize.
 */
public class NullStrategy implements IUpdateStrategy{
    @Override
    public String getName() {
        return "null";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter pacman, ACharacter context) {

    }
}
