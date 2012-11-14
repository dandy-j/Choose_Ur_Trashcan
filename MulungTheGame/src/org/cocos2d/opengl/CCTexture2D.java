package org.cocos2d.opengl;

import static javax.microedition.khronos.opengles.GL10.GL_CLAMP_TO_EDGE;
import static javax.microedition.khronos.opengles.GL10.GL_FLOAT;
import static javax.microedition.khronos.opengles.GL10.GL_LINEAR;
import static javax.microedition.khronos.opengles.GL10.GL_NEAREST;
import static javax.microedition.khronos.opengles.GL10.GL_REPEAT;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_2D;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_COORD_ARRAY;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_MAG_FILTER;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_MIN_FILTER;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_WRAP_S;
import static javax.microedition.khronos.opengles.GL10.GL_TEXTURE_WRAP_T;
import static javax.microedition.khronos.opengles.GL10.GL_TRIANGLE_STRIP;
import static javax.microedition.khronos.opengles.GL10.GL_VERTEX_ARRAY;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11ExtensionPack;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.opengl.GLResourceHelper.Resource;
import org.cocos2d.types.CCTexParams;
import org.cocos2d.types.CGAffineTransform;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.opengl.GLUtils;
import android.util.Log;

/** CCTexture2D class.
 * This class allows to easily create OpenGL 2D textures from images, text or raw data.
 * The created CCTexture2D object will always have power-of-two dimensions. 
 * Depending on how you create the CCTexture2D object, the actual image area of the texture
 * might be smaller than the texture dimensions i.e. "contentSize" != (pixelsWide, pixelsHigh)
 * and (maxS, maxT) != (1.0, 1.0).
 * Be aware that the content of the generated textures will be upside-down!
*/
public class CCTexture2D implements Resource {
    // private static final String LOG_TAG = CCTexture2D.class.getSimpleName();
	
	public static final int kMaxTextureSize = 1024;

    /**
     * width in pixels
     */
    public int pixelsWide() {
        return mWidth;
    }

    /**
     * height in pixels
     */
    public int pixelsHigh() {
        return mHeight;
    }

    /**
     * width in pixels
     */
    public float getWidth() {
        return mContentSize.width;
    }

    /**
     * height in pixels
     */
    public float getHeight() {
        return mContentSize.height;
    }

    /** texture name */
    public int name() {
    	
//        if( _name == 0 && CCDirector.gl != null && Thread.currentThread().getName().startsWith("GLThread"))
    	
//    	if( _name == 0) {	
//        	this.loadTexture(CCDirector.gl);
//        }
        return _name;
    }

    /** texture max S */
    public float maxS() {
        return _maxS;
    }

    /** texture max T */
    public float maxT() {
        return _maxT;
    }

    private boolean premultipliedAlpha = false;
    /** whether or not the texture has their Alpha premultiplied */
    public boolean hasPremultipliedAlpha() {
        return premultipliedAlpha;
    }
    
    private FloatBuffer mVertices;
    private FloatBuffer mCoordinates;
//    private ShortBuffer mIndices;

    /** this mBitmap should be created when we call load(),
     * then we create texture, mBitmap is destroyed
     */
    private Bitmap mBitmap;

    /** texture name */
    private int _name = 0;

    /** content size */
    private CGSize mContentSize;

    /** width in pixels */
    private int mWidth;

    /** hight in pixels */
    private int mHeight;
    // private Bitmap.Config _format;

    /** texture max S */
    private float _maxS;

    /** texture max T */
    private float _maxT;

    private CCTexParams _texParams;
    
    /** this object is responsible for loading Bitmap for texture */
    private GLResourceHelper.GLResourceLoader mLoader;

    public final CGSize getContentSize() {
        return mContentSize;
    }

