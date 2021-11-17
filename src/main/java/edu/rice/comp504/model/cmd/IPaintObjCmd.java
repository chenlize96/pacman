package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.agent.ACharacter;

/**
 * The IPaintObjCmd is an interface used to pass commands to objects in the pacman world.  The
 * objects must execute the command.
 */
public interface IPaintObjCmd {

    /**
     * Execute the command.
     * @param context The receiver paintobj on which the command is executed.
     */
    void execute(ACharacter context);
}
