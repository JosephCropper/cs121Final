import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class BattleManager {
    private ArrayList<Team> teamList = new ArrayList<>();
    private Team teamOne, teamTwo;
    private MoveManager moveManager = new MoveManager();

    public void start(ArrayList<Team> teams){
        Scanner scan = new Scanner(System.in);
        String input = "";
        boolean teamOneSet = false; boolean teamTwoSet = false;
        teamList = teams;
        System.out.println("!!TEAM SELECT!!\n Player one, choose your team!\n~~----~~");
        for (int i = 0; i < teamList.size(); i++){
            if (teamList.get(i).getMonNum() > 0){
                System.out.println(teamList.get(i).getTeamName());
            }
        }
        try{
            input = scan.next();
            for (int i = 0; i < teamList.size(); i++){
                if (teamList.get(i).getTeamName().compareTo(input) == 0 && teamList.get(i).getMonNum() > 0){
                    teamOne = teamList.get(i);
                    teamOneSet = true;
                }
            }
            if (!teamOneSet){
                System.err.println("Team Not Found. returning...");
                return;
            }
            System.out.println("Player two, choose your team!\n~~----~~");
            for (int i = 0; i < teamList.size(); i++){
                if (teamList.get(i).getMonNum() > 0 && teamList.get(i).getTeamName().compareTo(teamOne.getTeamName()) != 0){
                    System.out.println(teamList.get(i).getTeamName());
                }
            }
            try {
                input = scan.next();
                for (int i = 0; i < teamList.size(); i++) {
                    if (teamList.get(i).getTeamName().compareTo(input) == 0 && teamList.get(i).getMonNum() > 0) {
                        teamTwo = teamList.get(i);
                        teamTwoSet = true;
                    }
                }
                if (!teamTwoSet) {
                    System.err.println("Team Not Found. returning...");
                    return;
                }
                System.out.println("Starting Battle!");
                this.battleStart(teamOne, teamTwo);
            }
            catch(Exception e){}
        }
        catch(Exception e){}
    }

    public void battleStart(Team teamOne, Team teamTwo){
        System.out.println(teamOne.getTeamName() + "\n" + teamTwo.getTeamName());
        Random rand = new Random();
        int turnOrder = rand.nextInt(1, 3);
        if (turnOrder == 1){
            System.out.println("Player one, choose your first mon: ");
            this.initMons(teamOne);
            this.selectMon(teamOne);
            System.out.println("Player two, choose your first mon: ");
            this.initMons(teamTwo);
            this.selectMon(teamTwo);
        }
        else{
            System.out.println("Player two, choose your first mon: ");
            this.initMons(teamTwo);
            this.selectMon(teamTwo);
            System.out.println("Player one, choose your first mon: ");
            this.initMons(teamOne);
            this.selectMon(teamOne);
        }
        System.out.println("Battle start!");
        System.out.println("Player one sent out " + teamOne.getSelectedMon().getName());
        System.out.println("Player two sent out " + teamTwo.getSelectedMon().getName());
        while(true) {
            this.turnLoop(teamOne, teamTwo);
        }
    }

    public void turnLoop(Team teamOne, Team teamTwo){
        Team[] teams = {teamOne, teamTwo};
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        boolean gameContinue = true;
        int input = 0;
        int fasterMon = -1;
        int slowerMon = -1;
        while (gameContinue){
            if (teamOne.getSelectedMon().getGameStats(5) > teamTwo.getSelectedMon().getGameStats(5)){
                fasterMon = 0;
                slowerMon = 1;
            }
            else if (teamOne.getSelectedMon().getGameStats(5) < teamTwo.getSelectedMon().getGameStats(5)){
                fasterMon = 1;
                slowerMon = 0;
            }
            else{
                fasterMon = rand.nextInt(0,2);
                slowerMon = 1 - fasterMon;
            }

            boolean loopControl = true;
            input = 0;
            int turn = 2;
            while(loopControl) {
                System.out.println("Pokemon: " + teams[fasterMon].getSelectedMon().getName() + "\nHealth: " + teams[fasterMon].getSelectedMon().getGameStats(0) + "/" + teams[fasterMon].getSelectedMon().getMaxStats(0));
                System.out.println("(1) Moves\n(2) Pokemon");
                if (teams[fasterMon].getSelectedMon().getRecharge()) {
                    System.out.println(teams[fasterMon].getSelectedMon().getName() + " needs to recharge!");
                    teams[fasterMon].getSelectedMon().setRecharge(false);
                    teams[fasterMon].getSelectedMon().setUseMove("");
                    int temp = fasterMon;
                    fasterMon = slowerMon;
                    slowerMon = temp;
                    turn--;
                } else {
                    try {
                        input = scan.nextInt();
                        switch (input) {
                            case 1:
                                boolean moveLoopControl = true;
                                while (moveLoopControl) {
                                    teams[fasterMon].getSelectedMon().printMoves();
                                    System.out.println("(5) Back");
                                    try {
                                        input = scan.nextInt();
                                        for (int i = 0; i < 4; i++) {
                                            if ((input - 1) == (i) && teams[fasterMon].getSelectedMon().getMove(i).compareTo("-") != 0) {
                                                teams[fasterMon].getSelectedMon().setUseMove(teams[fasterMon].getSelectedMon().getMove(i));
                                                moveLoopControl = false;
                                                int temp = fasterMon;
                                                fasterMon = slowerMon;
                                                slowerMon = temp;
                                                turn--;
                                            }
                                        }
                                        if (input == 5){
                                            moveLoopControl = false;
                                        }
                                    } catch (Exception e) {
                                        System.err.println("Unrecognized input!");
                                    }
                                }
                                break;
                            case 2:
                                if (teams[fasterMon].getSelectedMon().getTrapped()) {
                                    System.out.println(teams[fasterMon].getSelectedMon().getName() + " is trapped!");
                                } else {
                                    if (this.selectMonIng(teams[fasterMon])) {
                                        System.out.println("Sent out " + teams[fasterMon].getSelectedMon().getName());
                                        teams[fasterMon].getSelectedMon().setUseMove("SWITCHING");
                                        int temp = fasterMon;
                                        fasterMon = slowerMon;
                                        slowerMon = temp;
                                        turn--;
                                    }
                                }
                                break;
                            default:
                                throw new Exception();
                        }
                    } catch (Exception e) {
                        System.err.println("Unrecognized input!");
                        scan.next();
                    }
                }
                if (turn == 0) {

                    this.useMove(slowerMon, fasterMon, teams[slowerMon].getSelectedMon().getUseMove(), teams[fasterMon].getSelectedMon().getUseMove(), teams);


                    teams[slowerMon].getSelectedMon().setFlinch(false);
                    teams[fasterMon].getSelectedMon().setFlinch(false);

                    if (teams[fasterMon].getSelectedMon().getStatus() == 2){
                        teams[fasterMon].getSelectedMon().setGameStats(0, ( teams[fasterMon].getSelectedMon().getGameStats(0) - (int)(teams[fasterMon].getSelectedMon().getPoisonTurns()/16.0 * (teams[fasterMon].getSelectedMon().getMaxStats(0)))));
                        if (teams[fasterMon].getSelectedMon().getGameStats(0) <= 0){
                            teams[fasterMon].getSelectedMon().setGameStats(0, 0);
                            System.out.println(teams[fasterMon].getSelectedMon().getName() + " fainted!");
                            boolean canContinue = false;
                            for (int i = 0; i < teams[fasterMon].getMonNum(); i++) {
                                if (teams[fasterMon].getMon(i).getGameStats(0) > 0) {
                                    canContinue = true;
                                }
                            }
                            if (canContinue) {
                                this.selectMon(teams[fasterMon]);
                            }
                            else {
                                System.out.println("Battle over!");
                                System.exit(0);
                            }
                        }
                        else{
                            teams[fasterMon].getSelectedMon().setPoisonTurns(1+teams[fasterMon].getSelectedMon().getPoisonTurns());
                        }
                    }
                    if (teams[slowerMon].getSelectedMon().getStatus() == 2){
                        teams[slowerMon].getSelectedMon().setGameStats(0, ( teams[slowerMon].getSelectedMon().getGameStats(0) - (int)(teams[slowerMon].getSelectedMon().getPoisonTurns()/16.0 * (teams[slowerMon].getSelectedMon().getMaxStats(0)))));
                        if (teams[slowerMon].getSelectedMon().getGameStats(0) <= 0){
                            teams[slowerMon].getSelectedMon().setGameStats(0, 0);
                            System.out.println(teams[slowerMon].getSelectedMon().getName() + " fainted!");
                            boolean canContinue = false;
                            for (int i = 0; i < teams[slowerMon].getMonNum(); i++) {
                                if (teams[slowerMon].getMon(i).getGameStats(0) > 0) {
                                    canContinue = true;
                                }
                            }
                            if (canContinue) {
                                this.selectMon(teams[slowerMon]);
                            }
                            else {
                                System.out.println("Battle over!");
                                System.exit(0);
                            }
                        }
                        else{
                            teams[slowerMon].getSelectedMon().setPoisonTurns(1+teams[slowerMon].getSelectedMon().getPoisonTurns());
                        }
                    }
                    //---------------

                    if (teams[fasterMon].getSelectedMon().getStatus() == 1){
                        teams[fasterMon].getSelectedMon().setGameStats(0, ( teams[fasterMon].getSelectedMon().getGameStats(0) - (int)(1/16.0 * (teams[fasterMon].getSelectedMon().getMaxStats(0)))));
                        if (teams[fasterMon].getSelectedMon().getGameStats(0) <= 0){
                            teams[fasterMon].getSelectedMon().setGameStats(0, 0);
                            System.out.println(teams[fasterMon].getSelectedMon().getName() + " fainted!");
                            boolean canContinue = false;
                            for (int i = 0; i < teams[fasterMon].getMonNum(); i++) {
                                if (teams[fasterMon].getMon(i).getGameStats(0) > 0) {
                                    canContinue = true;
                                }
                            }
                            if (canContinue) {
                                this.selectMon(teams[fasterMon]);
                            }
                            else {
                                System.out.println("Battle over!");
                                System.exit(0);
                            }
                        }
                    }
                    if (teams[slowerMon].getSelectedMon().getStatus() == 1){
                        teams[slowerMon].getSelectedMon().setGameStats(0, ( teams[slowerMon].getSelectedMon().getGameStats(0) - (int)(1/16.0 * (teams[slowerMon].getSelectedMon().getMaxStats(0)))));
                        if (teams[slowerMon].getSelectedMon().getGameStats(0) <= 0){
                            teams[slowerMon].getSelectedMon().setGameStats(0, 0);
                            System.out.println(teams[slowerMon].getSelectedMon().getName() + " fainted!");
                            boolean canContinue = false;
                            for (int i = 0; i < teams[slowerMon].getMonNum(); i++) {
                                if (teams[slowerMon].getMon(i).getGameStats(0) > 0) {
                                    canContinue = true;
                                }
                            }
                            if (canContinue) {
                                this.selectMon(teams[slowerMon]);
                            }
                            else {
                                System.out.println("Battle over!");
                                System.exit(0);
                            }
                        }
                    }

                    if (teams[fasterMon].getSelectedMon().getTrapped()) {
                        if (teams[fasterMon].getSelectedMon().getTrappedTurns() == 4) {
                            System.out.println(teams[fasterMon].getSelectedMon().getName() + " broke free!");
                            teams[fasterMon].getSelectedMon().setTrapped(false);
                        } else {
                            teams[fasterMon].getSelectedMon().setTrappedTurns(teams[fasterMon].getSelectedMon().getTrappedTurns() + 1);
                            teams[fasterMon].getSelectedMon().setGameStats(0, ((int) (teams[fasterMon].getSelectedMon().getGameStats(0) - teams[fasterMon].getSelectedMon().getMaxStats(0) / 8)));
                            System.out.println(teams[fasterMon].getSelectedMon().getName() + " was hurt by its bindings!");
                            if (teams[fasterMon].getSelectedMon().getGameStats(0) <= 0) {
                                System.out.println(teams[fasterMon].getSelectedMon().getName() + " fainted!");
                                boolean canContinue = false;
                                for (int i = 0; i < teams[fasterMon].getMonNum(); i++) {
                                    if (teams[fasterMon].getMon(i).getGameStats(0) > 0) {
                                        canContinue = true;
                                    }
                                }
                                if (canContinue) {
                                    this.selectMon(teams[fasterMon]);
                                }
                                else {
                                    System.out.println("Battle over!");
                                    System.exit(0);
                                }
                            }
                        }
                    }
                    if (teams[slowerMon].getSelectedMon().getTrapped()) {
                        if (teams[slowerMon].getSelectedMon().getTrappedTurns() == 4) {
                            System.out.println(teams[slowerMon].getSelectedMon().getName() + " broke free!");
                            teams[slowerMon].getSelectedMon().setTrapped(false);
                        } else {
                            teams[slowerMon].getSelectedMon().setTrappedTurns(teams[slowerMon].getSelectedMon().getTrappedTurns() + 1);
                            teams[slowerMon].getSelectedMon().setGameStats(0, ((int) (teams[slowerMon].getSelectedMon().getGameStats(0) - teams[slowerMon].getSelectedMon().getMaxStats(0) / 8)));
                            System.out.println(teams[slowerMon].getSelectedMon().getName() + " was hurt by its bindings!");
                            if (teams[slowerMon].getSelectedMon().getGameStats(0) <= 0) {
                                System.out.println(teams[slowerMon].getSelectedMon().getName() + " fainted!");
                                boolean canContinue = false;
                                for (int i = 0; i < teams[slowerMon].getMonNum(); i++) {
                                    if (teams[slowerMon].getMon(i).getGameStats(0) > 0) {
                                        canContinue = true;
                                    }
                                }
                                if (canContinue) {
                                    this.selectMon(teams[slowerMon]);
                                }
                                else {
                                    System.out.println("Battle over!");
                                    System.exit(0);
                                }
                            }
                        }
                    }

                    teams[fasterMon].reflectCheck();
                    teams[slowerMon].reflectCheck();
                    teams[fasterMon].lightScreenCheck();
                    teams[slowerMon].lightScreenCheck();
                    moveManager.weatherCheck();

                    loopControl = false;
                }
            }


        }
    }

    public void useMove(int slowerMon, int fasterMon, String monTwoMoveName, String monOneMoveName, Team[] teams){
        int moveOrder = moveManager.moveOrder(monOneMoveName, monTwoMoveName);
        boolean canContinue = true;
        switch (moveOrder){
            case 0:
            case 1:
                moveManager.useMove(teams[fasterMon].getSelectedMon(), teams[slowerMon].getSelectedMon(), monOneMoveName, teams, fasterMon);
                if (teams[slowerMon].getSelectedMon().getGameStats(0) > 0){
                    if (!teams[slowerMon].getSelectedMon().getFlinch()) {
                        moveManager.useMove(teams[slowerMon].getSelectedMon(), teams[fasterMon].getSelectedMon(), monTwoMoveName, teams, slowerMon);
                    }
                    else{
                        teams[slowerMon].getSelectedMon().setFlinch(false);
                        System.out.println(teams[slowerMon].getSelectedMon().getName() + " flinched!");
                        if (teams[slowerMon].getSelectedMon().getAbility().toLowerCase().compareTo("steadfast") == 0){
                            System.out.println(teams[slowerMon].getSelectedMon().getName() + " sped up!");
                            teams[slowerMon].getSelectedMon().setGameStats(5, (int)(teams[slowerMon].getSelectedMon().getGameStats(5)*1.5));
                        }
                    }
                    if (teams[fasterMon].getSelectedMon().getGameStats(0) <=0){
                        canContinue = false;
                        for (int i = 0; i < teams[fasterMon].getMonNum(); i++){
                            if (teams[fasterMon].getMon(i).getGameStats(0) >0){
                                canContinue = true;
                            }
                        }
                        if (canContinue){
                            selectMon(teams[fasterMon]);
                        }
                        else{
                            System.out.println("Battle over!");
                            System.exit(0);
                        }
                    }
                }
                else{
                    canContinue = false;
                    for (int i = 0; i < teams[slowerMon].getMonNum(); i++){
                        if (teams[slowerMon].getMon(i).getGameStats(0) >0){
                            canContinue = true;
                        }
                    }
                    if (canContinue){
                        selectMon(teams[slowerMon]);
                    }
                    else{
                        System.out.println("Battle over!");
                        System.exit(0);
                    }
                }
                break;
            case 2:
                moveManager.useMove(teams[slowerMon].getSelectedMon(), teams[fasterMon].getSelectedMon(), monTwoMoveName, teams, slowerMon);
                if (teams[fasterMon].getSelectedMon().getGameStats(0) > 0){
                    if (!teams[fasterMon].getSelectedMon().getFlinch()) {
                        moveManager.useMove(teams[fasterMon].getSelectedMon(), teams[slowerMon].getSelectedMon(), monOneMoveName, teams, fasterMon);
                    }
                    else{
                        teams[fasterMon].getSelectedMon().setFlinch(false);
                        System.out.println(teams[fasterMon].getSelectedMon().getName() + " flinched!");
                        if (teams[fasterMon].getSelectedMon().getAbility().toLowerCase().compareTo("steadfast") == 0){
                            System.out.println(teams[fasterMon].getSelectedMon().getName() + " sped up!");
                            teams[fasterMon].getSelectedMon().setGameStats(5, (int)(teams[fasterMon].getSelectedMon().getGameStats(5)*1.5));
                        }
                    }
                    if (teams[slowerMon].getSelectedMon().getGameStats(0) <= 0){
                        canContinue = false;
                        for (int i = 0; i < teams[slowerMon].getMonNum(); i++){
                            if (teams[slowerMon].getMon(i).getGameStats(0) >0){
                                canContinue = true;
                            }
                        }
                        if (canContinue){
                            selectMon(teams[slowerMon]);
                        }
                        else{
                            System.out.println("Battle over!");
                            System.exit(0);
                        }
                    }
                }
                else{
                    canContinue = false;
                    for (int i = 0; i < teams[fasterMon].getMonNum(); i++){
                        if (teams[fasterMon].getMon(i).getGameStats(0) >0){
                            canContinue = true;
                        }
                    }
                    if (canContinue){
                        selectMon(teams[fasterMon]);
                    }
                    else{
                        System.out.println("Battle over!");
                        System.exit(0);
                    }
                }
        }
    }

    public void initMons(Team team){
        for (int i = 0; i < team.getMonNum(); i++){
            try {
                team.getMon(i).initMon();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public boolean selectMonIng(Team team){
        System.out.println("--------");
        Scanner scan = new Scanner(System.in);
        boolean loopControl = true;
        String input = "";
        while (loopControl) {
            for (int i = 0; i < team.getMonNum(); i++) {
                if (team.getMon(i).getGameStats(0) > 0) {
                    System.out.println(team.getMon(i).getName() + " | " + team.getMon(i).getGameStats(0) + "/" + team.getMon(i).getMaxStats(0));
                }
            }
            System.out.println("Enter mon name or 1-" + team.getMonNum() + "\n 8 to return");
            try {
                input = scan.next();
                for (int i = 0; i < team.getMonNum(); i++) {
                    boolean intInput = false;
                    try {
                        intInput = Integer.parseInt(input) == i + 1;
                        if (Integer.parseInt(input) == 8){
                            loopControl = false;
                            return false;
                        }
                    } catch (Exception e) {}

                    if (team.getMon(i).getName().compareToIgnoreCase(input) == 0 || intInput && team.getMon(i).getName().compareTo(team.getSelectedMon().getName()) != 0) {
                        try {
                            for (int x = 1; x < 6; x++) {
                                team.getSelectedMon().setGameStats(x, team.getSelectedMon().getMaxStats(x));
                            }
                        }
                        catch(Exception e){
                        }

                        team.setSelectedMonNum(i);
                        loopControl = false;
                        return true;
                    }
                }
            } catch (Exception e) {}
        }
        return false;
    }
    public void selectMon(Team team){
        System.out.println("--------");
        Scanner scan = new Scanner(System.in);
        boolean loopControl = true;
        String input = "";
        while (loopControl) {
            for (int i = 0; i < team.getMonNum(); i++) {
                if (team.getMon(i).getGameStats(0) > 0) {
                    System.out.println(team.getMon(i).getName() + " | " + team.getMon(i).getGameStats(0) + "/" + team.getMon(i).getMaxStats(0));
                }
            }
            System.out.println("Enter mon name or 1-" + team.getMonNum());
            try {
                input = scan.next();
                for (int i = 0; i < team.getMonNum(); i++) {
                    boolean intInput = false;
                    try {
                        intInput = Integer.parseInt(input) == i + 1;
                    } catch (Exception e) {
                    }

                    if (team.getMon(i).getName().compareToIgnoreCase(input) == 0 || intInput) {
                        try {
                            for (int x = 1; x < 6; x++) {
                                team.getSelectedMon().setGameStats(x, team.getSelectedMon().getMaxStats(x));
                            }
                        }
                        catch(Exception e){
                        }
                        team.setSelectedMonNum(i);
                        loopControl = false;
                    }
                }
            } catch (Exception e) {
            }
        }

    }


    public static boolean battleCheck(ArrayList<Team> teamList){
        int valid = 0;
        for (int i = 0; i < teamList.size(); i++){
            if (teamList.get(i).getMonNum() > 0){
                valid++;
                if (valid == 2){
                    return true;
                }
            }
        }
        System.err.println("Not enough valid teams!");
        return false;
    }
}
