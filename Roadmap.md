# 🚀 LevelUp Tasks – Development Roadmap
## 🧱 Phase 1: Project Setup
1. Create a JavaFX + Gradle project in IntelliJ ✅

2. Initialize Git repository locally ✅

3. Push project to GitHub ✅

4. Set up package structure: model, controller, util, view ✅

5. Create a README.md with project vision, features, and tech stack ✅

6. Add a .gitignore for Java, Gradle, and IntelliJ ✅

## 🧩 Phase 2: Core Models & XP Logic
1. Create Task class (name, description, XP reward) ✅ 

2. Create User, UserStats class (XP, level, xp needed for next level = Level^2 ) ✅

3. Define XP and level-up calculation system ✅

4. Decide on data format for saving (JSON via Gson) ✅
5. Create & Implement Utility Classes UserStatsManager ✅
6. Implement Task System + Utility Class TaskStorage ✅

## 🎛️ Phase 3: Basic UI & Functionality
1. Build base UI: task list (ListView), XP display (Label + ProgressBar), buttons

2. Display all current tasks with their XP rewards

3. Allow selecting and completing a task

4. On completion: increase XP, update progress bar, check for level-up

5. Show basic level-up notification (e.g., dialog or alert)

💾 Phase 4: Persistence
1. Load tasks and stats from a local JSON file on startup

2. Save task completion and XP to file on changes

3. Implement auto-save or save-on-exit logic

## 🎉 Phase 5: Gamification & Feedback
1. Add animations for task completion (e.g., fade, bounce)

2. Add sound effects for feedback (success, level-up)

3. Show XP gain visually (“+10 XP!” overlay)

4. Implement simple achievements (e.g., “First Task Done”, “Level 5 Reached”)

5. Allow unlocking themes or color schemes through progress

## 🎨 Phase 6: UI Polish & UX Improvements
1. Apply clean, modern JavaFX styling (with CSS)

2. Support dark/light modes or customizable themes

3. Add support for daily goals or streak tracking

4. Improve layout responsiveness (window resizing)

5. Optional: enable drag-and-drop task reordering

## 📦 Phase 7: Final Touches & Release
1. Record a demo video or animated GIFs of the app

2. Polish and finalize README.md with screenshots and setup instructions

3. Create a GitHub Release (.jar file)

4. Share on LinkedIn, GitHub profile, or personal portfolio