package co.tjcelaya.panchi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;
import java.util.Stack;

public class GAME extends ApplicationAdapter {

    public static final String TAG = "GAME";
    public static       int    W   = 640;
    public static       int    H   = 480;

    public static SpriteBatch batch;
    public static BitmapFont  F;
    public static DEBUG       D;
    Texture            img;
    Stage              stage;
    OrthographicCamera cam;

    Array<Controller> controllers;
    boolean firstPass = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("small.png");

        cam = new OrthographicCamera(W, H);
        cam.setToOrtho(false);

        stage = new Stage(new FitViewport(W, H, cam), batch);

        F = new BitmapFont(Gdx.files.internal("MONO.fnt"));
        D = new DEBUG(this);
    }

    @Override
    public void render() {

        if (!firstPass) {
            firstPass = true;
            controllers = Controllers.getControllers();
        }


        D.write(String.format("TL   % .5f", controllers.first().getAxis(2)));
        D.write(String.format("TR   % .5f", controllers.first().getAxis(5)));

        D.write(String.format("R UD % .5f", controllers.first().getAxis(4)));
        D.write(String.format("R LR % .5f", controllers.first().getAxis(3)));

        D.write(String.format("L UD % .5f", controllers.first().getAxis(1)));
        D.write(String.format("L LR % .5f", controllers.first().getAxis(0)));


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img,
                   Scale.fromZero(controllers.first().getAxis(0), 0, W/2),
                   Scale.fromZero(controllers.first().getAxis(1), H/2, 0),
                   3,
                   3);
        batch.draw(img,
                   Scale.fromZero(controllers.first().getAxis(3), W/2, W),
                   Scale.fromZero(controllers.first().getAxis(4), H/2, 0),
                   3,
                   3);
        D.render();
        batch.end();
    }
}
