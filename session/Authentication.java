package session;

import data.DataHandler;
import data.encryption.MD5Encrypter;
import game.models.User;
import session.exceptions.*;

/**
 *
 * @author Tiago
 */
public class Authentication {

    public static void login(String username, String password) {

        //se já houverem 2 sessoes iniciadas
        if (SessionManager.getUserSessions().size() == 2) {
            throw new TooManySessionsException();
        }

        // se nao existir utilizador
        if (!checkIfUserExists(username)) {
            throw new NoUserFoundException();
        }

        //se alguem ja tiver a sessao iniciada nesta conta, ele nao pode fazer login
        if (checkIfUserLoggedIn(username)) {
            throw new AlreadyLoggedInException();
        }

        //verifica a password
        User aux = DataHandler.selectUser(username);
        if (!(MD5Encrypter.md5Encryption(password).equals(aux.getPassword()))) {
            throw new IncorrectCredentialsException();
        }

        //fazer login
        UserSession session = new UserSession(aux);
        SessionManager.getUserSessions().add(session);
    }

    public static void logout(String username) {
        if (!checkIfUserExists(username)) {
            throw new NoUserFoundException();
        }

        if (!checkIfUserLoggedIn(username)) {
            throw new NotLoggedInException();
        }

        SessionManager.removeSession(username);
    }

    public static void register(String username, String password, String email) {
        //se o utilizador existir
        if (checkIfUserExists(username)) {
            throw new UserAlreadyExists();
        }

        User user = new User(username, password, email);
        DataHandler.insertPlayer(user);
    }

    //true -> logged in, false -> not logged in
    private static boolean checkIfUserLoggedIn(String username) {

        for (UserSession userSession : SessionManager.getUserSessions()) {
            if (userSession.getUser().getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    //true -> existe, false -> nao existe
    private static boolean checkIfUserExists(String username) {
        User aux = DataHandler.selectUser(username);

        return aux != null;
    }
}
