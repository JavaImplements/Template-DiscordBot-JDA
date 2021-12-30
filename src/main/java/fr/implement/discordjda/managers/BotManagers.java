package fr.implement.discordjda.managers;

import fr.implement.discordjda.commands.CmdExemple;
import fr.implement.discordjda.listeners.ExempleListeners;
import fr.implement.discordjda.utils.SlashHandler;
import jdk.nashorn.internal.scripts.JD;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;

public class BotManagers {

    public static void registerCommands(JDA jda) {
        SlashHandler slashHandler = new SlashHandler(jda);

        // list ->  commands
        slashHandler.registerCommand(new CmdExemple());

        // register all commands
        slashHandler.registerCommands(jda);
    }

    public static void loadListeners(JDA jda){
        Arrays.asList(
                new ExempleListeners()
        ).forEach(jda::addEventListener);
    }


}
