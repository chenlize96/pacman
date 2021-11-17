package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

public class PacmanStrategy implements IUpdateStrategy{


    /**
     * Pacman moving strategy.
     * */
    @Override
    public String getName() {
        return "Pacman";
    }

    /**
     * Detect collision, current direction and move.
     * */
    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter context, ACharacter context2) {

    }
}
