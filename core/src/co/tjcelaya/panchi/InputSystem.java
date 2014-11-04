package co.tjcelaya.panchi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.utils.Array;
import com.google.common.collect.ImmutableMap;

/**
 * Created by yser on 11/2/14.
 */
public class InputSystem {

    public static final String TAG = "InputSystem";

    public static final float CTR_MIN = -0.2f;
    public static final float CTR_MAX = 0.2f;

    public static final float NEG_MIN = -0.6f;
    public static final float POS_MIN = 0.6f;

    public static final float DIR_COOLDOWN = 2f;

    public enum DIR {
        CTR, E, SE, S, SW, W, NW, N, NE
    }

    public enum MOVE {
        FORWARD, CHARGEFORWARD,
        BACKWARD, CHARGEBACKWARD,
        CROUCH, HOP, CHARGEHOP,
        QCIRC_FORWARD, QCIRC_BACK
    }

    private static final byte DIR_HISTORY_SIZE = 5;
    public static Array<Controller> controllers;

    public static PeekableRingBuffer<DIR> p1DirHist  = new PeekableRingBuffer<DIR>(DIR_HISTORY_SIZE);
    public static PeekableRingBuffer<DIR> p1MoveHist = new PeekableRingBuffer<DIR>(DIR_HISTORY_SIZE);
    public static float p1TimeSinceLastDir = 0f;

    public static final ImmutableMap<DIR[], MOVE> MOVE_DICT = new ImmutableMap.Builder<DIR[], MOVE>()
            .put(new DIR[]{ DIR.CTR, DIR.E, DIR.CTR }, MOVE.FORWARD)
            .put(new DIR[]{ DIR.CTR, DIR.W, DIR.CTR, DIR.E }, MOVE.CHARGEFORWARD)
            .build();

    public InputSystem() {

    }


    public void process(float delta) {

        if (p1TimeSinceLastDir < 5)
            p1TimeSinceLastDir += delta;

        processP1();

        Gdx.app.log(TAG, MOVE_DICT.toString());
    }

    private static void processP1() {
        if (controllers.size == 0)
            Gdx.app.exit();

        float Lx = controllers.first().getAxis(0);
        float Ly = controllers.first().getAxis(1);


        if (Lx < NEG_MIN) {

            // NE
            if (Ly < NEG_MIN) {
                addFresh(p1DirHist, DIR.NE);
            }
            // E
            else if (CTR_MIN < Ly && Ly < CTR_MAX) {
                addFresh(p1DirHist, DIR.E);
            }
            // SE
            else if (POS_MIN < Ly) {
                addFresh(p1DirHist, DIR.SE);
            }
        } else if (CTR_MIN < Lx && Lx < CTR_MAX) {

            // N
            if (Ly < NEG_MIN) {
                addFresh(p1DirHist, DIR.N);
            }
            // CTR
            else if (CTR_MIN < Ly && Ly < CTR_MAX) {
                addFresh(p1DirHist, DIR.CTR);
            }
            // S
            else if (POS_MIN < Ly) {
                addFresh(p1DirHist, DIR.S);
            }
        } else if (POS_MIN < Lx) {

            // NE
            if (Ly < NEG_MIN) {
                addFresh(p1DirHist, DIR.NW);
            }
            // E
            else if (CTR_MIN < Ly && Ly < CTR_MAX) {
                addFresh(p1DirHist, DIR.W);
            }
            // SE
            else if (POS_MIN < Ly) {
                addFresh(p1DirHist, DIR.SW);
            }
        }
    }

    private static void addFresh(PeekableRingBuffer<DIR> buffer, DIR d) {

        if (DIR_COOLDOWN < p1TimeSinceLastDir) {
            p1TimeSinceLastDir = 0;
            buffer.clear();
        }

        buffer.add(d);
    }
}


/*     -1
    -1  0  1
        1

    xbox L   R  \  ps   L   R
X = axis 0 & 3  \  axis 0 & 3
Y = axis 1 & 4  \  axis 1 & 6
*/