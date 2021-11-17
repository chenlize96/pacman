package edu.rice.comp504.model.strategy;


import edu.rice.comp504.model.agent.ACharacter;

/**
 * An interface for character strategies that determine the ghost behavior.
 */
public interface IUpdateStrategy {
    /**
     * The name of the strategy.
     * @return strategy name
     */
    String getName();

    /**
     * Update the position of the pacman.
     * @param context The pacman.
     */
    void updateState(ACharacter context);

    /**
     * Update the position of ghost.
     * */
    public void updateState(ACharacter pacman, ACharacter context);
}
