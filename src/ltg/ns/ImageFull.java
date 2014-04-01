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
		sendInitRequest();
	}
	
	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("images_full_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}

	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			//drawImage();
			_p.background(255);
			_imageSet.display(_x, _y);
			if(checkTime(5000)){
				_imageSet.changeBurstImage();
			}
		}
	}

	public void update(ArrayList<String> urls) {
		_imageSet.setImages(urls);
	}
}
