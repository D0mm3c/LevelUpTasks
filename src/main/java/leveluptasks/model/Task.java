package leveluptasks.model;

public class Task {
    private final String taskName;
    private String taskDescription;
    private int TaskXp;

    public Task(String pName, String pTaskDescription, int pTaskXp) {
        this.taskName = pName;
        this.taskDescription = pTaskDescription;
        this.TaskXp = pTaskXp;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String pTaskDescription) {
        if(pTaskDescription == null || pTaskDescription.isBlank()) throw new IllegalArgumentException("Description darf nicht leer sein!");
        this.taskDescription = pTaskDescription;
    }

    public int getTaskXp() {
        return TaskXp;
    }
    public void setTaskXp(int pTaskXp) {
        if(pTaskXp < 0) throw new IllegalArgumentException("XP darf nicht negativ sein!");
        this.TaskXp = pTaskXp;
    }

}
