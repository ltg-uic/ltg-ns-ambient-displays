package ltg.ns.update;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
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

	public void initNoteFull(LTGEvent e){ 
		
		_p.notesFull.update(e.getPayload().get("note_body").asText());
		
	}
	
	public void initNoteGrid(LTGEvent e){
		
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() == _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				_p.notesGrid.update(a.get(i).get("note_body").asText(), i);
			}
		}
	}
	
	public void initImageFull(LTGEvent e){ 
		
		ArrayNode a = (ArrayNode) e.getPayload().get("burst");
		ArrayList<String> s = new ArrayList<String>();
		for(int i=0; i < a.size(); i++ ){
			s.add(a.get(i).asText());
		}
		_p.imageFull.update(s);
		
	}
	
	public void initImageGrid(LTGEvent e){ 
		
		_p.println("h");

		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		for(int i=0; i < a.size(); i++){
			ArrayNode b = (ArrayNode) a.get(i).get("burst");
			ArrayList<String> s = new ArrayList<String>();

			for(int j=0; j < b.size(); j++ ){
				s.add(b.get(j).asText());
			}
			_p.println(s);
			_p.imageGrid.update(s, i);
		}
		
	}
	
	
	
	
}
