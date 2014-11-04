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

    public static final  String      TAG = "GAME";
    public static        int         W   = 640;
    public static        int         H   = 480;

    public static SpriteBatch batch;
    public static BitmapFont  F;
    public static DEBUG       D;
    public static Texture     DOT;
    public static InputSystem I;

    Stage              stage;
    OrthographicCamera cam;

    public static Array<Controller> controllers;
    boolean firstPass = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        DOT = new Texture("small.png");

        cam = new OrthographicCamera(W, H);
        cam.setToOrtho(false);

        stage = new Stage(new FitViewport(W, H, cam), batch);

        F = new BitmapFont(Gdx.files.internal("MONO.fnt"));
        D = new DEBUG(this);
        I = new InputSystem();
    }

    @Override
    public void render() {

        if (!firstPass) {
            firstPass = true;
            controllers = I.controllers = Controllers.getControllers();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        D.render();

        batch.end();
    }
}