    public void releaseTexture (GL10 gl) {
        if (_name != 0) {
            gl.glDeleteTextures(1, new int[]{_name}, 0);
            _name = 0;
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
//    	if(mLoader != null) {
//    		GLResourceHelper.sharedHelper().removeLoader(mLoader);
//    	}
    	if (_name != 0) {
    		GLResourceHelper.sharedHelper().perform(new GLResourceHelper.GLResorceTask() {
    			
				@Override
				public void perform(GL10 gl) {
					IntBuffer intBuffer = IntBuffer.allocate(1);
					intBuffer.put(0, _name);
					gl.glDeleteTextures(1, intBuffer);
				}
				
			});
    	}

        super.finalize();
    }

    public CCTexture2D() {
        _texParams = new CCTexParams(GL_LINEAR, GL_LINEAR, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
    }
    
    public void checkName() {
    	if (mLoader != null && _name == 0)
    		mLoader.load(this);
    }
    
    public void setLoader(GLResourceHelper.GLResourceLoader loader) {
    	if(loader != null) {
    		loader.load(this);
    		
//        	if(mLoader != null) {
//        		GLResourceHelper.sharedHelper().removeLoader(mLoader);
//        	}
        	GLResourceHelper.sharedHelper().addLoader(this, loader, false);
    	}
    	mLoader = loader;
    }
    
    /**
      Extensions to make it easy to create a CCTexture2D object from an image file.
      Note that RGBA type textures will have their alpha premultiplied - use the blending mode (GL_ONE, GL_ONE_MINUS_SRC_ALPHA).
      */
    /** Initializes a texture from a UIImage object */
    public void initWithImage(Bitmap image) {

        CGSize imageSize = CGSize.make(image.getWidth(), image.getHeight());
        CGAffineTransform transform = CGAffineTransform.identity();

        int width = toPow2((int) imageSize.width);
        int height = toPow2((int) imageSize.height);

        while (width > kMaxTextureSize || height > kMaxTextureSize) {
            width /= 2;
            height /= 2;
            transform = transform.getTransformScale(0.5f, 0.5f);
            imageSize.width *= 0.5f;
            imageSize.height *= 0.5f;
        }

        if (imageSize.width != width || imageSize.height != height) {
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    image.hasAlpha() ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(image, 0, 0, null);
            image.recycle();
            image = bitmap;
        }

        init(image, imageSize, imageSize);
    }

    public void initWithImage(Bitmap image, CGSize imageSize) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap((int) imageSize.width, (int) imageSize.height, config);
        Canvas canvas = new Canvas(bitmap);
        
        canvas.drawBitmap(image, 0, 0, new Paint());
        image.recycle();

        init(bitmap, imageSize, imageSize);
    }
    
    public void initWithImage(Bitmap image, CGSize imageSize, CGSize contentSize) {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap((int) imageSize.width, (int) imageSize.height, config);
        Canvas canvas = new Canvas(bitmap);
        
        canvas.drawBitmap(image, 0, 0, new Paint());
        image.recycle();

        init(bitmap, imageSize, contentSize);
    }

    private void init(Bitmap image, CGSize imageSize, CGSize contentSize) {
        mBitmap = image;

        mWidth = image.getWidth();
        mHeight = image.getHeight();
        mContentSize = contentSize;
        // _format = image.getConfig();
        _maxS = mContentSize.width / (float) mWidth;
        _maxT = mContentSize.height / (float) mHeight;
        ByteBuffer vfb = ByteBuffer.allocateDirect(4 * 3 * 4);
        vfb.order(ByteOrder.nativeOrder());
        mVertices = vfb.asFloatBuffer();

        ByteBuffer tfb = ByteBuffer.allocateDirect(4 * 2 * 4);
        tfb.order(ByteOrder.nativeOrder());
        mCoordinates = tfb.asFloatBuffer();
        
        // GLUtils.texImage2D makes premultiplied alpha
		if(mBitmap.getConfig() == Bitmap.Config.ARGB_8888)
			premultipliedAlpha = true;
		
//        ByteBuffer isb = ByteBuffer.allocateDirect(6 * 2);
//        isb.order(ByteOrder.nativeOrder());
//        mIndices = isb.asShortBuffer();
//		CCTextureCache.sharedTextureCache().addTexture(this);
		
		// for call loadTexture when reinit
		if(_name != 0) {
			_name = 0;
			loadTexture(CCDirector.gl);
		}
		else {
    		GLResourceHelper.sharedHelper().perform(new GLResourceHelper.GLResorceTask() {
    			
				@Override
				public void perform(GL10 gl) {
					loadTexture(gl);
				}
			});			
		}
    }

    /**
      Extensions to make it easy to create a CCTexture2D object from a string of text.
      Note that the generated textures are of type A8 - use the blending mode (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA).
    */
    /** Initializes a texture from a string with font name and font size */
    public void initWithText(String text, String fontname, float fontSize) {
    	initWithText(text, calculateTextSize(text, fontname, fontSize),
                CCLabel.TextAlignment.CENTER, fontname, fontSize);
    }

    private static CGSize calculateTextSize(String text, String fontname, float fontSize) {
//        Typeface typeface = Typeface.create(fontname, Typeface.NORMAL);
        Typeface typeface;
    	try{
    		typeface = Typeface.createFromAsset(CCDirector.theApp.getAssets(), fontname);
    	} catch (Exception e) {
    		typeface = Typeface.create(fontname, Typeface.NORMAL);
		}

        Paint textPaint = new Paint();
        textPaint.setTypeface(typeface);
        textPaint.setTextSize(fontSize);
        textPaint.setAntiAlias(true);

        int ascent = (int) Math.ceil(-textPaint.ascent());  // Paint.ascent is negative, so negate it
        int descent = (int) Math.ceil(textPaint.descent());
        int measuredTextWidth = (int) Math.ceil(textPaint.measureText(text));

        return CGSize.make(measuredTextWidth, ascent + descent);
    }

    private static int toPow2(int v) {
        if ((v != 1) && (v & (v - 1)) != 0) {
            int i = 1;
            while (i < v)
                i *= 2;
            v = i;
        }
        if (v > CCTexture2D.kMaxTextureSize) {
        	v = CCTexture2D.kMaxTextureSize;
        }
        return v;
    }

    /** Initializes a texture from a string with dimensions, alignment, font name and font size */
    public void initWithText(String text, CGSize dimensions, CCLabel.TextAlignment alignment, String fontname, float fontSize) {
    	//Typeface.create(fontname, Typeface.NORMAL);
    	Typeface typeface;
    	try{
    		typeface = Typeface.createFromAsset(CCDirector.theApp.getAssets(), fontname);
    	} catch (Exception e) {
    		typeface = Typeface.create(fontname, Typeface.NORMAL);
		}

        Paint textPaint = new Paint();
        textPaint.setTypeface(typeface);
        textPaint.setTextSize(fontSize);
        textPaint.setAntiAlias(true);

        int ascent = (int) Math.ceil(-textPaint.ascent());  // Paint.ascent is negative, so negate it
        int descent = (int) Math.ceil(textPaint.descent());
        int measuredTextWidth = (int) Math.ceil(textPaint.measureText(text));


        int textWidth = measuredTextWidth;
        int textHeight = ascent + descent;

        int width = toPow2((int)dimensions.width);
        int height = toPow2((int) dimensions.height);

        Bitmap.Config config = Bitmap.Config.ALPHA_8;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.TRANSPARENT);

        int centerOffsetHeight = ((int) dimensions.height - textHeight) / 2;
        int centerOffsetWidth = ((int) dimensions.width - textWidth) / 2;

        switch (alignment) {
            case LEFT:
                centerOffsetWidth = 0;
                break;
            case CENTER:
                //centerOffsetWidth = (effectiveTextWidth - textWidth) / 2;
                break;
            case RIGHT:
                centerOffsetWidth = (int) dimensions.width - textWidth;
                break;
        }

        canvas.drawText(text,
                centerOffsetWidth,
                ascent + centerOffsetHeight,
                textPaint);

        init(bitmap, dimensions, dimensions);
    }

