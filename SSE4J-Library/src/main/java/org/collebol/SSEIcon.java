package org.collebol;

public enum SSEIcon {
    DEFAULT(0, "Blank display, no icon"),
    HEALTH(1, "Health"),
    ARMOR(2, "Armor"),
    AMMO(3, "Ammo"),
    MONEY(4, "Money"),
    FLASHBANG(5, "Flash/Flashbang/Explosion"),
    KILLS(6, "Kills"),
    HEADSHOT(7, "Headshot"),
    HELMET(8, "Helmet"),
    HUNGER(10, "Hunger"),
    AIR(11, "Air/Breath"),
    COMPASS(12, "Compass"),
    TOOL(13, "Tool/Pickaxe"),
    MANA(14, "Mana/Potion"),
    CLOCK(15, "Clock"),
    LIGHTNING(16, "Lightning"),
    ITEM(17, "Item/Backpack"),
    AT_SYMBOL(18, "@ Symbol"),
    MUTED(19, "Muted"),
    TALKING(20, "Talking"),
    CONNECT(21, "Connect"),
    DISCONNECT(22, "Disconnect"),
    MUSIC(23, "Music"),
    PLAY(24, "Play"),
    PAUSE(25, "Pause"),
    CPU(27, "CPU"),
    GPU(28, "GPU"),
    RAM(29, "RAM"),
    ASSISTS(30, "Assists"),
    CREEP_SCORE(31, "Creep Score"),
    DEAD(32, "Dead"),
    DRAGON(33, "Dragon"),
    ENEMIES(35, "Enemies"),
    GAME_START(36, "Game Start"),
    GOLD(37, "Gold"),
    HEALTH2(38, "Health (2)"),
    KILLS2(39, "Kills (2)"),
    MANA2(40, "Mana (2)"),
    TEAMMATES(41, "Teammates"),
    TIMER(42, "Timer"),
    TEMPERATURE(43, "Temperature");

    private final int id;
    private final String description;

    SSEIcon(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static SSEIcon fromId(int id) {
        for (SSEIcon icon : values()) {
            if (icon.id == id) return icon;
        }
        return DEFAULT;
    }
}
