package ltg.ns;


import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import processing.core.PImage;
import de.looksgood.ani.*;
import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.ImageSet;
import ltg.ns.objects.Screen;

public class ImageFull extends Screen {
	protected PImage _img;
	protected ImageSet _imageSet;

	public ImageFull(){
		super();
	}

	public ImageFull(AmbientVizMain p) {
		super(p);
		_imageSet = new ImageSet(_p, 4);
		_imageSet.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}
	
	public ImageFull(AmbientVizMain p, String className) {
		super(p);
		if(className!=null) _className = className;
		_imageSet = new ImageSet(_p, 4);
		_imageSet.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}

	public void setActive(boolean active){
		if(active && _initActivation){
			super.setActive(active);
		}
		else{
			super.setActive(active);
		}
	}

	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(255);
			_imageSet.display(_x, _y);
		}
	}

	public void update(String eschool, String eclass, String egroup, String etime, ArrayList<String> urls) {
		_imageSet.update(eschool, eclass, egroup, etime, urls);
	}
}
