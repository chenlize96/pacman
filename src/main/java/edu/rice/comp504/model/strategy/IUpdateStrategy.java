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
     * Update the position of the ghosts.
     * @param ghost The ghost
     * @param ghost The pacman
     */
    void updateState(ACharacter ghost, ACharacter pacman);
}