    public void loadTexture(GL10 gl) {
        if (_name == 0) {
            int[] textures = new int[1];
            gl.glGenTextures(1, textures, 0);

            _name = textures[0];

            applyTexParameters(gl);

            // this shouldn't be so never, but if so, needs to be found where
            // texture reloading is in progress 
        	if(mBitmap ==null)
        		return;
        	
            GLUtils.texImage2D(GL_TEXTURE_2D, 0, mBitmap, 0);
            mBitmap.recycle();
            mBitmap = null;
        }
    }


    /**
      Drawing extensions to make it easy to draw basic quads using a CCTexture2D object.
      These functions require GL_TEXTURE_2D and both GL_VERTEX_ARRAY and GL_TEXTURE_COORD_ARRAY
            client states to be enabled.
      */
    /** draws a texture at a given point */
    public void drawAtPoint(GL10 gl, CGPoint point) {
        gl.glEnable(GL_TEXTURE_2D);

        loadTexture(gl);

        float width = (float) mWidth * _maxS;
        float height = (float) mHeight * _maxT;

        float vertices[] = {point.x, point.y, 0.0f,
                width + point.x, point.y, 0.0f,
                point.x, height + point.y, 0.0f,
                width + point.x, height + point.y, 0.0f};

        mVertices.put(vertices);
        mVertices.position(0);

        float coordinates[] = {0.0f, _maxT,
                _maxS, _maxT,
                0.0f, 0.0f,
                _maxS, 0.0f};

        mCoordinates.put(coordinates);
        mCoordinates.position(0);

        gl.glEnableClientState(GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glBindTexture(GL_TEXTURE_2D, _name);

        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        gl.glVertexPointer(3, GL_FLOAT, 0, mVertices);
        gl.glTexCoordPointer(2, GL_FLOAT, 0, mCoordinates);
        gl.glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        // Clear the vertex and color arrays
        gl.glDisableClientState(GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL_TEXTURE_2D);
    }

    /** draws a texture inside a rect */
    public void drawInRect(GL10 gl, CGRect rect) {
        gl.glEnable(GL_TEXTURE_2D);

        loadTexture(gl);

        // float width = (float) mWidth * _maxS;
        // float height = (float) mHeight * _maxT;

	    float vertices[] = {
            rect.origin.x, rect.origin.y, /*0.0f,*/
			rect.origin.x + rect.size.width,	rect.origin.y,	/*0.0f,*/
			rect.origin.x, rect.origin.y + rect.size.height, /*0.0f,*/
			rect.origin.x + rect.size.width, rect.origin.y + rect.size.height, /*0.0f*/
        };

        mVertices.put(vertices);
        mVertices.position(0);

        float coordinates[] = {0.0f, _maxT,
                _maxS, _maxT,
                0.0f, 0.0f,
                _maxS, 0.0f};

        mCoordinates.put(coordinates);
        mCoordinates.position(0);

        gl.glEnableClientState(GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glBindTexture(GL_TEXTURE_2D, _name);

        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        gl.glVertexPointer(2, GL_FLOAT, 0, mVertices);
        gl.glTexCoordPointer(2, GL_FLOAT, 0, mCoordinates);
        gl.glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        // Clear the vertex and color arrays
        gl.glDisableClientState(GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL_TEXTURE_2D);
    }

    //
    // Use to apply MIN/MAG filter
    //

//    private static CCTexParams _gTexParams = new CCTexParams(GL_LINEAR, GL_LINEAR, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
//    private static CCTexParams _texParamsCopy;

    /** sets the min filter, mag filter, wrap s and wrap t texture parameters.
      If the texture size is NPOT (non power of 2),
             then in can only use GL_CLAMP_TO_EDGE in GL_TEXTURE_WRAP_{S,T}.
      @since v0.8
    */
    public void setTexParameters(CCTexParams texParams) {
    	_texParams.set(texParams);
    }
    
    public void setTexParameters(int min, int mag, int s, int t) {
    	_texParams.set(min, mag, s, t);
    	if(_name != 0) {
    		GLResourceHelper.sharedHelper().perform(new GLResourceHelper.GLResorceTask() {
    			
				@Override
				public void perform(GL10 gl) {
					applyTexParameters(gl);
				}
    		});
    	}
    }

//    public static CCTexParams texParameters() {
//        return gTexParams;
//    }

    private void applyTexParameters(GL10 gl) {
        gl.glBindTexture(GL_TEXTURE_2D, _name);
        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, _texParams.minFilter );
        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, _texParams.magFilter);
        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, _texParams.wrapS);
        gl.glTexParameterx(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, _texParams.wrapT);
    }

