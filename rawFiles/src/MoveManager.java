import java.util.ArrayList;
import java.util.Random;

public class MoveManager {
    private ArrayList<Move> moveList = new ArrayList<>();
    private int weather = 0;
    private int weatherTurns = 0;


    //0 = clear, 1 = Rain, 2 = Sun, 3 = Sand

    public void weatherCheck(){
        if (weather != 0){
            weatherTurns++;
            if (weatherTurns == 5){
                System.out.println("the weather cleared up...");
                weather = 0;
                weatherTurns = 0;
            }
        }
    }



    public MoveManager(){
        moveList.add(new Move("Calm_Mind", "psychic", 0, 100, 0, false, false, false));
        moveList.add(new Move("Confide", "normal", 0, 100, 0, false, false, false));
        moveList.add(new Move("Curse", "Ghost", 0, 100, 0, false, false, false));
        moveList.add(new Move("Dark_Pulse", "dark", 80, 100, 0, false, false, false));
        moveList.add(new Move("Dream_Eater", "psychic", 0, 100, 0, false, false, false));
        moveList.add(new Move("Facade", "normal", 70, 100, 0, false, true, true));
        moveList.add(new Move("Feint_Attack", "dark", 60, 110, 0, false, true, true));
        moveList.add(new Move("Frustration", "normal", 102, 100, 0, false, true, true));
        moveList.add(new Move("Giga_Impact", "normal", 150, 100, 0, false, false, false));
        moveList.add(new Move("Hyper_Beam", "normal", 150, 90, 0, false, false, false));
        moveList.add(new Move("Hypnosis", "psychic", 0, 60, 0, false, false ,false));
        moveList.add(new Move("Icy_Wind", "ice", 55, 90, 0, false, false, false ));
        moveList.add(new Move("Infestation", "bug", 20, 100, 0, false, true, false));
        moveList.add(new Move("Nasty_Plot", "dark", 0, 100, 0, false, false, false));
        moveList.add(new Move("Ominous_Wind", "ghost", 60, 100, 0, false, false, false));
        moveList.add(new Move("Pain_Split", "normal", 0, 100, 0, false, false, false));
        moveList.add(new Move("Psychic", "psychic", 90, 100, 0, false, false, false));
        moveList.add(new Move("Reflect", "psychic", 0,  100, 0, false, false, false));
        moveList.add(new Move("Light_Screen", "psychic", 0, 100, 0, false, false, false));
        moveList.add(new Move("Rain_Dance", "water", 0, 100, 0, false, false, false));
        moveList.add(new Move("Rest", "normal", 0, 100, 0, false, false, false));
        moveList.add(new Move("Rock_Tomb", "rock", 65, 95, 0, false, true, false));
        moveList.add(new Move("Silver_Wind", "bug", 60, 100, 0, false, false, false));
        moveList.add(new Move("Energy_Ball", "grass", 90, 100, 0, false, false, false));
        moveList.add(new Move("Sludge_Bomb", "poison", 90, 100, 0, false, false, false));
        moveList.add(new Move("Extrasensory", "psychic", 80, 100, 0, false, false, false));
        moveList.add(new Move("Shadow_Ball", "ghost", 80, 100, 0, false, false, false));
        moveList.add(new Move("Air_Slash", "flying", 60, 95, 0, false, false, false));
        moveList.add(new Move("Aura_Sphere", "fighting", 80, 110, 0, false, false, false));
        moveList.add(new Move("Ancient_Power", "rock", 60, 100, 0, false, false, false));
        moveList.add(new Move("Swords_Dance", "normal", 0, 100, 0, false, false, false));
        moveList.add(new Move("Close_Combat", "fighting", 120, 100, 0, false, true, true));
        moveList.add(new Move("Meteor_Mash", "steel", 90, 90, 0, false, true, true));
        moveList.add(new Move("Extreme_Speed", "normal", 90, 100, 1, false, true, true));
        moveList.add(new Move("Scald", "water", 80, 100, 0, false, false, false));
        moveList.add(new Move("Recover", "normal", 0, 100, 0, false, false, false));
        moveList.add(new Move("Toxic", "poison", 0, 90, 0, false, false, false));
        moveList.add(new Move("Ice_Beam", "ice", 80, 100, 0, false, false, false ));
        moveList.add(new Move("Dragon_Claw", "dragon", 80, 100, 0, false, true, true));
        moveList.add(new Move("Earthquake", "ground", 100, 100, 0, false, true, false));


    }

