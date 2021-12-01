package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.agent.ACharacter;
import edu.rice.comp504.model.agent.Ghost;
import edu.rice.comp504.model.agent.Pacman;
import org.junit.Test;

import java.awt.*;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class InteractCmdTest {
    ACharacter[] characters = new ACharacter[3];
    InteractCmd test = new InteractCmd(characters);

    @Test
    public void execute() {
        characters[0] = new Ghost("ghost", new Point(2,1), new Point(0,1), 0);
        Pacman context = new Pacman("pacman", new Point(2,1), new Point(1,1), 1);
        characters[1] = context;
        characters[2] = new Ghost("ghost", new Point(1,0), new Point(1,0), 3);

        test.execute(context);
        test.execute(context);

        characters[0].setLoc(new Point(1,1));
        PacmanStore.setCurrentFrame(1);
        test.execute(context);

        context.setLoc(new Point(1,26));
        test.execute(context);
        for(int i=0;i<PacmanStore.getGrid().length;i++){
            for(int j=0;j<PacmanStore.getGrid()[0].length;j++){
                if(PacmanStore.getGrid()[i][j].getType().equals("apple")){
                    System.out.println("apple loc:");
                    System.out.println(i+" "+j);
                }
            }
        }
        context.setLoc(new Point(22,8));
        test.execute(context);
    }
}