package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.agent.Ghost;
import junit.framework.TestCase;

import java.awt.*;

public class SwitchStrategyCmdTest extends TestCase {

    SwitchStrategyCmd test = new SwitchStrategyCmd();

    public void testExecute() {
        Ghost ghost = new Ghost("ghost", new Point(3,1), new Point(3,1), 0);
        test.execute(ghost);
    }
}