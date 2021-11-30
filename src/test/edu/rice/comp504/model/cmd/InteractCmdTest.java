package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class InteractCmdTest {
    Ghost[] ghosts = {new Ghost("ghost", new Point(3,1), new Point(3,1), 0)};
    InteractCmd test = new InteractCmd(ghosts);

    @Test
    public void execute() {
        Pacman context = new Pacman("pacman", new Point(2,1), new Point(2,1), 0);
        test.execute(context);
    }
}