package ltg.ns;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.NoteCount;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;

public class NotesNumberFull extends Screen {

	protected NoteCount _noteCount;

	public NotesNumberFull(AmbientVizMain p, String className, String bannerURL) {
		super(p);
		_className = className;
		_name = "notes_number_full_class_"+className;
		setShapeBanner(bannerURL);
		_noteCount = new NoteCount(_p);
		_noteCount.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}

	public NotesNumberFull(AmbientVizMain p, String className) {
		super(p);
		_className = className;
		_noteCount = new NoteCount(_p);
		_noteCount.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}

	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(255);
			_noteCount.display(_x, _y);
			if(_banner != null){
				_p.shape(_banner, _width/2, 0.93f*_height, _banner.width*_p.width/_banner.width, _banner.height*_p.height/7.4f/_banner.height);
			}
		}
	}

	public void update(String eSchool, String eClass, String eCount){
		_noteCount.updateCount(eSchool, eClass, eCount);
	}

}
