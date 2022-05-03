package com.battleship.game.commands;

import com.battleship.game.logic.BattleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
public class MainMenu {

    @Autowired
    @Lazy
    private BattleEngine battleEngine;

    @ShellMethod(key = "Q", value = "Quit app")
    public void quitApp() {
        System.exit(0);
    }

    @ShellMethod(key = "?", value = "Show help")
    public void showHelp() {
        System.out.println("\n\n*********************\n\n " +
                "To start New Game press - \"N\"\n" +
                "\n" +
                "To see Help press - \"?\"\n" +
                "\n" +
                "To quit press - \"quit\"\n" +
                "\n" +
                "\n\n*********************\n\n"+
                " How to play? \n" +
                "\n" +
                "After starting the game you will be asked to set coordinates for your 3 ships. Two destroyers (size 4) and one battleship (size 5)" +
                "\n" +
                "To shot the enemy ship write the coordinates to shot e.g. \"A5\" where A - column, 5 - row" +
                "\n" +
                "" +
                "\n\n*********************\n\n");
    }

    @ShellMethod(key = "N", value = "New Game")
    public void newGame() {
        try {
            battleEngine.runGame();
        }catch (IOException ioException){
            System.out.println("Error: "+ioException.getMessage());
        }
    }
}
