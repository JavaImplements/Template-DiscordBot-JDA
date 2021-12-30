package fr.implement.discordjda.utils.bdd;


import fr.implement.discordjda.utils.bdd.tables.DataBan;

public enum DataEnum {

    BAN(new DataBan("user_ban")),
    ;


    private final Object object;

    DataEnum(Object object) {
        this.object = object;
    }

    public Object getDatabase() {
        return this.object;
    }
}
