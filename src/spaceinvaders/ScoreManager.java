package spaceinvaders;

public final class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    public final void increaseScore(int amount) {
        score += amount;
    }

    public final int getScore() {
        return score;
    }

    public final void addScore(int points) {
        score += points;
    }
}



