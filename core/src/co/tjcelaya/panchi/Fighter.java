package co.tjcelaya.panchi;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by yser on 11/2/14.
 */
public class Fighter extends Actor {

    Controller C;

    public Fighter(Controller c) {
        C = c;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }
}
