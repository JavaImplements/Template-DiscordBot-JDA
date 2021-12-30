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

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Main {

    public static void main(String[] args) throws LoginException {

        // Create Client

        CommandClientBuilder client = new CommandClientBuilder().setActivity(Activity.playing("Bienvenue sur la Template de Implements !"));

        // Create Instance of Bot

        EventWaiter waiter = new EventWaiter();


        JDA jda = JDABuilder.createDefault("token")
                .addEventListeners(waiter, client.build())
                // Check Discord API for Intents
                .setEnabledIntents(EnumSet.allOf(GatewayIntent.class))
                .setAutoReconnect(true)
                .build();


        // Register all commands
        BotManagers.registerCommands(jda);
        // Register all Listeners
        BotManagers.loadListeners(jda);

        // Connexion MYSQL Server
        MySQL.connect(new DatabaseUser("localhost", 3306, "Implements-Database", "admin", "password"), true);


    }

}
