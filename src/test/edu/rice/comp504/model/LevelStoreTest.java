package edu.rice.comp504.model;

import org.junit.Test;

import java.util.logging.Level;

import static org.junit.Assert.*;

public class LevelStoreTest {

    @Test
    public void getTheLevel() {
        LevelStore.getTheLevel("easy");
        LevelStore.getTheLevel("hard");
    }
}