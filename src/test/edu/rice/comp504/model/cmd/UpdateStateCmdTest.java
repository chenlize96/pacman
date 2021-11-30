package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class UpdateStateCmdTest {

    @Test
    public void execute() {
        ACharacter[] characters = new ACharacter[2];
        characters[0] = new Pacman("pacman",new Point(1,1), new Point(1,1),2);
        characters[1] = new Ghost("ghost",new Point(1,2),new Point(1,2),2);
        UpdateStateCmd test = new UpdateStateCmd(characters);
        test.execute(characters[0]);
        test.execute(characters[1]);
    }
}