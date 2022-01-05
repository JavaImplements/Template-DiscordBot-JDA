package fr.implement.discordjda.managers;

import fr.implement.discordjda.commands.CmdExemple;
import fr.implement.discordjda.listeners.ExempleListeners;
import fr.implement.discordjda.utils.SlashHandler;
import net.dv8tion.jda.api.JDA;

import java.util.Arrays;

public class BotManagers {

    private JDA jda;

    public BotManagers(JDA jda) {
        this.jda = jda;
    }

    public JDA getJda() {
        return jda;
    }

    public void registerCommands() {
        SlashHandler slashHandler = new SlashHandler(jda);
        slashHandler.registerCommand(new CmdExemple());
        slashHandler.registerCommands(jda);

    }

    public void registerListeners() {
        Arrays.asList(
                new ExempleListeners()
        ).forEach(jda::addEventListener);
    }

    public void init() {
        this.registerCommands();
        this.registerListeners();
    }

}
