package Bussiness;

import Presentation.InsertPlayers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlayerManager {
    
    public void insertPlayerInDB( int id, String name, int age, boolean state, int team, String profile, String fortitude, Date debutDate ) {
        
    }
    
    public Date restartDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(); 
        try {
            date = format.parse("2004-01-30");
        } catch (ParseException ex) {
            Logger.getLogger(InsertPlayers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
    
    public ArrayList<String> getTeams() {

        return null;
    }
    
    public ArrayList<String> getProfile() {
        return null;
    }
    
    public ArrayList<String> getFortitude() {
        return null;
    }
}
