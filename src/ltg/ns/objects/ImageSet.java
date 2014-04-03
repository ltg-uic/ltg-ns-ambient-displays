package ltg.ns.objects;

import java.util.ArrayList;

import processing.core.PGraphics;
import processing.core.PImage;
import wordcram.Colorers;
import wordcram.WordCram;
import de.looksgood.ani.Ani;
import ltg.ns.AmbientVizMain;

public class ImageSet {
	ArrayList<PImage> _imageSet;
	ArrayList<PImage> _newImageSet;

	PImage _sampleImage;
	int _width, _height, _size, _currentIndex;
	String _title;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1, _pgNext;
	boolean _loadingBurst;

	public ImageSet(AmbientVizMain p, int size) {
		_p = p;
		_gTint = 255;
		_title = "GROUP:";
		_width = 50;
		_height = 50;
		_currentIndex = 0;
		_size = size;
		_sampleImage = _p.loadImage("imageTiger.jpg");
		_loadingBurst = false;
		_imageSet = new ArrayList<PImage>();
		_newImageSet = new ArrayList<PImage>();
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

	}

	public void changeBurst(){
		_loadingBurst = true;
		Ani.to(this, 2f, "_gTint", 0, Ani.LINEAR, "onEnd:resetTint");
	}

	public void resetTint(){
		_imageSet.clear();
		_imageSet = new ArrayList<PImage>(_newImageSet);
		_newImageSet.clear();
		_size = _imageSet.size();
		_currentIndex = 0;
		Ani.to(this, 2f, "_gTint", 255, Ani.LINEAR, "onEnd:setLoadingBurstFalse");
	}
	public void setLoadingBurstFalse(){
		_loadingBurst = false;
	}

	public void changeBurstImage(){
		if(!_loadingBurst){
			if(!isNewSetLoaded()){
				Ani.to(this, 0.5f, "_gTint", 200, Ani.LINEAR, "onEnd:resetTintBurst");
			}
			else{
				changeBurst();
			}
		}
	}


	public void resetTintBurst(){
		if(_currentIndex+1 >= _size)_currentIndex = 0;
		else _currentIndex++;
		Ani.to(this, 0.5f, "_gTint", 255);
	}

	public void setImages(ArrayList<String> urls) {
		_newImageSet.clear();
		for(int i = 0; i < urls.size(); i++){
			PImage img = _p.requestImage(urls.get(i));
			_newImageSet.add(img);
		}
	}

	public boolean isNewSetLoaded(){

		boolean is = true;

		for(int i = 0; i < _newImageSet.size(); i++){
			PImage img = _newImageSet.get(i);
			if(img.width == 0 || img.width == -1){
				is = false;
				break;
			}
		}	

		if(_newImageSet.size() ==0) is = false;
		return is;

	}

	public void display(float x, float y) {		

		PImage _current = _imageSet.get(getCurrentIndex());
		PImage _next = _imageSet.get(getNextIndex());

		
		_pg1 = _p.createGraphics(_width, _height);
		_pgNext = _p.createGraphics(_width, _height);
		
		_pgNext.beginDraw();
		_pgNext.background(_p.bgColor);
		_pgNext.imageMode(_p.CENTER);
		_pgNext.image(_next, _pg1.width/2, _pg1.height/2, _width, _height);
		_pgNext.endDraw();

		_pg1.beginDraw();
		_pg1.imageMode(_p.CENTER);
		_pg1.image(_current, _pg1.width/2, _pg1.height/2, _width, _height);
		_pg1.endDraw();

		_p.tint(255, _gTint);
		_p.imageMode(_p.CENTER);
		_p.image(_pgNext, x, y);
		_p.image(_pg1, x, y);
		
		_pg1.dispose();
		_pgNext.dispose();
	}



	private int getNextIndex(){
		if(_currentIndex+1 >= _size) return 0;
		else return _currentIndex+1;
	}

	private int getCurrentIndex(){
		return _currentIndex;
	}
}