    public int moveOrder(String moveOneName, String moveTwoName){
        //0 = same bracket, 1 = move one moves first, -1 move two moves first
        int moveOnePriority = 0, moveTwoPriority = 0;
        for (int i = 0; i < moveList.size(); i++){
            if (moveOneName.compareToIgnoreCase(moveList.get(i).getName()) == 0){
                moveOnePriority = moveList.get(i).getPriority();
            }
            if (moveTwoName.compareToIgnoreCase(moveList.get(i).getName()) == 0){
                moveTwoPriority = moveList.get(i).getPriority();
            }
        }
        if (moveOnePriority > moveTwoPriority){
            //System.err.println("one prio");
            return 1;
        }
        else if (moveTwoPriority > moveOnePriority){
            //System.err.println("two prio");
            return -1;
        }
        //System.err.println("nul prio");
        return 0;
    }

    public void useMove(Pokemon attackingMon, Pokemon defendingMon, String moveName, Team[] teams, int atkMonNum){
        Move move;
        Random rand = new Random();
        if (attackingMon.getStatus() == 4){
            if (attackingMon.getSleepTurns() == 0){
                System.out.println(attackingMon.getName() + " is asleep!");
                attackingMon.setSleepTurns(attackingMon.getSleepTurns()+1);
            }
            else{
                int wake = rand.nextInt(0, (6-attackingMon.getSleepTurns()));
                if (wake == 0){
                    System.out.println(attackingMon.getName() + " woke up!");
                    attackingMon.setStatus(0);
                }
                else{
                    System.out.println(attackingMon.getName() + " is asleep!");
                    attackingMon.setSleepTurns(attackingMon.getSleepTurns() + 1);
                }
            }
        }
        if(attackingMon.getStatus() != 4) {
            for (int i = 0; i < moveList.size(); i++) {
                if (moveName.compareTo(moveList.get(i).getName()) == 0) {
                    move = moveList.get(i);
                    System.out.println(attackingMon.getName() + " used " + move.getName());
                    //Standard checks

                    int damage = (int) (calcDamage(attackingMon, defendingMon, move, teams, atkMonNum));
                    defendingMon.setGameStats(0, defendingMon.getGameStats(0) - damage);
                    if (move.getContact()) {
                        contactCheck(attackingMon, defendingMon);
                    }
                    if (move.getRecoil()) {
                        attackingMon.setGameStats(0, attackingMon.getGameStats(0) - (damage / 3));
                        if (attackingMon.getGameStats(0) <= 0) {
                            attackingMon.setGameStats(0, 0);
                            System.out.println(attackingMon.getName() + " fainted!");
                        }
                    }
                    if (defendingMon.getGameStats(0) <= 0) {
                        defendingMon.setGameStats(0, 0);
                        System.out.println(defendingMon.getName() + " fainted!");
                    }

                    try {
                        secondaryEffect(attackingMon, defendingMon, move, teams, atkMonNum);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
            }
        }
    }

    public void secondaryEffect(Pokemon attackingMon, Pokemon defendingMon, Move move, Team[] teams, int atkMonNum){
        int bonus = 1;
        Random rand = new Random();
        if (attackingMon.getAbility().compareToIgnoreCase("Serene_Grace") == 0){
            bonus++;
        }
        switch (move.getName().toLowerCase()) {
            case "swords_dance":
                attackingMon.setGameStats(1, (int)(attackingMon.getGameStats(1)*2));
                break;
            case "calm_mind":
                attackingMon.setGameStats(3, (int) (attackingMon.getGameStats(3) * 1.5));
                attackingMon.setGameStats(4, (int) (attackingMon.getGameStats(4) * 1.5));
                break;
            case "nasty_plot":
                attackingMon.setGameStats(3, (int) (attackingMon.getGameStats(3) * 2));
                break;
            case "confide":
                System.out.println(defendingMon.getName() + "'s special attack dropped!");
                defendingMon.setGameStats(3, (int) (defendingMon.getGameStats(3) * .75));
                break;
            case "curse":
                attackingMon.setGameStats(1, (int) (attackingMon.getGameStats(1) * 1.5));
                attackingMon.setGameStats(2, (int) (attackingMon.getGameStats(2) * 1.5));
                attackingMon.setGameStats(5, (int) (attackingMon.getGameStats(5) * .75));
                break;
            case "close_combat":
                attackingMon.setGameStats(2, (int) (attackingMon.getGameStats(2) * .75));
                attackingMon.setGameStats(4, (int) (attackingMon.getGameStats(4) * .75));
                break;
            case "air_slash":
                bonus *=3;
            case "extrasensory":
                if (rand.nextInt(0, 10) < (bonus) && defendingMon.getAbility().toLowerCase().compareTo("inner_focus") != 0) {
                    defendingMon.setFlinch(true);
                }
                break;
            case "recover":
                int recoverAmt = attackingMon.getMaxStats(0)/2;
                attackingMon.setGameStats(0, attackingMon.getGameStats(0)+recoverAmt);
                if (attackingMon.getGameStats(0) > attackingMon.getMaxStats(0)){
                    attackingMon.setGameStats(0, attackingMon.getMaxStats(0));
                }
            case "scald":
                if (rand.nextInt(0, 12) < (bonus*4) && defendingMon.getStatus() == 0){
                    defendingMon.setStatus(1);
                    System.out.println(defendingMon.getName() + " was burned!");
                }
                break;
            case "dark_pulse":
                if (rand.nextInt(0, 10) < (2 * bonus) && defendingMon.getAbility().toLowerCase().compareTo("inner_focus") != 0) {
                    defendingMon.setFlinch(true);
                }
                break;
            case "giga_impact":
            case "hyper_beam":
                attackingMon.setRecharge(true);
                break;
            case "icy_wind":
                System.out.println(defendingMon.getName() + "'s speed dropped!");
                defendingMon.setGameStats(5, (int) (defendingMon.getGameStats(5) * .75));
                break;
            case "infestation":
                if (!defendingMon.getTrapped()) {
                    defendingMon.setTrapped(true);
                    defendingMon.setTrappedTurns(0);
                    System.out.println(defendingMon.getName() + " was trapped!");
                }
                break;
            case "ancient_power":
            case "ominous_wind":
            case "silver_wind":
                if (rand.nextInt(0, 10) < (bonus)) {
                    System.out.println(attackingMon.getName() + " powered up!");
                    attackingMon.setGameStats(1, (int) (attackingMon.getGameStats(1) * 1.5));
                    attackingMon.setGameStats(2, (int) (attackingMon.getGameStats(2) * 1.5));
                    attackingMon.setGameStats(3, (int) (attackingMon.getGameStats(3) * 1.5));
                    attackingMon.setGameStats(4, (int) (attackingMon.getGameStats(4) * 1.5));
                    attackingMon.setGameStats(5, (int) (attackingMon.getGameStats(5) * 1.5));
                }
                break;
            case "pain_split":
                int newHealth = (attackingMon.getGameStats(0) + defendingMon.getGameStats(0)) / 2;
                attackingMon.setGameStats(0, newHealth);
                defendingMon.setGameStats(0, newHealth);
                break;
            case "shadow_ball":
                bonus *=2;
            case "psychic":
            case "energy_ball":
                if (rand.nextInt(0, 10) < (bonus)) {
                    System.out.println(defendingMon.getName() + "'s spdef dropped!");
                    defendingMon.setGameStats(4, (int) (defendingMon.getGameStats(4) * .75));
                }
                break;
            case "reflect":
                if (!teams[atkMonNum].getReflect()){
                    teams[atkMonNum].setReflect(true);
                    System.out.println(attackingMon.getName() + " set up reflect!");
                }
                break;
            case "light_screen":
                if (!teams[atkMonNum].getLightScreen()){
                    teams[atkMonNum].setlightScreen(true);
                    System.out.println(attackingMon.getName() + " set up light screen!");
                }
                break;
            case "rain_dance":
                if(weather!= 1){
                    System.out.println("it started to rain!");
                    weather=1;
                    weatherTurns = 0;
                }
                break;
            case "rest":
                attackingMon.setStatus(4);
                attackingMon.setSleepTurns(0);
                System.out.println(attackingMon.getName() + " fell asleep!");
                attackingMon.setGameStats(0, attackingMon.getMaxStats(0));
                break;
            case "sludge_bomb":
                if (rand.nextInt(0, 12) < (4*bonus) && defendingMon.getStatus() == 0) {
                    System.out.println(defendingMon.getName() + " was poisoned!");
                    defendingMon.setStatus(2);
                    defendingMon.setPoisonTurns(0);
                }
                break;
        }
    }

    public void contactCheck(Pokemon attackingMon, Pokemon defendingMon){
        switch(defendingMon.getAbility().toLowerCase()) {
            case "rough_skin":
                System.out.println(attackingMon.getName() + " was hurt by " + defendingMon.getName() + "\'s " + defendingMon.getAbility());
                attackingMon.setGameStats(0, attackingMon.getGameStats(0) - (attackingMon.getMaxStats(0) / 8));
                if (attackingMon.getGameStats(0) <= 0) {
                    attackingMon.setGameStats(0, 0);
                    System.out.println(attackingMon.getName() + " fainted!");
                }
                break;
        }
    }

    public double calcDamage(Pokemon atkMon, Pokemon defMon, Move move, Team[] teams, int atkMonNum){
        String[] attackingMon = atkMon.getTypes();
        String[] defendingMon = defMon.getTypes();
        String moveType = move.getType();
        double type = 1;
        switch(move.getName().toLowerCase()){
            case "dream_eater":
                if (defMon.getStatus() == 4) {
                    move.setDamage(100);
                }
                break;
            case "Facade":
                if (defMon.getStatus() == 1 || defMon.getStatus() == 2 || defMon.getStatus() == 3){
                    move.setDamage(140);
                }
                break;
        }
        switch (moveType.toLowerCase()){
            case "normal":
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 || defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type*=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 || defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type*=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("ghost") == 0 || defendingMon[1].compareToIgnoreCase("ghost") == 0){
                    type = 0;
                }
                break;
            case "fighting":
                if (defendingMon[0].compareToIgnoreCase("normal") == 0 || defendingMon[1].compareToIgnoreCase("normal") == 0){
                    type*=2;
                }
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 || defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type*=2;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("ice") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ice") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("dark") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dark") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("flying") == 0 ||
                        defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("poison") == 0 ||
                        defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("bug") == 0 ||
                        defendingMon[1].compareToIgnoreCase("bug") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("psychic") == 0 ||
                        defendingMon[1].compareToIgnoreCase("psychic") == 0){
                    type*=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("fairy") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fairy") == 0){
                    type*= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("ghost") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ghost") == 0){
                    type = 0;
                }
                break;
            case "flying":
                if (defendingMon[0].compareToIgnoreCase("fighting") == 0 || defendingMon[1].compareToIgnoreCase("fighting") == 0){
                    type *=2;
                }
                if (defendingMon[0].compareToIgnoreCase("bug") == 0 ||
                        defendingMon[1].compareToIgnoreCase("bug") == 0){
                    type*=2;
                }
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *=2;
                }

                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("electric") == 0 ||
                        defendingMon[1].compareToIgnoreCase("electric") == 0){
                    type *= .5;
                }
                break;
            case "poison":
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 || defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *=2;
                }
                if (defendingMon[0].compareToIgnoreCase("fairy") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fairy") == 0){
                    type *=2;
                }

                if (defendingMon[0].compareToIgnoreCase("poison") == 0 ||
                        defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("ground") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ground") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("ghost") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ghost") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type = 0;
                }
                break;
            case "ground":
                if (defendingMon[0].compareToIgnoreCase("poison") == 0 || defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *=2;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *=2;
                }
                if (defendingMon[0].compareToIgnoreCase("electric") == 0 ||
                        defendingMon[1].compareToIgnoreCase("electric") == 0){
                    type *=2;
                }

                if (defendingMon[0].compareToIgnoreCase("bug") == 0 ||
                        defendingMon[1].compareToIgnoreCase("bug") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type*=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("flying") == 0 ||
                        defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type = 0;
                }
                break;
            case "rock":
                if (defendingMon[0].compareToIgnoreCase("flying") == 0 || defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type*=2;
                }
                if (defendingMon[0].compareToIgnoreCase("bug") == 0 ||
                        defendingMon[1].compareToIgnoreCase("bug") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("ice") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ice") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("fighting") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fighting") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("ground") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ground") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                break;
            case "bug":
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("psychic") == 0 ||
                        defendingMon[1].compareToIgnoreCase("psychic") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("dark") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dark") == 0){
                    type *=2;
                }

                if (defendingMon[0].compareToIgnoreCase("fighting") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fighting") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("flying") == 0 ||
                        defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("poison") == 0 ||
                        defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("ghost") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ghost") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fairy") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fairy") == 0){
                    type *= .5;
                }
                break;
            case "ghost":
                if (defendingMon[0].compareToIgnoreCase("ghost") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ghost") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("psychic") == 0 ||
                        defendingMon[1].compareToIgnoreCase("psychic") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("dark") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dark") == 0){
                    type *=.5;
                }
                if (defendingMon[0].compareToIgnoreCase("normal") == 0 ||
                        defendingMon[1].compareToIgnoreCase("normal") == 0){
                    type = 0;
                }
                break;
            case "steel":
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("ice") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ice") == 0){
                    type *=2;
                }
                if (defendingMon[0].compareToIgnoreCase("fairy") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fairy") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("water") == 0 ||
                        defendingMon[1].compareToIgnoreCase("water") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("electric") == 0 ||
                        defendingMon[1].compareToIgnoreCase("electric") == 0){
                    type *= .5;
                }
                break;
            case "fire":
                if (weather == 2){
                    type *= 1.5;
                }
                else if (weather == 1){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("bug") == 0 ||
                        defendingMon[1].compareToIgnoreCase("bug") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("ice") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ice") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("water") == 0 ||
                        defendingMon[1].compareToIgnoreCase("water") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= .5;
                }
                break;
            case "water":
                if (weather == 1){
                    type *= 1.5;
                }
                else if (weather == 2){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("ground") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ground") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("water") == 0 ||
                        defendingMon[1].compareToIgnoreCase("water") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= .5;
                }
                break;
            case "grass":
                if (defendingMon[0].compareToIgnoreCase("ground") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ground") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("rock") == 0 ||
                        defendingMon[1].compareToIgnoreCase("rock") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("water") == 0 ||
                        defendingMon[1].compareToIgnoreCase("water") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("flying") == 0 ||
                        defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("poison") == 0 ||
                        defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("bug") == 0 ||
                        defendingMon[1].compareToIgnoreCase("bug") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= .5;
                }
                break;
            case "electric":
                if (defendingMon[0].compareToIgnoreCase("flying") == 0 ||
                        defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("water") == 0 ||
                        defendingMon[1].compareToIgnoreCase("water") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("electric") == 0 ||
                        defendingMon[1].compareToIgnoreCase("electric") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("ground") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ground") == 0){
                    type = 0;
                }
                break;
            case "psychic":
                if (defendingMon[0].compareToIgnoreCase("fighting") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fighing") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("poison") == 0 ||
                        defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("psychic") == 0 ||
                        defendingMon[1].compareToIgnoreCase("psychic") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("dark") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dark") == 0){
                    type = 0;
                }
                break;
            case "ice":
                if (defendingMon[0].compareToIgnoreCase("flying") == 0 ||
                        defendingMon[1].compareToIgnoreCase("flying") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("ground") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ground") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("grass") == 0 ||
                        defendingMon[1].compareToIgnoreCase("grass") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                    defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("water") == 0 ||
                        defendingMon[1].compareToIgnoreCase("water") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("ice") == 0 ||
                        defendingMon[1].compareToIgnoreCase("ice") == 0){
                    type *= .5;
                }
                break;
            case "dragon":
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fairy") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fairy") == 0){

                    type = 0;
                }

                break;
            case "dark":
                if (defendingMon[0].compareToIgnoreCase("ghost") == 0 || defendingMon[1].compareToIgnoreCase("ghost") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("psychic") == 0 ||
                        defendingMon[1].compareToIgnoreCase("psychic") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("fighting") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fighting") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("dark") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dark") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fairy") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fairy") == 0){
                    type *= .5;
                }
                break;
            case "fairy":
                if (defendingMon[0].compareToIgnoreCase("fighting") == 0 || defendingMon[1].compareToIgnoreCase("fighting") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("dragon") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dragon") == 0){
                    type *= 2;
                }
                if (defendingMon[0].compareToIgnoreCase("dark") == 0 ||
                        defendingMon[1].compareToIgnoreCase("dark") == 0){
                    type *= 2;
                }

                if (defendingMon[0].compareToIgnoreCase("poison") == 0 ||
                        defendingMon[1].compareToIgnoreCase("poison") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("steel") == 0 ||
                        defendingMon[1].compareToIgnoreCase("steel") == 0){
                    type *= .5;
                }
                if (defendingMon[0].compareToIgnoreCase("fire") == 0 ||
                        defendingMon[1].compareToIgnoreCase("fire") == 0){
                    type *= .5;
                }
                break;
        }



        if (moveType.compareTo(attackingMon[0]) == 0 || moveType.compareTo(attackingMon[1]) == 0){
            type *= 1.5;
        }
        Random rand = new Random();
        boolean crit = false;
        switch (atkMon.getCritTier()){
            case 1:
                if (rand.nextInt(1, 25) == 1){
                    crit = true;
                }
                break;
            case 2:
                if (rand.nextInt(1, 9) == 1){
                    crit = true;
                }
                break;
            case 3:
                if (rand.nextInt(1, 3) == 1){
                    crit = true;
                }
                break;
            default:
                crit = true;
                break;
        }
        if (crit){
            type *= 1.5;
        }
        type *= (rand.nextInt(85, 101) / 100.0);
        if (move.getPhysical() && atkMon.getStatus() == 1){
            type *= .5;
        }
        if ((move.getPhysical() && teams[(1-atkMonNum)].getReflect()) || (!move.getPhysical()) && teams[(1-atkMonNum)].getLightScreen()){
            type *= .5;
        }

        if (move.getPhysical()){
            type = type * ((42 * move.getDamage() * ((atkMon.getGameStats(1) * 1.0) / defMon.getGameStats(2) ) ) / 50.0);
        }
        else{
            type = type * ((42 * move.getDamage() * ((atkMon.getGameStats(3) * 1.0) / defMon.getGameStats(4) ) ) / 50.0);
        }

        if (rand.nextInt(0, 101) > move.getAccuracy()){
            System.out.println(atkMon.getName() + " missed!");
            type = 0;
        }
        else {
            switch(move.getName().toLowerCase()){
                case "hypnosis":
                    if (defMon.getStatus() == 0 || defMon.getStatus() == 5){
                        defMon.setStatus(4);
                        System.out.println(defMon.getName() + " fell asleep!");
                    }
                    break;
                case "toxic":
                    if (defMon.getStatus() == 0) {
                        System.out.println(defMon.getName() + " was poisoned!");
                        defMon.setStatus(2);
                        defMon.setPoisonTurns(0);
                    }
                    break;
                case "rock_tomb":
                    defMon.setGameStats(5, (int)(defMon.getGameStats(5)*.75));
                    System.out.println(defMon.getName() + " lost speed!");
                    break;
                case "meteor_mash":
                    if (rand.nextInt(0, 10) < (2)){
                        atkMon.setGameStats(1, (int) (atkMon.getGameStats(1) * 1.5));
                        System.out.println(atkMon.getName() + "'s attack rose!");
                    }
                    break;
            }
        }

        if (move.getName().toLowerCase().compareTo("dream_eater") == 0){
            atkMon.setGameStats(0, (int)(atkMon.getGameStats(0) + type/2));
            if (atkMon.getGameStats(0) > atkMon.getMaxStats(0)){
                atkMon.setGameStats(0, atkMon.getMaxStats(0));
            }
            move.setDamage(0);
        }
        if (move.getName().toLowerCase().compareTo("facade") == 0){
            move.setDamage(70);
        }

        return type;
    }
}
