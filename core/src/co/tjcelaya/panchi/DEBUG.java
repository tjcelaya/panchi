package co.tjcelaya.panchi;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by yser on 11/2/14.
 */
public class DEBUG {

    public static final String TAG = "DEBUG";
    GAME G;
    SpriteBatch SB;
    private static Stack<DEBUGMSG> messages = new Stack<DEBUGMSG>();
    private static int linesWritten = 0;

    public DEBUG(GAME g) {
        G = g;
        SB = G.batch;
    }

    public void render() {
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
    }


    public static void write(float msg) {
        messages.push(new DEBUGMSG(String.format("%+.5f", msg)));
    }

    public static void write(boolean msg) {
        messages.push(new DEBUGMSG(String.format("%b", msg)));
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