package co.tjcelaya.panchi;

/**
 * Created by yser on 11/2/14.
 */
public class Pair<A,B> {
    public A first;
    public B second;

    public Pair() {
    }

    public Pair(A first) {
        this.first = first;
    }

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}
