package fr.implement.discordjda.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static EmbedBuilder getEmbedNoPerm(JDA jda) {
        return new EmbedBuilder().setFooter("Developped by Implements", jda.getSelfUser().getAvatarUrl()).setTimestamp(new Date().toInstant()).setDescription("Vous n'avez pas la permissions.").setColor(Color.RED);
    }

    public static void Log(String str){
        System.out.println(str);
    }

    public static String date2str(Date date){
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return f.format(date);
    }

    public static Date str2date(String date) {
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = null;
        try {
            d = f.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
