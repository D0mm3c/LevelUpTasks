package leveluptasks.model;

public enum Level {
    //Level, int id, int min xp
    LEVEL_1(1, 30),
    LEVEL_2(2, 100),
    LEVEL_3(3, 200);

    private int id;
    private int minXp;

    private Level(int id, int minXp) {
        this.id = id;
        this.minXp = minXp;
    }
}
