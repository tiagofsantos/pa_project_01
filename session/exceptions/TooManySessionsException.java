/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.exceptions;

/**
 *
 * @author Tiago
 */
public class TooManySessionsException extends RuntimeException{

    public TooManySessionsException() {
        super("Já existem 2 sessões iniciadas");
    }
    
}
