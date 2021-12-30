package fr.implement.discordjda.utils.bdd.mysql;


import fr.implement.discordjda.utils.ConsoleColor;
import fr.implement.discordjda.utils.bdd.DatabaseUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MySQL {

    private final String usedTab;
    public static Connection c;

    public MySQL(String usedTab) {
        this.usedTab = usedTab;
    }

    public static void connect(String host, int port, String database, String user, String password, boolean autoReconnect) {

        if (!isConnected()) {
            try {
                c = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=" + autoReconnect, user, password);

                System.out.println("[Mysql-LOG] " + ConsoleColor.GREEN.getColorString() + "La liaison entre le Bot et la base de donner a été effectuée avec succès" + ConsoleColor.RESET.getColorString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void connect(DatabaseUser user, boolean autoReconnect) {
        connect(user.getHost(), user.getPort(), user.getDatabase(), user.getUser(), user.getPassword(), autoReconnect);
    }

    private static boolean isConnected() {
        try {
            if (c == null || c.isClosed() || c.isValid(5)) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    



    public Object get(String objectNameEntry, String objectNameExit, String column) {
        try {
            PreparedStatement q = c.prepareStatement("SELECT " + column + " FROM " + usedTab + " WHERE " + objectNameEntry + " = ?");
            q.setString(1, objectNameExit);

            Object ob = null;
            ResultSet rs = q.executeQuery();

            while (rs.next()) ob = rs.getObject(column);

            q.close();

            return ob;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void set(String objectNameEntry, String objectNameExit, String column, Object object) {
        try {
            PreparedStatement q = c.prepareStatement("UPDATE " + usedTab + " SET " + column + " = ? WHERE " + objectNameEntry + " = ?");
            q.setObject(1, object);
            q.setString(2, objectNameExit);
            q.executeUpdate();
            q.close();
            System.out.println(getProjectName() + " Changement dans la colonne '" + column + "' => '" + object + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Object> takeAll(String column) {
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM " + usedTab);

            List<Object> list = new ArrayList<>();

            while (rs.next()) list.add(rs.getObject(column));

            rs.close();
            s.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void trim(String column) {
        try {
            PreparedStatement q = c.prepareStatement("UPDATE " + usedTab + " SET " + column + " = TRIM(CHAR(9) FROM TRIM(0))");
            q.executeUpdate();
            q.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getUsedTab() {
        return usedTab;
    }

    public abstract String getProjectName();

}
