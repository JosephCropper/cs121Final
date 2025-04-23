import java.io.*;
import java.util.Scanner;

public class Team implements java.io.Serializable {
    private Pokemon[] pokemonList = new Pokemon[6];
    private String teamName;
    private int monNum = 0;
    private int selectedMonNum = -1;
    private boolean reflect = false;
    private boolean lightScreen = false;
    private int reflectTurns = 0;
    private int lightScreenTurns = 0;

    public void reflectCheck(){
        if (reflect){
            reflectTurns++;
            if (reflectTurns == 5){
                System.out.println(teamName + "'s reflect fades away...");
                reflect = false;
                reflectTurns = 0;
            }
        }
    }

    public void lightScreenCheck(){
        if (lightScreen){
            lightScreenTurns++;
            if (lightScreenTurns == 5){
                System.out.println(teamName + "'s lightScreen fades away...");
                lightScreen = false;
                lightScreenTurns = 0;
            }
        }
    }

    public void setReflect(boolean inbool){ reflect = inbool; reflectTurns = 0; }
    public void setlightScreen(boolean inbool){ lightScreen = inbool; lightScreenTurns = 0; }
    public boolean getReflect(){ return reflect; }
    public boolean getLightScreen(){ return lightScreen; }



    public Team(){}
    public Team(String inName){
        teamName = inName;
    }

    public void addMon(Pokemon pkm){
        pokemonList[monNum] = pkm;
        monNum++;
    }

    public int getSelectedMonNum(){ return selectedMonNum; }
    public String getTeamName() {return teamName; }
    public int getMonNum() { return monNum; }
    public Pokemon getMon(int monNumber) { return pokemonList[monNumber]; }
    public Pokemon getSelectedMon() { return pokemonList[selectedMonNum]; }

    public void setTeamName(String inName){ teamName = inName; }
    public void setSelectedMonNum(int selectedMon){ selectedMonNum = selectedMon; }

    public String preview(){
        String rtn = "";
        for (int i = 0; i < monNum; i++){
            rtn = rtn + pokemonList[i].preview() + "\n";
        }
        return (rtn);
    }
}
