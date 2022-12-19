package org.demo.core;

import java.util.Optional;

public class Command {
    private Move move;
    private Placement placement;
    private int id;

    public static Optional<Command> getInstance(
            Move move,
            int xOrId, //repurposing for different commands
            int y,
            Face face) {
        Command command;
        switch (move) {
            case ROBOT:
                command = new Command(move);
                command.id = xOrId;
                return Optional.of(command);
            case PLACE:
                Placement placement = new Placement(xOrId, y, face);
                command = new Command(move);
                command.setPlacement(placement);
                return Optional.of(command);
            case MOVE:
                return Optional.of(new Command(move));
            case LEFT:
                return Optional.of(new Command(move));
            case RIGHT:
                return Optional.of(new Command(move));
            case REPORT:
                return Optional.of(new Command(move));
            default:
                return Optional.empty();
        }
    }

    private Command(Move move) {
        this.move = move;
    }

    private void setPlacement(Placement placement) {
        if (move == Move.PLACE)
            this.placement = placement;
    }

    public Move getMove() {
        return move;
    }

    public Placement getPlacement() {
        return placement;
    }

    public int getId() {
        return id;
    }
}
