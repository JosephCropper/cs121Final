import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Pokemon implements java.io.Serializable {
    //HP, ATK, DEF, SPATK, SPDEF, SPD
    private int[] nature = {0, 0};
    private int[] maxStats = new int[6];
    private int[] effortValues = new int[6];
    private final int[] gameStats = new int[6];
    private int status = 0;
    //healthy, burn, poison, paralasis, sleep, drowsy
    private String name, ability = "", item;
    private String[] types;
    private String useMove;
    private int critTier = 1;
    private boolean flinch = false;
    private boolean recharge = false;
    private int sleepTurns = 0;
    private boolean trapped = false;
    private int trappedTurns = 0;
    private int poisonTurns =0;


    public void setPoisonTurns(int inturns){ poisonTurns = inturns; }
    public int getPoisonTurns(){ return poisonTurns; }

    private final int[] movePP = new int[4];
    private final String[] moves = {"-", "-", "-", "-"};

    //For Creation
    private ArrayList<String> possibleAbilities;
    private ArrayList<Integer> scoreValues;
    private ArrayList<String> moveList;

    public Pokemon(){
        this.ability = " ";
    }
    public Pokemon(int[] inNature, int[] inMaxStats,int[] inEVs, String inName, String inAbility, String inItem, String[] inTypes){
        nature = inNature;
        maxStats = inMaxStats;
        name = inName;
        ability = inAbility;
        item = inItem;
        effortValues = inEVs;
        types = inTypes;
    }

    public void initMon(){
        this.maxStats[0] = ((2*this.scoreValues.get(0) + this.effortValues[0]/4 + 131) *100)/100 + 10;
        for (int i = 1; i < 6; i++){
            this.maxStats[i] = (((2*this.scoreValues.get(i) + this.effortValues[i]/4 + 131)* 100)/100 + 5);
            if (this.nature[0] == i && this.nature[1] != i){
                this.maxStats[i] *= 1.1;
            }
            if (this.nature[1] == i && this.nature[0] != i){
                this.maxStats[i] *= .9;
            }
        }
        System.arraycopy(this.maxStats, 0, this.gameStats, 0, 6);
    }

    public void newMon(String inName, String[] inTypes, ArrayList<String> inAbilities, ArrayList<Integer> inScoreValues, ArrayList<String> inMoveList){
        name = inName;
        types = inTypes;
        possibleAbilities = inAbilities;
        scoreValues = inScoreValues;
        moveList = inMoveList;
    }

    public void editAbility(){
        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        int input = 0;
        while (loopControl){
            System.out.println("Current ability: " + this.ability);
            for (int i = 0; i < possibleAbilities.size(); i++){
                System.out.println(i + ") " + possibleAbilities.get(i));
            }
            try{
                input = scan.nextInt();
                for (int i = 0; i < possibleAbilities.size(); i++){
                    if (input == i){
                        ability = possibleAbilities.get(i);
                        if (ability.compareToIgnoreCase("super_luck") == 0){
                            this.critTier++;
                        }
                        loopControl = false;
                    }
                }
            }
            catch (Exception e){
                System.err.println("Unrecognized input!");
            }
        }
    }
    public void editMoves(){
        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        int input = 5;
        while (loopControl){
            System.out.println("--- Edit moves ---");
            System.out.println("1) " + this.moves[0] + " | 2) " + this.moves[1]);
            System.out.println("3) " + this.moves[2] + " | 4) " + this.moves[3] + "\n5) Exit");
            try{
                input = scan.nextInt();
            }
            catch(Exception e){
                System.err.println("Unrecognized input!");
            }
            switch(input){
                case 5:
                    return;
                default:
                    try{
                        this.changeMove(input-1);
                    }
                    catch (Exception e){
                        System.err.println("Unrecognized input!");
                    }
                    break;
            }
        }
    }

    public void editEvs(){
        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        int input, evTotal = 0;
        while (loopControl){
            evTotal = 510;
            for (int i = 0; i < 6; i++){
                evTotal -= effortValues[i];
            }
            System.out.println(evTotal + " Points to spend");
            System.out.println("1) Hp: " + effortValues[0]);
            System.out.println("2) Atk: " + effortValues[1]);
            System.out.println("3) Def: " + effortValues[2]);
            System.out.println("4) SpAtk: " + effortValues[3]);
            System.out.println("5) SpDef: " + effortValues[4]);
            System.out.println("6) Speed: " + effortValues[5]);
            System.out.println("7) Nature: +" + nature[0] + ", -" + nature[1] +"\n8) Exit");
            System.out.println("Input: ");
            try{
                input = scan.nextInt();
                if (input > 0 && input < 7){
                    System.out.println("Enter point difference: ");
                    int temp = effortValues[input - 1];
                    int secondaryInput = scan.nextInt();
                    if ((effortValues[input-1] + secondaryInput) > 252 || (effortValues[input - 1] + secondaryInput) < 0 || (evTotal - secondaryInput) < 0){
                        System.err.println("Ev err...");
                    }
                    else{
                        evTotal -= secondaryInput;
                        effortValues[input - 1] += secondaryInput;
                    }
                }
                else if(input == 7){
                    System.out.println("Positive stat: (2-6) ");
                    input = scan.nextInt();
                    if (input >1 && input < 7){
                        nature[0] = input;
                    }
                    else{
                        System.err.println("Unrecognized input! returning...");
                        return;
                    }
                    System.out.println("Negative stat: ");
                    input = scan.nextInt();
                    if (input > 1 && input < 7){
                        nature[1] = input;
                    }
                }
                else if (input == 8){
                    return;
                }
                else{
                    System.err.println("Unrecognized input!");
                }
            }
            catch (Exception e){
                System.err.println("Unrecognized input!");
            }

        }
    }
    public void changeMove(int moveNum){
        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        int input;
        while (loopControl){
            System.out.println(this.moves[moveNum]);
            System.out.println("Select new move:\n------");
            for (int i = 0; i < moveList.size(); i++){
                System.out.println(i + ") " + moveList.get(i));
            }
            try{
                input = scan.nextInt();
                this.moves[moveNum] = moveList.get(input);
                loopControl = false;
            }
            catch (Exception e){
                System.err.println("Unrecognized input!");
                scan.next();
            }
        }
    }

    public void editMon(){
        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        String input = "";
        while (loopControl){
            System.out.println("--- Edit pokemon ---\nPokemon: " + this.name);
            System.out.println("1) Edit Moves\n2) Edit Ability\n3) Edit Item\n4) Edit EVs\n5) Exit");
            input = scan.next();
            switch(input.toLowerCase()){
                case "1":
                case "moves":
                case "edit moves":
                    this.editMoves();
                    break;
                case "2":
                case "ability":
                case "edit ability":
                    if (possibleAbilities.size() == 1)
                    {
                        System.out.println("Only one ability!");
                    }
                    else{
                        this.editAbility();
                    }
                    break;
                case "3":
                case "item":
                case "edit item":
                    System.out.println("coming soon!");
                    break;
                case "4":
                case "evs":
                case "edit evs":
                    this.editEvs();
                    break;
                case "5":
                case "exit":
                    return;
                default:
                    System.err.println("Input not recognized!");
                    break;
            }

        }
    }
    public int[] getNature(){ return nature; }
    public int[] getMaxStats(){ return maxStats; }
    public int getMaxStats(int statNum){ return maxStats[statNum]; }
    public int[] getEVs(){ return effortValues; }
    public int[] getGameStats(){ return gameStats; }
    public int getGameStats(int statNum){ return gameStats[statNum]; }
    public String getName(){ return name; }
    public String getAbility(){ return ability; }
    public String getItem(){ return item; }
    public String[] getTypes(){ return types; }
    public String getUseMove() { return useMove; }
    public int getStatus() { return status; }
    public String getMove(int moveNum){ return this.moves[moveNum]; }
    public int getCritTier(){ return critTier; }
    public boolean getFlinch(){ return flinch; }
    public boolean getRecharge(){ return recharge; }
    public int getSleepTurns(){ return sleepTurns; }
    public boolean getTrapped(){ return trapped; }
    public int getTrappedTurns(){ return trappedTurns; }
    public void printMoves(){
        for (int i = 0; i < 4; i++){
            System.out.print("(" + (i+1) + ") " + this.moves[i] + "\t\t");
            if (i == 1 || i == 3){
                System.out.println();
            }
        }
    }


    public void setUseMove(String inMoveName) { useMove = inMoveName; }
    public void setName(String inName){ name = inName; }

    public void setSleepTurns(int newSleepTurns){ sleepTurns = newSleepTurns; }
    public void setNature(int[] inNature){ nature = inNature; }
    public void setEffortValue(int value, int stat){ effortValues[stat] = value; }
    public void setStatus(int newStatus) {
        if (newStatus != 2 || (this.types[0].compareToIgnoreCase("poison") != 0
                && this.types[1].compareToIgnoreCase("poison") != 0
                && this.types[0].compareToIgnoreCase("steel") != 0
                && this.types[1].compareToIgnoreCase("steel") != 0))
        {
            status = newStatus;
        }

        if (this.ability.compareToIgnoreCase("marvel_scale") ==0 && status !=0){
            this.setGameStats(2, (int)(this.getGameStats(2)*1.5));
        }
        else if (this.ability.compareToIgnoreCase("marvel_scale") == 0 && status == 0){
            this.setGameStats(2, (int)(this.getGameStats(2)*.75));
        }

    }
    public void setGameStats( int statNum, int newNum ){ this.gameStats[statNum] = newNum; }
    public void setFlinch(boolean inFlinch){ flinch = inFlinch; }
    public void setRecharge(boolean inRecharge){ recharge = inRecharge;}
    public void setTrapped(boolean inTrapped){ trapped = inTrapped;}
    public void setTrappedTurns(int inTrappedTurns){ trappedTurns = inTrappedTurns;}


    public String preview(){
        return (this.name + " | " + this.types[0] + " " + this.types[1]);
    }


}