//    public static void restoreTexParameters() {
//        _gTexParams = _texParamsCopy;
//    }
//
//    public static void saveTexParameters() {
//        _texParamsCopy = _gTexParams.copy();
//    }


    /** sets alias texture parameters:
      - GL_TEXTURE_MIN_FILTER = GL_NEAREST
      - GL_TEXTURE_MAG_FILTER = GL_NEAREST

      @since v0.8
    */
    public void setAliasTexParameters() {
    	setTexParameters(GL_NEAREST, GL_NEAREST, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
    }


    /** sets antialias texture parameters:
      - GL_TEXTURE_MIN_FILTER = GL_LINEAR
      - GL_TEXTURE_MAG_FILTER = GL_LINEAR

      @since v0.8
      */
    public void setAntiAliasTexParameters() {
    	setTexParameters(GL_LINEAR, GL_LINEAR, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
    }

    /** Generates mipmap images for the texture.
      It only works if the texture size is POT (power of 2).
      @since v0.99.0
      */
    public void generateMipmap() {
        assert ( mWidth == toPow2((int)mWidth) && mHeight == toPow2((int)mHeight))
                : "Mimpap texture only works in POT textures";
        
		GLResourceHelper.sharedHelper().perform(new GLResourceHelper.GLResorceTask() {
			
			@Override
			public void perform(GL10 gl) {
				if(_name != 0) {
					gl.glBindTexture( GL_TEXTURE_2D, _name);
					((GL11ExtensionPack)gl).glGenerateMipmapOES(GL_TEXTURE_2D);
				}
			}
		});
    }
    
}

