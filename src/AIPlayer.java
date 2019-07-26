public class AIPlayer extends Player {

    private Intelligence intelligence;

    public AIPlayer (GameEngine engine, int id) {
        super(engine, id);
        this.intelligence = new Intelligence();
    }
}
