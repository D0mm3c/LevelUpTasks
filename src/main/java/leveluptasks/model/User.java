package leveluptasks.model;

public class User {
    private String name;
    private UserStats userStats;

    public User(String name, UserStats userStats) {
        setName(name);
        this.userStats = userStats;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name == null || name.isBlank()) throw new IllegalArgumentException("Name darf nicht leer sein!");
        this.name = name;
    }

    public UserStats getUserStats(){
        return userStats;
    }


}
