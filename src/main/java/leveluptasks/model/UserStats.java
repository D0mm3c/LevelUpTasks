package leveluptasks.model;

public class UserStats {
    int xp;
    int level;

    public UserStats() {
        xp = 0;
        level = 1;
    }

    public UserStats(int xp, int level) {
        this.xp = xp;
        this.level = level;
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

    private int calculateXpToNextLevel(int level) {
        return 50 * level * level;
    }

    public void addXp(int xp) {
        setXp(getXp() + xp);
        if(getXp() >= calculateXpToNextLevel(getLevel())) {
            setLevel(getLevel() + 1);
        }
    }
}
