package leveluptasks.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import leveluptasks.model.UserStats;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserStatsManager {
    private static final Path USER_STATS_PATH = Paths.get("data", "userstats.json");
    private final Gson gson = new Gson();

    private UserStats userStats;

    public UserStatsManager() {
        load();
    }


    // Loads user stats from file or creates default if not found/corrupt
    public void load() {
        try {
            if (Files.notExists(USER_STATS_PATH)) {
                createDefaultStats();
                save();
                return;
            }

            try (Reader reader = Files.newBufferedReader(USER_STATS_PATH)) {
                userStats = gson.fromJson(reader, UserStats.class);
            }

            // If file is empty or invalid, create defaults
            if (userStats == null) {
                createDefaultStats();
                save();
            }
        } catch (IOException | JsonSyntaxException e) {
            System.err.println("Error loading user stats: " + e.getMessage());
            createDefaultStats();
            save();
        }
    }

    private void createDefaultStats() {
        userStats = new UserStats(); // constructor sets level=1, xp=0, etc.
    }

    // Saves current userStats to file
    public void save() {
        try {
            Files.createDirectories(USER_STATS_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(USER_STATS_PATH)) {
                gson.toJson(userStats, writer);
            }
        } catch (IOException e) {
            System.err.println("Error saving user stats: " + e.getMessage());
        }
    }

    // Returns the in-memory UserStats instance
    public UserStats getUserStats() {
        return userStats;
    }

    // Updates the in-memory stats and immediately saves
    public void updateUserStats(UserStats newStats) {
        this.userStats = newStats;
        save();
    }
}
