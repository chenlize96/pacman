package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

/**
 * Use for cyan. Use the rate, the complicated one.
 */
public class FickleStrategy implements IUpdateStrategy{
    @Override
    public String getName() {
        return "fickle";
    }


    @Override
    public void updateState(ACharacter pacman, ACharacter context) {

    }
}
