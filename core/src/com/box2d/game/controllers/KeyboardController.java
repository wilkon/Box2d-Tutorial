package com.box2d.game.controllers;

import com.badlogic.gdx.InputProcessor;

import static com.badlogic.gdx.Input.Keys.*;

public class KeyboardController implements InputProcessor {
    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;

    @Override
    public boolean keyDown(int keycode) {
        boolean keyProcessed = false;
        switch(keycode){
            case LEFT:
                left = true;
                keyProcessed = true;
                break;
            case RIGHT:
                right = true;
                keyProcessed = true;
                break;
            case UP:
                up = true;
                keyProcessed = true;
                break;
            case DOWN:
                down = true;
                keyProcessed = true;
        }
        return keyProcessed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        switch(keycode){
            case LEFT :
                left = false;
                keyProcessed = true;
                break;
            case RIGHT :
                right = false;
                keyProcessed = true;
                break;
            case UP :
                up = false;
                keyProcessed = true;
                break;
            case DOWN :
                down = false;
                keyProcessed = true;
        }
        return keyProcessed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
