package co.tjcelaya.panchi;

/**
 * Created by yser on 11/2/14.
 */
public class DEBUGMSG extends Pair<String, Pair<Float, Float>> {
    public DEBUGMSG() {
        super();
    }

    public DEBUGMSG(String s) {
        super(s, null);
    }

    public DEBUGMSG(String s, float f1, float f2) {
        super(s, new Pair(f1, f2));
    }
}
