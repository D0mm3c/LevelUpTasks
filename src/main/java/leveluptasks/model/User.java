package leveluptasks.model;

public class User {
    private String name;
    private UserStats stats;

    private int level;
    private int xp;
    private int xpToNextLevel;

    public User(String name) {
        setName(name);
        stats = new UserStats();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Name darf nicht leer sein!");
        this.name = name;
    }
    public UserStats getStats() {
        return stats;
    }

    public int getXp() {
        return this.xp;
    }
    public void setXp(int xp) {
        if(xp < 0) throw new IllegalArgumentException("XP darf nicht negativ sein!");
        this.xp = xp;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        if(level < 1) throw new IllegalArgumentException("Level darf nicht kleiner als 1 sein!");
        this.level = level;
    }

    public void setXpToNextLevel(int xpToNextLevel) {
        if(xp < 0) throw new IllegalArgumentException("XP darf nicht negativ sein!");
        this.xpToNextLevel = xpToNextLevel;
    }
    public int getXpToNextLevel() {
        return this.xpToNextLevel;
    }

    private int calculateXpToNextLevel(int level) {
        return 50 * level * level;

    }

    public void addXp(int xp) {
        setXp(getXp() + xp);
        if(getXp() >= calculateXpToNextLevel(getLevel())) {
            setLevel(getLevel() + 1);
            setXp(0);
            setXpToNextLevel(calculateXpToNextLevel(getLevel()));
        }
    }

}
