package co.tjcelaya.panchi;

/**
 * Created by yser on 11/2/14.
 */
public class Scale {

    public static float fromZero (float actual, float min, float max) {

        float range = max - min;

        return actual * range/2 + (range/2) + min;
    }
}
