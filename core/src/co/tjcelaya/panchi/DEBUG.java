package co.tjcelaya.panchi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.common.collect.EvictingQueue;

import java.util.Stack;

/**
 * Created by yser on 11/2/14.
 */
public class DEBUG {

    public static final String TAG = "DEBUG";
    GAME        G;
    SpriteBatch SB;
    private static Stack<DEBUGMSG> messages     = new Stack<DEBUGMSG>();
    private static int             linesWritten = 0;

    PeekableRingBuffer<String> c = new PeekableRingBuffer<String>(3);

    public DEBUG(GAME g) {
        G = g;
        SB = G.batch;

    }

    public void render(float delta) {

        G.I.process(delta);

        write(G.I.p1DirHist.ring.toString());

        DEBUGMSG d;

        while (!messages.empty()) {
            d = messages.pop();

            if (d.second == null) {
                directWriteText(d.first);
            } else {
                directWriteText(d.first, d.second.first, d.second.second);
            }

        }

        linesWritten = 0;

        write(String.format("TL   % .5f", G.controllers.first().getAxis(2)));
        write(String.format("TR   % .5f", G.controllers.first().getAxis(5)));
        write(String.format("R UD % .5f", G.controllers.first().getAxis(4)));
        write(String.format("R LR % .5f", G.controllers.first().getAxis(3)));
        write(String.format("L UD % .5f", G.controllers.first().getAxis(1)));
        write(String.format("L LR % .5f", G.controllers.first().getAxis(0)));

        SB.draw(G.DOT,
                Scale.fromZero(G.controllers.first().getAxis(0), 0, G.W / 2),
                Scale.fromZero(G.controllers.first().getAxis(1), G.H / 2, 0),
                3,
                3);
        SB.draw(G.DOT,
                   Scale.fromZero(G.controllers.first().getAxis(3), G.W/2, G.W),
                   Scale.fromZero(G.controllers.first().getAxis(4), G.H/2, 0),
                   3,
                   3);
    }


    public static void write(float msg) {
        messages.push(new DEBUGMSG(String.format("%+.5f", msg)));
    }

    public static void write(String msg) {
        messages.push(new DEBUGMSG(msg));
    }

    public static void write(String msg, float x, float y) {
        messages.push(new DEBUGMSG(msg, x, y));
    }

    private void directWriteText(String msg) {
        G.F.draw(SB, msg, 10, G.H - 20 * (++linesWritten));
    }

    private void directWriteText(String msg, float x, float y) {
        G.F.draw(SB, msg, x, y);
    }
}