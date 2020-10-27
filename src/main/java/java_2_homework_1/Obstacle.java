package java_2_homework_1;

import java.util.Random;

public enum  Obstacle {

    TRACK("Беговая дорожка", Obstacle.MAX_LENGTH_TRACK, 0),
    WALL("Стена", 0, Obstacle.MAX_HEIGHT_WALL);

    static final int MAX_LENGTH_TRACK = 500;
    static final int MAX_HEIGHT_WALL = 4;

    private final int length;
    private final int height;
    private final String title;

    Obstacle(String title, int maxLength, int maxHeight){
        Random randomHeight = new Random();

        this.height = randomHeight.nextInt((maxHeight)+1);
        this.length = randomHeight.nextInt((maxLength)+1);
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    void doIt(Team teammate){
        switch (this) {
            case WALL:
                teammate.jump(this);
                break;
            case TRACK:
                teammate.run(this);
                break;
        }
    }

    public String getTitle() {
        return title;
    }
}
