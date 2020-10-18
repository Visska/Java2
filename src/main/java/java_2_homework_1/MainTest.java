package java_2_homework_1;

public class MainTest {
    public static void main(String[] args) {
        for(Team teammate: Team.values()){
            for(Obstacle obstacle: Obstacle.values()){
                obstacle.doIt(teammate);
                if(teammate.isLoose()) {
                    teammate.showLooseMessage();
                    break;
                }
            }
        }

    }
}
