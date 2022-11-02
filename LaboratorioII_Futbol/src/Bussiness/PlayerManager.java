package Bussiness;

import Data.DBPlayers;
import Presentation.InsertPlayers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PlayerManager {
    
    private DBPlayers dbPlayers = new DBPlayers();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public Date restartDate() {
        
        Date date = new Date(); 
        try {
            date = dateFormat.parse("2004-01-30");
        } catch (ParseException ex) {
            Logger.getLogger(InsertPlayers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
        
    public void insertPlayerInDB( int id, String name, int age, boolean state, int team, String profile, String fortitude, Date debutDate ) {
        try {
            debutDate = dateFormat.parse(dateFormat.format(debutDate));
        } catch (ParseException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int skill = dbPlayers.getSkill(profile, fortitude);
        Player player = new Player(id, name, age, state, team, skill, debutDate);
        dbPlayers.insertPlayer(player);
    }
    
    public HashMap<Integer, HashMap> report1() {
        HashMap<Integer, HashMap> reportOne = dbPlayers.getReport1();
        return reportOne;
    }
    
    public HashMap<Integer, HashMap> report2(Date initialDate, Date finalDate) {
        try {
            initialDate = dateFormat.parse(dateFormat.format(initialDate));
            finalDate = dateFormat.parse(dateFormat.format(finalDate));
        } catch (ParseException ex) {
            Logger.getLogger(PlayerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        HashMap<Integer, HashMap> reportTwo = dbPlayers.getReport2(initialDate, finalDate);
        return reportTwo;
    }
    
    public HashMap<Integer, HashMap> report3(int lowerAge, int upperAge, String team) {
        HashMap<Integer, HashMap> reportThree = dbPlayers.getReport3(lowerAge, upperAge, team);
        return reportThree;
    }
    
    public ArrayList<String> getTeams() {
        ArrayList<String> teamList = dbPlayers.getTeams();
        return teamList;
    }
    
}
