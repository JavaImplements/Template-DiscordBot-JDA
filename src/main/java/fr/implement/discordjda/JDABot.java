package fr.implement.discordjda;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import fr.implement.discordjda.managers.BotManagers;
import fr.implement.discordjda.utils.bdd.DatabaseUser;
import fr.implement.discordjda.utils.bdd.mysql.MySQL;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class JDABot {

    /*
        @author: Implemets

        En cours de développement....
     */

    private static JDA jda;
    private static Activity activity;
    private static String nameBot;

    public static void login(@NotNull String token, Activity activity, String nameLog) throws LoginException {
        setNameBot(nameLog);
        setActivity(activity);

        // Create => Client
        CommandClientBuilder client = new CommandClientBuilder();
        client.setPrefix("&");
        client.setOwnerId("00000");
        client.setActivity(getActivity());

        // Create => instance bot

        EventWaiter waiter = new EventWaiter();

        jda = JDABuilder.createDefault(token)
                .addEventListeners(waiter, client.build())
                .setEnabledIntents(EnumSet.allOf(GatewayIntent.class))
                .setAutoReconnect(true)
                .build();


        new BotManagers(jda).init();

        // Connexion Mysql

        MySQL.connect(new DatabaseUser("localhost", 3306, "database", "user", "password"), true);

    }

    public static JDA getJDA() {
        return jda;
    }

    public static Activity getActivity() {
        return activity;
    }

    public static void setActivity(Activity activity) {
        JDABot.activity = activity;
    }

    public static String getNameBot() {
        return nameBot;
    }

    public static void setNameBot(String nameBot) {
        JDABot.nameBot = nameBot;
    }
}
