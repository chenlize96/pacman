package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Pacman;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class InteractCmdTest {
    InteractCmd test = new  InteractCmd(null);
    @Test
    public void execute() {
        Pacman context = new Pacman("pacman", new Point(1,1), new Point(1,1), 0);
        test.execute(context);
    }
}