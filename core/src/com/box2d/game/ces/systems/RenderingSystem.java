package com.box2d.game.ces.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.box2d.game.ces.components.TextureComponent;
import com.box2d.game.ces.components.TransformComponent;

import java.util.Comparator;

public class RenderingSystem extends SortedIteratingSystem {

    static final float PPM = 32.0f; // pixels per meter of box2d objects contain

    //width/height calculated against our PPM
    static final float FRUSTUM_WIDTH = Gdx.graphics.getWidth()/PPM;
    static final float FRUSTUM_HEIGHT = Gdx.graphics.getHeight()/PPM;

    //ratio for converting pixels to meters
    public static final float PIXELS_TO_METERS = 1.0f / PPM;

    //screen width in meters
    private static Vector2 meterDimensions = new Vector2();
    private static Vector2 pixelDimensions = new Vector2();

    public static Vector2 getScreenSizeInMeters(){
        meterDimensions.set(
                Gdx.graphics.getWidth() * PIXELS_TO_METERS,
                Gdx.graphics.getHeight() * PIXELS_TO_METERS);
        return meterDimensions;
    }

    public static Vector2 getScreenSizeInPixels(){
        pixelDimensions.set(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return pixelDimensions;
    }

    public static float pixelsToMeters(float pixelValue){
        return pixelValue * PIXELS_TO_METERS;
    }

    // reference to our sprite batch
    private SpriteBatch batch;

    // array used to allow sorting of images allowing us to draw images on top of each other
    private Array<Entity> renderQueue;

    // comparator that allows us to sort images base don the Z position of the transformComponent
    private Comparator<Entity> comparator;

    // reference to our camera
    private OrthographicCamera camera;

    // component mappers to retrieve components from entities
    private ComponentMapper<TextureComponent> textureMapper;
    private ComponentMapper<TransformComponent> transformMapper;

    @SuppressWarnings("unchecked")
    public RenderingSystem(SpriteBatch batch) {

        //get all entities with a transformComponent and TextureComponent
        super(Family.all(TransformComponent.class, TextureComponent.class).get(), new ZComparator());

        //craeetes out componentMappers
        textureMapper = ComponentMapper.getFor(TextureComponent.class);
        transformMapper = ComponentMapper.getFor(TransformComponent.class);

        //create the array for sorting entities
        renderQueue = new Array<Entity>();

        //set our batch to the one supploed in the constructor
        this.batch = batch;

        camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
        camera.position.set(FRUSTUM_WIDTH / 2f, FRUSTUM_HEIGHT / 2f, 0);
    }

    @Override
    public void update(float deltaTime){
        super.update(deltaTime);

        //sort the render queue based on Z index (Depth)
        renderQueue.sort(comparator);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.enableBlending();
        batch.begin();

        // looping through each entity on the render queue
        for(Entity entity : renderQueue){
            TextureComponent textureComp = textureMapper.get(entity);
            TransformComponent transformComp = transformMapper.get(entity);

            //nothing exists within this component, so there's nothing to load
            if(textureComp.region == null || transformComp.isHidden){
                continue;
            }

            float width = textureComp.region.getRegionWidth();
            float height = textureComp.region.getRegionHeight();

            //grbbing the center of our entity
            float originX = width/2f;
            float originY = height/2f;

            batch.draw(textureComp.region,
                    transformComp.position.x - originX, transformComp.position.y - originY,
                    originX, originY,
                    width, height,
                    pixelsToMeters(transformComp.scale.x), pixelsToMeters(transformComp.scale.y),
                    transformComp.rotation);
        }

        batch.end();
        renderQueue.clear();
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }

    public OrthographicCamera getCamera(){
        return camera;
    }
}
