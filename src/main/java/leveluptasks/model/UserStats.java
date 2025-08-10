package leveluptasks.model;

public class UserStats {
    private int level;
    private int xp;
    private int xpToNextLevel;
    private int totalXp;

    private String name = "Dude";

    public UserStats() {
        setName(name);
        this.level = 1;
        this.xp = 0;
        this.xpToNextLevel = calculateXpToNextLevel(getLevel());
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Name darf nicht leer sein!");
        this.name = name;
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

    private void addTotalXp(int xp) {
        setTotalXp(getTotalXp() + xp);
    }
    public int getTotalXp() {
        return this.totalXp;
    }
    public void setTotalXp(int totalXp) {
        if(totalXp < 0) throw new IllegalArgumentException("XP darf nicht negativ sein!");
        this.totalXp = totalXp;
    }

    //Add XP, returns true if level up
    public boolean addXp(int xp) {
        setXp(getXp() + xp);
        addTotalXp(xp);

        if(getXp() >= calculateXpToNextLevel(getLevel())) {
            setXp(getXp() - calculateXpToNextLevel(getLevel()));
            setLevel(getLevel() + 1);
            setXpToNextLevel(calculateXpToNextLevel(getLevel()));
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Level: %d, XP: %d, XP to next level: %d, Total XP: %d", getLevel(), getXp(), getXpToNextLevel(), getTotalXp());
    }

}
