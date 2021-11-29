package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;

/**
 * Use for pink. Advanced four units before the pacman direction.
 */
public class AmbushStrategy implements IUpdateStrategy{
    @Override
    public String getName() {
        return "ambush";
    }

    @Override
    public void updateState(ACharacter pacman, ACharacter context) {

    }
}
