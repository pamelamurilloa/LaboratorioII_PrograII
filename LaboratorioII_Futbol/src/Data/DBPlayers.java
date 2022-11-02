package Data;

import Bussiness.Player;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBPlayers {
    
    DBConexion conexion = new DBConexion();
    private Statement s = null;
    private ResultSet rs = null;
    private Connection connection = null;
    
    public void insertPlayer(Player player) {
        try {
            connection = conexion.conexion();
            s = connection.createStatement();
            int playerInserted = s.executeUpdate("INSERT INTO JUGADORES VALUES ('" + player.getId() + "','" 
                    + player.getName() + "','" 
                    + player.getAge() + "','" 
                    + player.isState() + "','" 
                    + player.getTeam() + "','" 
                    + player.getSkill() + "','" 
                    + player.getDebutDate() + "' )");
            if (playerInserted == 0) {
                System.out.println("Error al insertar jugador");
            } else {
                System.out.println("Jugador insertado exitosamente");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int getSkill(String profile, String fortitude) {
        int skill = -1;
        try {
            connection = conexion.conexion();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT ID_DESTREZA FROM DESTREZAS WHERE UPPER(PERFIL) = '" + profile.toUpperCase() + "' AND UPPER(FORTALEZA) = '" + fortitude.toUpperCase() + "'");
            while (rs.next()) {
                skill = rs.getInt("id_destreza");
            }

        } catch (Exception e) {
            Logger.getLogger(DBPlayers.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return skill;
    }
    
    public ArrayList<String> getTeams() {
        ArrayList<String> teamList = new ArrayList<>();
        try {
            connection = conexion.conexion();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT NOMBRE_EQUIPO FROM EQUIPO");
            while (rs.next()) {
                teamList.add(rs.getString("nombre_equipo"));
            }
        } catch (Exception e) {
            System.out.println("Error en DBPlayers:getTeams()");
        }
        return teamList;
    }
    
    public HashMap<Integer, HashMap> getReport1() {
        HashMap<Integer, HashMap> report1 = new HashMap();
        int count = 0;
        try {
            connection = conexion.conexion();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT J.NOMBRE AS NOMBRE, E.NOMBRE_EQUIPO AS NOMBRE_EQUIPO, D.PERFIL AS PERFIL "
                    + "FROM JUGADORES J "
                    + "LEFT JOIN EQUIPO E "
                    + "ON J.EQUIPO = E.ID_EQUIPO "
                    + "LEFT JOIN DESTREZAS D "
                    + "ON J.DESTREZA = D.ID_DESTREZA "
                    + "WHERE J.EDAD > 20 "
                    + "AND UPPER(D.FORTALEZA) = 'VELOCIDAD'");
            while (rs.next()) {
                HashMap subHashMap = new HashMap();
                subHashMap.put("Name", rs.getString("nombre"));
                subHashMap.put("Team", rs.getString("nombre_equipo"));
                subHashMap.put("Profile", rs.getString("perfil"));
                report1.put(count, subHashMap);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBPlayers.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return report1;
    }
    
    public HashMap<Integer, HashMap> getReport2(Date initialDate, Date finalDate) {
        HashMap<Integer, HashMap> report2 = new HashMap();
        int count = 0;
        try {
            connection = conexion.conexion();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT J.NOMBRE AS NOMBRE, E.NOMBRE_EQUIPO AS NOMBRE_EQUIPO, J.FECHA_DEBUT AS DEBUT FROM JUGADORES J "
                    + "LEFT JOIN EQUIPO E ON J.EQUIPO = E.ID_EQUIPO "
                    + "WHERE FECHA_DEBUT BETWEEN '" + initialDate + "' "
                    + "AND '" + finalDate + "'");
            
            while (rs.next()) {
                HashMap subHashMap = new HashMap();
                subHashMap.put("Name", rs.getString("nombre"));
                subHashMap.put("Team", rs.getString("nombre_equipo"));
                subHashMap.put("Debut", rs.getDate("debut"));
                report2.put(count, subHashMap);
                count++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBPlayers.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return report2;
    }
        
    public HashMap<Integer, HashMap> getReport3(int lowerAge, int upperAge, String teamName) {
        HashMap<Integer, HashMap> report3 = new HashMap();
        try {
            connection = conexion.conexion();
            s = connection.createStatement();
            rs = s.executeQuery("SELECT J.CEDULA AS CEDULA, J.NOMBRE AS NOMBRE, J.EDAD AS EDAD, J.FECHA_DEBUT AS DEBUT "
                    + "FROM JUGADORES J "
                    + "LEFT JOIN EQUIPO E ON J.EQUIPO = E.ID_EQUIPO "
                    + "LEFT JOIN DESTREZAS D ON J.DESTREZA = D.ID_DESTREZA "
                    + "WHERE EDAD BETWEEN '" + lowerAge + "' AND '" + upperAge + "' "
                    + "AND UPPER(E.NOMBRE_EQUIPO) = '" + teamName.toUpperCase() + "' "
                    + "AND UPPER(D.FORTALEZA) = 'CABECEO' "
                    + "AND E.ESTADO = 'true' AND J.FECHA_DEBUT > '01/01/1997' "
                    + "ORDER BY J.NOMBRE");
            while (rs.next()) {
                HashMap subHashMap = new HashMap();
                subHashMap.put("Name", rs.getString("nombre"));
                subHashMap.put("Age", rs.getInt("edad"));
                subHashMap.put("Debut", rs.getDate("debut"));
                report3.put(rs.getInt("cedula"), subHashMap);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBPlayers.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return report3;
    }
    
    public static void main(String[] args) {
        DBPlayers dbp = new DBPlayers();
        HashMap<Integer, HashMap> a = dbp.getReport3(15, 30, "ALAJUELENSE");
        for (Integer key : a.keySet()) {
            System.out.println(key);
            System.out.println(a.get(key).get("Debut"));
        }
        
        HashMap<Integer, HashMap> b = dbp.getReport1();
        for (Integer key : b.keySet()) {
            System.out.println(b.get(key).get("Name"));
        }
        
    }
}
