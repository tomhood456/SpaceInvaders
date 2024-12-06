package spaceinvaders;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public int getScore() {
        return score;
    }
}


