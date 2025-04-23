import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.*;

public class TeamBuilder {
    public ArrayList<Team> team = new ArrayList<>();
    public TeamBuilder(){
        try{
            FileInputStream file = new FileInputStream("teams");
            ObjectInputStream in = new ObjectInputStream(file);

            team = (ArrayList<Team>)in.readObject();
            in.close();
            file.close();
        }
        catch (Exception e){
            System.out.println("Failed Fetching");
        }
    }


    public ArrayList<Team> init(){
        return team;
    }
    public ArrayList<Team> start(ArrayList<Team> teams){
        boolean loopCheck = true;
        while (loopCheck){
            switch(this.menu().toLowerCase()){
                case "1":
                case "new team":
                case "new":
                    System.out.println("Enter team name: ");
                    Scanner scan = new Scanner(System.in);
                    teams.add(new Team(scan.next()));
                    break;
                case "2":
                case "edit":
                case "edit team":
                    if (teams.size() != 0) {
                        System.out.println("--- Team List ---");
                        for (int i = 0; i < teams.size(); i++) {
                            System.out.println("|| " + teams.get(i).getTeamName());
                        }
                        System.out.println("Select team: ");
                        scan = new Scanner(System.in);
                        String input = scan.next();
                        for (int i = 0; i < teams.size(); i++) {
                            if (input.compareToIgnoreCase(teams.get(i).getTeamName()) == 0) {
                                this.editTeam(teams.get(i), teams);
                                i = teams.size();
                            }
                        }
                    }
                    else{
                        System.err.println("No teams!");
                    }
                    return teams;
                case "3":
                case "exit":
                    loopCheck = false;
                    System.out.println("Returning...");
                    return teams;
                default:
                    System.out.println("Not Recognized");
                    break;
            }
        }
        return teams;
    }

    public void saveTeams(){
        try {
            FileOutputStream file = new FileOutputStream("teams");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(team);
            out.close();
            file.close();
        }
        catch(Exception e){
            System.err.println("Failed Saving");
        }
    }

    public void editTeam(Team team, ArrayList<Team> teams){
        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        String input;
        while (loopControl) {
            System.out.println("Team Name: " + team.getTeamName() + "\n" + team.getMonNum() + "/6 pokemon");
            System.out.println("1) Add Mon\n2) Edit Mon\n3) Change name\n4) Save team\n5) Delete team");
            input = scan.next();
            switch (input.toLowerCase()){
                case "1":
                case "add":
                case "add mon":
                    if (team.getMonNum() < 6){
                        this.newMon(team);
                    }
                    else{
                        System.err.println("Team is full!");
                    }
                    break;
                case "2":
                case "edit":
                case "edit mon":
                    if (team.getMonNum() != 0) {
                        for (int i = 0; i < team.getMonNum(); i++) {
                            System.out.println(team.getMon(i).getName());
                        }
                        System.out.println("Select Pokemon (1-6/name, 7 to exit)");
                        input = scan.next();
                        for (int i = 0; i < team.getMonNum(); i++){
                            boolean intInput = false;
                            try{
                                intInput = Integer.parseInt(input) == i+1;
                            }
                            catch (Exception e) {}
                            if (team.getMon(i).getName().compareToIgnoreCase(input) == 0 || intInput){
                                team.getMon(i).editMon();
                            }
                        }
                    }
                    else{
                        System.err.println("No Mons!");
                    }
                    break;
                case "3":
                case "change":
                case "change name":
                    System.out.println("Enter new team name:");
                    input = scan.next();
                    team.setTeamName(input);
                    break;
                case "4":
                case "save":
                case "save team":
                    loopControl = false;
                    return;
                case "5":
                case "delete":
                case "delete team":
                    teams.remove(team);
                    loopControl = false;
                    return;
                default:
                    System.err.println("Unrecognized input!");
                    break;
            }

        }
    }
    public String menu(){
        boolean loopCheck = true;
        Scanner scan = new Scanner(System.in);
        String input = "";
        while(loopCheck){
            System.out.println("{>Team Builder<}\n[1] New Team\n[2] Edit Team\n[3] Exit");
            try{
                input = scan.next();
                loopCheck = false;
            }
            catch(Exception e){}
        }
        return input;
    }

    public void newMon(Team team){
        File file = new File("./Resources/PokemonData");
        Scanner dex = new Scanner(System.in);
        try{
            dex = new Scanner(file);
        }
        catch(Exception e)
        {
            System.err.println("File not Found!");
        }

        Scanner scan = new Scanner(System.in);
        boolean loopCheck = true; boolean nameCheck = false;
        String input = ""; String dexSearch = "";
        while(loopCheck){
            System.out.println("Enter Mon Name: ");
            input = scan.next().toLowerCase();
            loopCheck = true;
            nameCheck = false;
            dexSearch = "";
            try{
                dex = new Scanner(file);
            }
            catch (Exception e){
            }
            while (dexSearch.compareTo("endlist") != 0 && loopCheck){
                try {
                    try {
                        dexSearch = dex.next().toLowerCase();
                    }
                    catch(Exception e){}
                    if (nameCheck){
                        nameCheck = false;
                        if (dexSearch.compareTo(input) == 0){
                            System.out.println("Pokemon Found!");
                            try {
                                team.addMon(this.initMon(dex, input));
                            }
                            catch (Exception e){

                            }
                            loopCheck = false;
                        }
                    }
                    if (dexSearch.compareTo("----") == 0) {
                        nameCheck = true;
                    }
                }
                catch (Exception e){
                }
            }

        }//LoopCheck
    }


    public Pokemon initMon(Scanner dex, String name){
        Pokemon newMon = new Pokemon();
        try {
            String dexSearch = "";
            ArrayList<String> inAbilities = new ArrayList<String>();
            ArrayList<Integer> inStatValues = new ArrayList<Integer>();
            ArrayList<String> inMovePool = new ArrayList<String>();
            String[] inTypes = new String[2];
            for (int i = 0; i < 2; i++) {
                dexSearch = dex.next().toLowerCase();
                inTypes[i] = dexSearch;
            }
            for (int i = 0; i < 6; i++) {
                dexSearch = dex.next().toLowerCase();
                inStatValues.add(Integer.valueOf(dexSearch));
            }
            dexSearch = dex.next();

            while (dexSearch.compareTo("[") != 0) {
                dexSearch = dex.next();
                inAbilities.add(dexSearch);
            }
            inAbilities.removeLast();
            inAbilities.removeLast();
            while (dexSearch.compareTo("]") != 0) {
                dexSearch = dex.next();
                inMovePool.add(dexSearch);
            }
            inMovePool.removeLast();

            newMon.newMon(name, inTypes, inAbilities, inStatValues, inMovePool);
            return newMon;
        }
        catch (Exception e){
            System.err.println(e);
        }
        return newMon;
    }

    public Team newTeam(String teamName){
        return new Team(teamName);
    }
}
