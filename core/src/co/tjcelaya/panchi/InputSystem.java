package co.tjcelaya.panchi;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.utils.Array;

import java.util.Stack;

/**
 * Created by yser on 11/2/14.
 */
public class InputSystem {

    public static final float CTR_MIN = -0.2f;
    public static final float CTR_MAX = 0.2f;

    public static final float NEG_MIN = -0.6f;
    public static final float POS_MIN = 0.6f;

    public enum DIR {
        CTR, E, SE, S, SW, W, NW, N, NE
    }

    private static final byte DIR_HISTORY_SIZE = 5;
    public static Array<Controller> controllers;
    public static PeekableRingBuffer<DIR> p1DirHist = new PeekableRingBuffer<DIR>(DIR_HISTORY_SIZE);

    public InputSystem() {

    }

    public void process() {
        float Lx, Ly;

        Lx = controllers.first().getAxis(0);
        Ly = controllers.first().getAxis(1);

        if (Lx < NEG_MIN) {

            // NE
            if (Ly < NEG_MIN)
                p1DirHist.addDifferent(DIR.NE);
            // E
            else if (CTR_MIN < Ly && Ly < CTR_MAX)
                p1DirHist.addDifferent(DIR.E);
            // SE
            else if (POS_MIN < Ly)
                p1DirHist.addDifferent(DIR.SE);

        } else if (CTR_MIN < Lx && Lx < CTR_MAX) {

            // N
            if (Ly < NEG_MIN)
                p1DirHist.addDifferent(DIR.N);
            // CTR
            else if (CTR_MIN < Ly && Ly < CTR_MAX)
                p1DirHist.addDifferent(DIR.CTR);
            // S
            else if (POS_MIN < Ly)
                p1DirHist.addDifferent(DIR.S);

        } else if (POS_MIN < Lx) {

            // NE
            if (Ly < NEG_MIN)
                p1DirHist.addDifferent(DIR.NW);
                // E
            else if (CTR_MIN < Ly && Ly < CTR_MAX)
                p1DirHist.addDifferent(DIR.W);
                // SE
            else if (POS_MIN < Ly)
                p1DirHist.addDifferent(DIR.SW);

        }
    }
}


/*       L   R
X = axis 0 & 3
Y = axis 1 & 4

       -1
    -1  0  1
        1      */