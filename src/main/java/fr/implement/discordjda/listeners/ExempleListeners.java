package fr.implement.discordjda.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ExempleListeners extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent e){
        if(!e.getMember().getId().equalsIgnoreCase(e.getJDA().getSelfUser().getId())){
            e.getChannel().sendMessage("Pour tout les commands avec un '/' il faut un temps pour qu'elle s'ajoute à votre bot après la premiere connexion. 1 à 3 heures à verifier sur la doc de discord").queue();
        }
    }

}
