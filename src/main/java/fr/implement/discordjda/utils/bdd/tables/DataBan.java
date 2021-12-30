package fr.implement.discordjda.utils.bdd.tables;


import fr.implement.discordjda.utils.ConsoleColor;
import fr.implement.discordjda.utils.bdd.mysql.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBan extends MySQL {

    public DataBan(String usedTab) { super(usedTab); }



    public void banUser(String user_id, String username, String reason) {
        if (!isBan(user_id)) {
            try {
                PreparedStatement q = c.prepareStatement("INSERT INTO " + getUsedTab() + "(user_id, username, reason) VALUES (?,?,?)");
                q.setString(1, user_id);
                q.setString(2, username);
                q.setString(3, reason);
                q.execute();
                q.close();

                System.out.println(ConsoleColor.RED.getColorString() + getProjectName() + " " + username + " à été ban du serveur." + ConsoleColor.RESET.getColorString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isBan(String user_id) {
        try {
            PreparedStatement q = c.prepareStatement("SELECT user_id from " + getUsedTab()+ " WHERE user_id = ?");
            q.setString(1, user_id);
            ResultSet result = q.executeQuery();
            boolean hasAccount = result.next();
            q.close();

            return hasAccount;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getProjectName() {
        return "[Implements-Ban]";
    }
}
