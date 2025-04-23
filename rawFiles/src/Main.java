import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //Go figure, theres a lot of data in pokemon
        //Unfortunately, when i attempted to use an API, i ran into issues where there was too much data to import, and it would cut off early.
        //So all of this had to be entered by hand
        //Since there is... a LOT of data, consider this more a proof-of-concept rather than a fully finished pokemon sim
        //A solid portion of code is infastructure for things i didn't even have time to impliment.

        //Use the example team i managed to make data for:
        /*
        Pokemon Name: spiritomb
        moves:
        dark_pulse, silver_wind, ominous_wind, psychic
        Ability: pressure
        EVs:
        +252Spatk, +252Speed, +4Spdef
        Nature:
        +6, -2
         */
        /*
        Pokemon Name: roserade
        moves:
        Energy_Ball, Sludge_Bomb, Shadow_Ball, Extrasensory
        Ability: none
        EVs:
        +252Spatk, +252Speed, +4HP
        Nature:
        +6, -2
         */
        /*
        Pokemon Name: Togekiss
        moves:
        Air_Slash, Aura_Sphere, Ancient_Power, Psychic
        Ability: Serene_Grace
        EVs:
        +252Spatk, +252Speed, +4Hp
        Nature:
        +6, -2
         */
        /*
        Pokemon Name: lucario
        moves:
        swords_dance, close_combat, meteor_mash, extreme_speed
        Ability: inner_focus
        EVs:
        +252atk, +252Speed, +4hp
        Nature:
        +2, -4
         */
        /*
        Pokemon Name: milotic
        moves:
        scald, recover, ice_beam, toxic
        Ability: miracle_scale
        EVs:
        +252def, +4spdef, +252hp
        Nature:
        +5, -2
         */
        /*
        Pokemon Name: garchomp
        moves:
        swords_dance, earthquake, dragon_claw, giga_impact
        Ability: rough_skin
        EVs:
        +252atk, +252Speed, +4Hp
        Nature:
        +6, -4
         */



        ArrayList<Team> teams = new ArrayList<Team>();
        BattleManager battleManager = new BattleManager();

        boolean loopControl = true;
        Scanner scan = new Scanner(System.in);
        String input = "";
        TeamBuilder team = new TeamBuilder();




        teams = team.init();
        while (loopControl){
            System.out.println("{-< Pokemon Simulator >-}\n(1) Battle!\n(2) Team Builder\n(3) Exit");
            input = scan.next();
            switch (input.toLowerCase()){
                case "1":
                case "battle":
                    if( BattleManager.battleCheck(teams)){
                        battleManager.start(teams);
                    }
                    break;
                case "2":
                case "team":
                case "teams":
                case "team builder":
                    teams = team.start(teams);
                    break;
                case "3":
                case "exit":
                    team.saveTeams();
                    loopControl = false;
            }
        }

    }
}
