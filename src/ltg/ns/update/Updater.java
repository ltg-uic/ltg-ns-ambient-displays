package ltg.ns.update;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.commons.ltg_event_handler.SingleChatLTGEventListener;
import ltg.ns.AmbientVizMain;
import ltg.ns.NotesNumberGrid;
import ltg.ns.objects.Note;
import processing.core.PApplet;

public class Updater {

	AmbientVizMain _p;
	
	public Updater(AmbientVizMain p){
		_p = p;
	}

	public void updateNoteFull(LTGEvent e){ 
	    _p.println();
		_p.notesFull.update(e.getPayload().get("note_body").asText());
	}
	
	
}
