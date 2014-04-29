package ltg.ns.update;

import java.util.ArrayList;

import org.omg.CORBA._PolicyStub;

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

	public synchronized void notesFullInit(final LTGEvent e){ 	
		String _eType = e.getPayload().get("type").asText();
		String _eSchool = e.getPayload().get("school").asText();
		String _eClass = e.getPayload().get("class").asText();
		String _eGroup = e.getPayload().get("group").asText();
		String _eNoteBody = e.getPayload().get("note_body").asText();

		if (_eType.equals("all")){
			_p.notesFullAll.update(_eSchool, _eClass, _eGroup, _eNoteBody);
		}
		else{
			_p.notesFullOur.update(_eSchool, _eClass, _eGroup, _eNoteBody);
		}
	}

	public synchronized void notesGridInit(LTGEvent e){
		String _eType = e.getPayload().get("type").asText();
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() <= _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				String _eSchool = a.get(i).get("school").asText();
				String _eClass = a.get(i).get("class").asText();
				String _eGroup = a.get(i).get("group").asText();
				String _eNoteBody = a.get(i).get("note_body").asText();
				//boolean _eUpdated = a.get(i).get("updated").asBoolean();
				if (_eType.equals("all")){
					_p.notesGridAll.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
				else{
					_p.notesGridOur.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
			}
		}
	}

	public synchronized void numberNotesFullInit(LTGEvent e){
		String _eType = e.getPayload().get("type").asText();
		String _eSchool = e.getPayload().get("school").asText();
		String _eClass = e.getPayload().get("class").asText();
		String _eCount = e.getPayload().get("#_note").asText();
		if (_eType.equals("all")){
			_p.numNotesFullAll.update(_eSchool, _eClass, _eCount);
		}
		else{
			_p.numNotesFullOur.update(_eSchool, _eClass, _eCount);
		}
	}

	public synchronized void numberNotesGridInit(LTGEvent e){ 
		String _eType = e.getPayload().get("type").asText();
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() <= _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				String _eSchool = a.get(i).get("school").asText();
				String _eClass = a.get(i).get("class").asText();
				String _eGroup = a.get(i).get("group").asText();
				String _eNoteBody = a.get(i).get("#_notes").asText();
				//boolean _eUpdated = a.get(i).get("updated").asBoolean();

				if (_eType.equals("all")){
					_p.numNotesGridAll.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
				else{
					_p.numNotesGridOur.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
			}
		}
	}

	public synchronized void wordleFullInit(LTGEvent e){ 
		String _eType = e.getPayload().get("type").asText();
		String _eSchool = e.getPayload().get("school").asText();
		String _eClass = e.getPayload().get("class").asText();
		//String _eGroup = e.getPayload().get("group").asText();
		String a = e.getPayload().get("wordle_text").asText();

		if (_eType.equals("all")){
			_p.wordleCollectiveAll.update(_eSchool, _eClass, a);
		}
		else{
			_p.wordleCollectiveOur.update(_eSchool, _eClass, a);
		}
	}

	public synchronized void wordleGridInit(LTGEvent e){ 
		String _eType = e.getPayload().get("type").asText();
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() <= _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				String _eSchool = a.get(i).get("school").asText();
				String _eClass = a.get(i).get("class").asText();
				String _eGroup = a.get(i).get("group").asText();
				String _eNoteBody = a.get(i).get("wordle_text").asText();
				//boolean _eUpdated = a.get(i).get("updated").asBoolean();

				if (_eType.equals("all")){
					_p.wordleGridAll.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
				else{
					_p.wordleGridOur.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}

			}
		}
	}
	//	public void tagsFullInit(LTGEvent e){ 	
	//		String a = e.getPayload().get("wordle_text").asText();
	//		_p.wordleCollectiveOur.update(a);
	//	}
	//
	//	public void tagsGridInit(LTGEvent e){ 	
	//		String a = e.getPayload().get("wordle_text").asText();
	//		_p.wordleCollectiveOur.update(a);
	//	}
	//	
	//	public void initNoteGrid(LTGEvent e){
	//		String _eClass = e.getPayload().get("class").asText();
	//		String _eSchool = e.getPayload().get("school").asText();
	//		
	//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
	//		if(a.size() == _p.gridSquares){
	//			for(int i=0; i < a.size(); i++ ){
	//				_p.notesGridOur.update(a.get(i).get("note_body").asText(), i);
	//			}
	//		}
	//	}
	//	
	//	public void imageFullInit(LTGEvent e){ 	
	//		String _eSchool = e.getPayload().get("school").asText();
	//		String _eClass = e.getPayload().get("class").asText();
	//		String _eGroup = e.getPayload().get("group").asText();
	//		String _eTime = e.getPayload().get("time").asText();
	//		//get burst
	//		ArrayNode a = (ArrayNode) e.getPayload().get("burst");
	//		ArrayList<String> s = new ArrayList<String>();
	//		for(int i=0; i < a.size(); i++ ){
	//			s.add(a.get(i).asText());
	//		}
	//		if(_eClass.equals(_p.className)){
	//			_p.imageFullOur.update(_eSchool, _eClass, _eGroup, _eTime, s);
	//		}
	//		else if (_eClass.equals("all")){
	//			_p.imageFullAll.update(_eSchool, _eClass, _eGroup, _eTime, s);
	//		}
	//	}
	//
	//	public void imageGridInit(LTGEvent e){ 	
	//		//get grid
	//		String _eClass = e.getPayload().get("class").asText();
	//		String _eSchool = e.getPayload().get("school").asText();
	//		String _eGroup = e.getPayload().get("group").asText();
	//	}


	////////////// UPDATES ///////////////////////////////////
	//////////////////////////////////////////////////////////

	public synchronized void notesFullUpdate(LTGEvent e){ 	
		notesFullInit(e);
	}

	public synchronized void numberNotesFullUpdate(LTGEvent e){ 	
		numberNotesFullInit(e);
	}

	public synchronized void wordleFullUpdate(LTGEvent e){ 	
		wordleFullInit(e);
	}

	//	public void imageFullUpdate(LTGEvent e){ 	
	//		imageFullInit(e);
	//	}

	//	public void tagsFullUpdate(LTGEvent e){ 	
	//		tagsFullInit(e);
	//	}

	//grid
	//	public void imageGridUpdate(LTGEvent e){ 	
	//
	//	}

	public synchronized void notesGridUpdate(LTGEvent e){ 
		String _eType = e.getPayload().get("type").asText();
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() <= _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				String _eSchool = a.get(i).get("school").asText();
				String _eClass = a.get(i).get("class").asText();
				String _eGroup = a.get(i).get("group").asText();
				String _eNoteBody = a.get(i).get("note_body").asText();
				//boolean _eUpdated = a.get(i).get("updated").asBoolean();

				if (_eType.equals("all")){	
					_p.notesGridAll.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
				else{
					_p.notesGridOur.update(_eSchool, _eClass, _eGroup, _eNoteBody, i);
				}
			}
		}
	}

	public synchronized void numberNotesGridUpdate(LTGEvent e){
		String _eType = e.getPayload().get("type").asText();
		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
		if(a.size() <= _p.gridSquares){
			for(int i=0; i < a.size(); i++ ){
				String _eSchool = a.get(i).get("school").asText();
				String _eClass = a.get(i).get("class").asText();
				String _eGroup = a.get(i).get("group").asText();
				String _eNumNotes = a.get(i).get("#_notes").asText();

				//if(_eUpdated){
				if (_eType.equals("all")){
					_p.numNotesGridAll.update(_eSchool, _eClass, _eGroup, _eNumNotes, i);
				}
				else{
					_p.numNotesGridOur.update(_eSchool, _eClass, _eGroup, _eNumNotes, i);
				}
			}
		}
	}


	public synchronized void wordleGridUpdate(LTGEvent e){ 	
		//String a = e.getPayload().get("wordle_text").asText();
		//_p.wordleCollectiveOur.update(a);
	}



	//	public void tagsGridUpdate(LTGEvent e){ 	
	//		String a = e.getPayload().get("wordle_text").asText();
	//		_p.wordleCollectiveOur.update(a);
	//	}
	//	public void initNumberNotesFull(LTGEvent e){ 	
	//		String a = e.getPayload().get("wordle_text").asText();
	//		_p.wordleCollectiveOur.update(a);
	//	}
	//	
	//	public void initNumberNotesGrid(LTGEvent e){ 
	//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
	//		for(int i=0; i < a.size(); i++){
	//			String s = a.get(i).get("wordle_text").asText();
	//			_p.wordleGridOur.update(s, i);
	//		}
	//	}
	//	
	//	public void initImageFull(LTGEvent e){ 		
	//		ArrayNode a = (ArrayNode) e.getPayload().get("burst");
	//		ArrayList<String> s = new ArrayList<String>();
	//		for(int i=0; i < a.size(); i++ ){
	//			s.add(a.get(i).asText());
	//		}
	//		_p.imageFullOur.update(s);
	//	}
	//	
	//	public void initImageGrid(LTGEvent e){ 
	//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
	//		for(int i=0; i < a.size(); i++){
	//			ArrayNode b = (ArrayNode) a.get(i).get("burst");
	//			ArrayList<String> s = new ArrayList<String>();
	//
	//			for(int j=0; j < b.size(); j++ ){
	//				s.add(b.get(j).asText());
	//			}
	//			_p.imageGridOur.update(s, i);
	//		}
	//	}
	//	
	//	public void initTagsFull(LTGEvent e){ 	
	//		String a = e.getPayload().get("wordle_text").asText();
	//		_p.wordleCollectiveOur.update(a);
	//	}
	//	
	//	public void initTagsGrid(LTGEvent e){ 
	//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
	//		for(int i=0; i < a.size(); i++){
	//			String s = a.get(i).get("wordle_text").asText();
	//			_p.wordleGridOur.update(s, i);
	//		}
	//	}
	//	
	//	public void initWordleFull(LTGEvent e){ 	
	//		String a = e.getPayload().get("wordle_text").asText();
	//		_p.wordleCollectiveOur.update(a);
	//	}
	//	
	//	public void initWordleGrid(LTGEvent e){ 
	//		
	//		ArrayNode a = (ArrayNode) e.getPayload().get("grid");
	//		for(int i=0; i < a.size(); i++){
	//			String s = a.get(i).get("wordle_text").asText();
	//			_p.wordleGridOur.update(s, i);
	//		}
	//	}
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
