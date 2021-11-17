package edu.rice.comp504.model.cmd;

/**
 * A factory that makes commands.
 */
public interface ICmdFac {

    /**
     * Makes a character command.
     * @return A character command
     */
    IPaintObjCmd make();
}
