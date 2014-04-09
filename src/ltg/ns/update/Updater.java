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
	
	public void updateNoteFull(LTGEvent e){ 
		String _eClass = e.getPayload().get("class").asText();
		String _eSchool = e.getPayload().get("school").asText();
		String _eNoteBody = e.getPayload().get("note_body").asText();
		
		switch (_eClass){
			case "all":{
				_p.notesFullAll.update(_eSchool, _eClass, _eNoteBody);
			}
			default:{
				_p.notesFullOur.update(_eSchool, _eClass, _eNoteBody);
			}
			
		}
	}
	
	public void initNoteGrid(LTGEvent e){
		String _eClass = e.getPayload().get("class").asText();
		String _eSchool = e.getPayload().get("school").asText();
		
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() == _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				_p.notesGridOur.update(a.get(i).get("note_body").asText(), i);
			}
		}
	}
	
	public void initNumberNotesFull(LTGEvent e){ 	
		String a = e.getPayload().get("wordle_text").asText();
		_p.wordleCollectiveOur.update(a);
	}
	
	public void initNumberNotesGrid(LTGEvent e){ 
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		for(int i=0; i < a.size(); i++){
			String s = a.get(i).get("wordle_text").asText();
			_p.wordleGridOur.update(s, i);
		}
	}
	
	public void initImageFull(LTGEvent e){ 		
		ArrayNode a = (ArrayNode) e.getPayload().get("burst");
		ArrayList<String> s = new ArrayList<String>();
		for(int i=0; i < a.size(); i++ ){
			s.add(a.get(i).asText());
		}
		_p.imageFullOur.update(s);
	}
	
	public void initImageGrid(LTGEvent e){ 
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		for(int i=0; i < a.size(); i++){
			ArrayNode b = (ArrayNode) a.get(i).get("burst");
			ArrayList<String> s = new ArrayList<String>();

			for(int j=0; j < b.size(); j++ ){
				s.add(b.get(j).asText());
			}
			_p.imageGridOur.update(s, i);
		}
	}
	
	public void initTagsFull(LTGEvent e){ 	
		String a = e.getPayload().get("wordle_text").asText();
		_p.wordleCollectiveOur.update(a);
	}
	
	public void initTagsGrid(LTGEvent e){ 
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		for(int i=0; i < a.size(); i++){
			String s = a.get(i).get("wordle_text").asText();
			_p.wordleGridOur.update(s, i);
		}
	}
	
	public void initWordleFull(LTGEvent e){ 	
		String a = e.getPayload().get("wordle_text").asText();
		_p.wordleCollectiveOur.update(a);
	}
	
	public void initWordleGrid(LTGEvent e){ 
		
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		for(int i=0; i < a.size(); i++){
			String s = a.get(i).get("wordle_text").asText();
			_p.wordleGridOur.update(s, i);
		}
	}
//	
//	public void initWordleGrid(LTGEvent e){ 
//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
//		for(int i=0; i < a.size(); i++){
//			ArrayNode b = (ArrayNode) a.get(i).get("burst");
//			ArrayList<String> s = new ArrayList<String>();
//
//			for(int j=0; j < b.size(); j++ ){
//				s.add(b.get(j).asText());
//			}
//			_p.imageGrid.update(s, i);
//		}
//	}
//	
//	public void initScoreFull(LTGEvent e){ 		
//		ArrayNode a = (ArrayNode) e.getPayload().get("burst");
//		ArrayList<String> s = new ArrayList<String>();
//		for(int i=0; i < a.size(); i++ ){
//			s.add(a.get(i).asText());
//		}
//		_p.imageFull.update(s);
//	}
//	
//	public void initScoreGrid(LTGEvent e){ 
//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
//		for(int i=0; i < a.size(); i++){
//			ArrayNode b = (ArrayNode) a.get(i).get("burst");
//			ArrayList<String> s = new ArrayList<String>();
//
//			for(int j=0; j < b.size(); j++ ){
//				s.add(b.get(j).asText());
//			}
//			_p.imageGrid.update(s, i);
//		}
//	}
	
	
	
	
}
