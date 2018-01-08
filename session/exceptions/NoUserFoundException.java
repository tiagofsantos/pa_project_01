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
public class NoUserFoundException extends RuntimeException{

    public NoUserFoundException() {
        super("O nome de utilizador inserido não corresponde a uma conta");
    }
    
}
