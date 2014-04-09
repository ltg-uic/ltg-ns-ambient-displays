package ltg.ns;



import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Note;
import ltg.ns.objects.Screen;

public class NotesFull extends Screen{

	protected Note _note;

	public NotesFull(){
		super();
	}

	public NotesFull(AmbientVizMain p) {
		super(p);
		_note = new Note(_p);
		_note.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}
	
	public NotesFull(AmbientVizMain p, String className) {
		super(p);
		_className = className;
		_note = new Note(_p);
		_note.setDimensions(_width-_p.borderFullChannels, _height-_p.borderFullChannels);
	}

	public void sendInitRequest(){
		this.sendUpdateRequest();
	}

	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(255);
			_note.display(_x, _y);
			if(checkTime(_p.updateInterval)){
				sendUpdateRequest();
			}
		}
	}
	
	public void sendUpdateRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("notes_full_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}
	
	public void update(String eSchool, String eClass, String eNoteBody){
		_note.updateNote(eSchool, eClass, eNoteBody);
	}
}
