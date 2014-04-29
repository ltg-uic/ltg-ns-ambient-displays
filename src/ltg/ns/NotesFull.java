package ltg.ns;



import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PShape;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Note;
import ltg.ns.objects.Screen;

public class NotesFull extends Screen{

	protected Note _note;

	public NotesFull(AmbientVizMain p, String className, String bannerURL) {
		super(p);
		System.out.println(className);
		if(className!=null) _className = className;
		_name = "notes_full_class_"+_className;
		setShapeBanner(bannerURL);
		_note = new Note(_p);
		_note.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}

	public void display(){
		if(isActive() && !isLoading()){
			_p.background(255);
			_note.display(_x, _y);
			if(_banner != null){
				_p.shape(_banner, _width/2, 0.93f*_height, _banner.width*_p.width/_banner.width, _banner.height*_p.height/7.4f/_banner.height);
			}
		}
		super.display();
	}

	public void update(String eSchool, String eClass, String eGroup,String eNoteBody){
		System.out.println("updating note");
		_note.updateNote(eSchool, eClass, eGroup, eNoteBody);
	}
}
