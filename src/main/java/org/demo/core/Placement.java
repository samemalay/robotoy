package org.demo.core;

import org.demo.App;

import java.util.Optional;

import static org.demo.core.Board.LOWER_BOUND;
import static org.demo.core.Board.UPPER_BOUND;

public class Placement {

    private int x;
    private int y;
    private Face face;

    public Placement(int x, int y, Face face) {
        this.x = x;
        this.y = y;
        this.face = face;
    }

    public void onLeft() {
        Face newface = Face.EAST; //initialised to remediate compilation issue
        switch (face){
            case EAST:
                newface = Face.NORTH;
                break;
            case NORTH:
                newface = Face.WEST;
                break;
            case WEST:
                newface = Face.SOUTH;
                break;
            case SOUTH:
                newface = Face.EAST;
                break;
            default:
                break;
        }
        face = newface;
    }

    public void onRight() {
        Face newface = Face.EAST; //initialised to remediate compilation issue
        switch (face){
            case EAST:
                newface = Face.SOUTH;
                break;
            case NORTH:
                newface = Face.EAST;
                break;
            case WEST:
                newface = Face.NORTH;
                break;
            case SOUTH:
                newface = Face.WEST;
                break;
            default:
                break;
        }
        face = newface;
    }

    public void onMove() {
        if (!App.isMulti()) {
            //single robot, only check bounds
            switch (face){
                case EAST:
                    if (x < UPPER_BOUND)
                        x = x + 1;
                    break;
                case NORTH:
                    if (y < UPPER_BOUND)
                        y = y + 1;
                    break;
                case WEST:
                    if (x > LOWER_BOUND)
                        x = x - 1;
                    break;
                case SOUTH:
                    if (y > LOWER_BOUND)
                        y = y - 1;
                    break;
                default:
                    break;
            }
        } else {
            //multi mode - check board occupation status
            switch (face){
                case EAST:
                    if (x < UPPER_BOUND && Board.occupy(x+1, y) && Board.release(x, y))
                        x = x + 1;
                    break;
                case NORTH:
                    if (y < UPPER_BOUND && Board.occupy(x, y + 1) && Board.release(x, y))
                        y = y + 1;
                    break;
                case WEST:
                    if (x > LOWER_BOUND && Board.occupy(x - 1, y)  && Board.release(x, y))
                        x = x - 1;
                    break;
                case SOUTH:
                    if (y > LOWER_BOUND && Board.occupy(x, y - 1)  && Board.release(x, y))
                        y = y - 1;
                    break;
                default:
                    break;
            }

        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Face getFace() {
        return face;
    }
}
