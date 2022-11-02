package Bussiness;

import java.util.Date;


public class Player {
    private int id;
    private String name;
    private int age;
    private boolean state;
    private int team;
    private int skill;
    private Date debutDate;

    public Player(int id, String name, int age, boolean state, int team, int skill, Date debutDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.state = state;
        this.team = team;
        this.skill = skill;
        this.debutDate = debutDate;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public Date getDebutDate() {
        return debutDate;
    }

    public void setDebutDate(Date debutDate) {
        this.debutDate = debutDate;
    }

}
