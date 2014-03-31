package ltg.ns.objects;

import java.util.ArrayList;

import ltg.ns.AmbientVizMain;
import processing.core.PGraphics;
import processing.core.PImage;
import de.looksgood.ani.Ani;

public class ScoreBoard {
	ArrayList<PImage> _imageSet;
	PImage _sampleImage;
	int _width, _height, _size;
	String _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;
	boolean _loadingImage;

	public ScoreBoard(AmbientVizMain p, int size) {
		_p = p;
		_gTint = 255;
		_title = "GROUP:";
		_width = 50;
		_height = 50;
		_pg1 = _p.createGraphics(_width, _height);
		_size = size;
		_sampleImage = _p.loadImage("imageTiger.jpg");
		_loadingImage = false;		
		_imageSet = new ArrayList<PImage>();
		initImageSet();
	}

	private void initImageSet(){
		for(int i=0; i<_size; i++){
			_imageSet.add(_sampleImage);
		}
	}
	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		_pg1.setSize(_width, _height);
	}

	public void changeImage(){
		_loadingImage = true;
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}

	public void resetTint(){
		Ani.to(this, 2f, "_gTint", 255);
		PImage tmp = _imageSet.get(_size-1);
		_imageSet.remove(_size-1);
		_imageSet.add(0, tmp);
		_size = _imageSet.size();
		_loadingImage = false;
	}

	public void display(float x, float y) {		
		_pg1.beginDraw();
		_pg1.background(_p.bgColor);
		_pg1.imageMode(_p.CENTER);
		_pg1.image(_imageSet.get(_size-1), _pg1.width/2, _pg1.height/2, _width, _height);
		_pg1.endDraw();

		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pg1, x, y);
	}
}
