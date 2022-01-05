package fr.implement.discordjda;

import fr.implement.discordjda.utils.ConsoleColor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Main extends JDABot {

    /**
     * @author Implements
     * @updated 05/01/22
     * <p>
     * Cette template est en cours de développement.
     */


    public static void main(String[] args) throws LoginException {
        login("token", Activity.playing("Template : Développed by Implements"), ConsoleColor.BLUE.getColorString() + "Implements-Template - " + ConsoleColor.RESET.getColorString());

        runTaskBot(getJDA());
    }


    // System plus performants / completes en cours de développement.

    public static void runTaskBot(JDA jda) {
        Scanner s = new Scanner(System.in);
        String cmd = s.nextLine();
        if (cmd.equals("stop")) {
            jda.shutdown();
            System.exit(0);
        }
    }
}
