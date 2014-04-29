package ltg.ns.objects;



import ltg.ns.AmbientVizMain;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

public class ChannelIcon
{
	AmbientVizMain _p;
	String _imgURL;
	float _x, _y, _width, _height;
	PImage _img;
	int _channelNumber;
	PShape _shape;
	
	public ChannelIcon(AmbientVizMain p, float x, float y, String shapeURL){
		_p = p;
		_x = x;
		_y = y;
	}
	
	public ChannelIcon(AmbientVizMain p, float x, float y, float width, float height, int channelNumber, String shapeURL){
		_p = p;
		_x = x;
		_y = y;
		_width = width;
		_height = height;
		_channelNumber = channelNumber;
		setShape(shapeURL);
	}
	
	public void setShape(String shapeURL){
		_shape = _p.loadShape(shapeURL);
	}
	
	
	public void loc(PVector p){
		_x = p.x;
		_y = p.y;
	}
	
	public float width(){
		return _width;
	}
	
	public float height(){
		return _height;
	}
	
	public void display(){
		_p.rectMode(_p.CENTER);
		_p.imageMode(_p.CENTER);
		_p.shapeMode(_p.CENTER);
		_p.noStroke();
		_p.fill(0);
		if((_x-_width/2 < _p.mouseX) && (_p.mouseX < _x+_width/2) && ((_y-_height/2) < _p.mouseY) && (_p.mouseY < _y+_height/2)){
			_p.stroke(255, 100, 0);
			_p.strokeWeight(4);
			_p.noFill();
			_p.rect(_x, _y, _width, _height, 10);
		}
		if(_img == null && _shape == null){
			_p.rect(_x, _y, _width, _height, 5);
		}
		else if(_img != null && _shape == null){
			if (_img.width == 0) {
			    // Image is not yet loaded
			  } else if (_img.width == -1) {
			    // This means an error occurred during image loading
			  } else {
			    // Image is ready to go, draw it
			    _p.image(_img, _x, _y);
			  }
		}
		else if(_shape != null && _img == null){
			_p.shape(_shape, _x, _y, _width, _height);
		}
	}
	
	public int mouseClicked(float x, float y){
		if((_x-_width/2 < x) && (x < _x+_width/2) && ((_y-_height/2) < y) && (y < _y+_height/2)){
			return _channelNumber;
		}
		return -1;
	}
}
