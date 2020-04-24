package de.rhistel.mynote.logic.controller;

/**
 * Handelt den LoginZustand
 * und andere bei Bedard
 */
public class UserController {

    //region 0.Konstanten
    private static UserController instance;
    //endregion
    //region 1. Decl. and Init Attribute
    private boolean userIsLoggedIn;
    //endregion

    //region 2. Konsturkor

    /**
     * Privater Standardkonstruktor
     * zum direkten initalisieren
     * der Attribute.
     * Private weil kein Zugriff von aussen
     * moeglich sein soll
     */
    private UserController() {
        this.userIsLoggedIn = false;
    }
    //endregion

    /**
     * Gibt die Eiinzige instanz zurueck
     * @return
     */
    public static synchronized UserController getInstance(){
        if(instance == null){
            instance = new UserController();
        }

        return instance;
    }

    public boolean isUserIsLoggedIn() {
        return userIsLoggedIn;
    }

    public void setUserIsLoggedIn(boolean userIsLoggedIn) {
        this.userIsLoggedIn = userIsLoggedIn;
    }
}
