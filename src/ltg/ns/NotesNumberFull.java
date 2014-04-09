package ltg.ns;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.NoteCount;
import ltg.ns.objects.Screen;

public class NotesNumberFull extends Screen {

	protected NoteCount _noteCount;

	public NotesNumberFull(){
		super();
	}

	public NotesNumberFull(AmbientVizMain p) {
		super(p);
		_noteCount = new NoteCount(_p);
		_noteCount.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}
	
	public NotesNumberFull(AmbientVizMain p, String className) {
		super(p);
		_className = className;
		_noteCount = new NoteCount(_p);
		_noteCount.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}

	public void sendUpdateRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("wordle_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}
	
	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(255);
			_noteCount.display(_x, _y);
			if(checkTime(_p.updateInterval)){
				sendUpdateRequest();
			}
			if(checkTime(3000)){
				_noteCount.changeNoteCount((int)_p.random(150));
			}
		}
	}
}
