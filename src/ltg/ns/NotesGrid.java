package ltg.ns;


import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Note;
import ltg.ns.objects.Screen;
import ltg.ns.objects.Wordle;
import processing.core.PVector;

public class NotesGrid extends Screen{
	
	protected int _currentIndex, _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<Note> _notes;


	public NotesGrid() {
		super();
	}

	public NotesGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_lastChanged = 0;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		sendInitRequest();
		setGridParameters();
		initNotes();
	}	

	private void initNotes(){
		_notes = new ArrayList<Note>();
		for(int i = 0; i < _numCols*_numRows; i++){
			Note n = new Note(_p);
			n.setDimensions(_widthContent, _heightContent);
			_notes.add(n);
		}
	}
	
	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("notes_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}
	
	public void update(String note, int i){
		_notes.get(i).updateNote(note);
	}

	public void display(){
		
		super.display();
		
		if(isActive() && !isLoading()){
				_p.background(_p.bgColor);
				for(int i=0; i<_notes.size(); i++){
					int currentRow = _p.floor(i / _numCols);
					int currentCol = i % _numCols;
					PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);
					_notes.get(i).display(loc.x, loc.y);;
				}
				
//				if(checkTime(3000)){
//					_notes.get((int)_p.random(0, _notes.size())).changeNote();
//				}

		}
		
	}


	public void setGridParameters(){
		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/_numRows;
		_startX = _xSpace/2;
		_startY = _ySpace/2;
		_widthContent = (_p.width - _xSpace*(_numCols-1) - _startX*2)*200f;
		_heightContent = (_p.height - _ySpace*(_numRows-1) - _startY*2)*125f;
		
	}
	
}
