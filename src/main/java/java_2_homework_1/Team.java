package java_2_homework_1;

import java.util.Random;

public enum Team implements RunJumping {

    CAT("Кот", Team.MAX_HEIGHT_JUMP_CAT, Team.MAX_LENGTH_RUN_CAT),
    HUMAN("Человек", Team.MAX_HEIGHT_JUMP_HUMAN, Team.MAX_LENGTH_RUN_HUMAN),
    ROBOT("Робот", Team.MAX_HEIGHT_JUMP_ROBOT, Team.MAX_LENGTH_RUN_ROBOT);

    static final  int MAX_HEIGHT_JUMP_CAT = 4;
    static final int MAX_LENGTH_RUN_CAT = 300;

    static final  int MAX_HEIGHT_JUMP_HUMAN = 2;
    static final int MAX_LENGTH_RUN_HUMAN = 400;

    static final  int MAX_HEIGHT_JUMP_ROBOT = 5;
    static final int MAX_LENGTH_RUN_ROBOT = 800;


    private final int heightJump;
    private final int lengthRun;
    private final String title;
    private boolean loose = false;




    Team(String title, int maxHeightJump, int maxLengthRun){

        Random random = new Random();
        this.heightJump = random.nextInt((maxHeightJump)+1);
        this.lengthRun = random.nextInt((maxLengthRun)+1);
        this.title = title;
    }

    public void showLooseMessage(){
        System.out.printf("%s выбывает. %n", title);
        System.out.println("");
    }


    @Override
    public void jump(Obstacle wall) {

        System.out.printf("%s пытается перепрыгнуть %s высотой: %s %n", title, wall.getTitle(), wall.getHeight());

        if(wall.getHeight() <= this.heightJump){

            System.out.printf("%s перепрыгнул. %n",title);
        }else {
            this.loose = true;
            System.out.printf("%s не перепрыгнул. Смог допрыгнуть до %s. %n", this.title, this.heightJump);
        }
        System.out.println(" ");
    }

    @Override
    public void run(Obstacle track) {

        System.out.printf("%s пытается пробежать %s длиной: %s %n", title, track.getTitle(), track.getLength());
        if(track.getLength() <= this.lengthRun){

            System.out.printf("%s пробежал. %n", title);
        }else {
            this.loose = true;
            System.out.printf("%s не пробежал. Смог только %s. %n", title, this.lengthRun);
        }
        System.out.println(" ");
    }

    public int getLengthRun() {
        return lengthRun;
    }

    public int getHeightJump() {
        return heightJump;
    }

    public String getTitle() {
        return title;
    }

    public boolean isLoose() {
        return loose;
    }

    public void setLoose(boolean loose) {
        this.loose = loose;
    }
}
