package fr.implement.discordjda.commands;

import fr.implement.discordjda.utils.SlashCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class CmdExemple extends SlashCommand {

    public CmdExemple() {
        super("exemple", "Description");
    }

    @Override
    public void execute(SlashCommandEvent e, Member member, User user, TextChannel textChannel) {
        textChannel.sendMessage("Command Exemple ! envoyez par : " + user.getName()).queue();
    }
}
