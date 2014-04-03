package ltg.ns;

import java.util.ArrayList;

import ltg.ns.objects.Note;
import ltg.ns.objects.NoteCount;
import ltg.ns.objects.Screen;
import processing.core.PVector;


public class NotesNumberGrid extends Screen{
	
	protected int _currentIndex, _numCols, _numRows, _lastChanged;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<NoteCount> _notesCount;


	public NotesNumberGrid() {
		super();
	}

	public NotesNumberGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_lastChanged = 0;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initNotesCounters();
	}	

	public void initNotesCounters(){
		_notesCount = new ArrayList<NoteCount>();
		for(int i = 0; i < _numCols*_numRows; i++){
			NoteCount n = new NoteCount(_p);
			n.setDimensions(_widthContent, _heightContent);
			_notesCount.add(n);
		}
	}

	public void display(){
		
		super.display();
		
		if(isActive() && !isLoading()){
				_p.background(_p.bgColor);
				for(int i=0; i<_notesCount.size(); i++){
					int currentRow = _p.floor(i / _numCols);
					int currentCol = i % _numCols;
					PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);
					_notesCount.get(i).display(loc.x, loc.y);;
				}
				
				if(checkTime(3000)){
					_notesCount.get((int)_p.random(0, _notesCount.size())).changeNoteCount((int)_p.random(50));
				}
		}
		
	}


	public void setGridParameters(){
		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/_numRows;
		
		_startX = _xSpace/2;
		_startY = _ySpace/2;
		
		_widthContent = (_p.proportionWidth*_p.width);
		_heightContent = (_p.proportionHeight*_p.width);
	}
	
	
	
}
