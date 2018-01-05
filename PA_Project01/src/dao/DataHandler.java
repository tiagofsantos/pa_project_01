/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import game.models.Game;
import game.models.User;

/**
 *
 * @author Tiago
 */
public class DataHandler {
    
    public static void saveGame(Game game){
        if(game.getInactivePlayer() instanceof User){
            System.out.println("ganhei");
           UserDAOJSON.getInstance().addGamePlayed(game.getInactivePlayer().getUsername());
           UserDAOJSON.getInstance().addVictory(game.getInactivePlayer().getUsername());
        }
        
        if(game.getActivePlayer() instanceof User){
            System.out.println("perdi");
        }
    }
    
}
