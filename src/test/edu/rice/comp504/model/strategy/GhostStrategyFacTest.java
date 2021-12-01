package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import junit.framework.TestCase;

import java.awt.*;

public class GhostStrategyFacTest extends TestCase {

    public void testMakeStrategyFactory() {
        GhostStrategyFac test = GhostStrategyFac.makeStrategyFactory();
        test.make("random");
        test.make("backHome");
    }

    public void testMake() {
    }

    public void testSwitchStrategy() {
        GhostStrategyFac test = GhostStrategyFac.makeStrategyFactory();
        ACharacter test2 = new Ghost("ghost", new Point(1,1), new Point(1,1), 0);
        test.switchStrategy(test2);
    }
}