package leveluptasks.model;

public class Task {
    private String name;
    private String reward;

    public Task(String name, String reward) {
        this.name = name;
        this.reward = reward;
    }

    public String getName() {
        return name;
    }

    public String getReward() {
        return reward;
    }

    @Override
    public String toString() {
        return name + " (" + reward + ")";
    }
}
