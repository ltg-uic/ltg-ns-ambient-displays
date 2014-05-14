package ltg.ns;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.Note;
import ltg.ns.objects.NoteCount;
import ltg.ns.objects.Screen;
import processing.core.PVector;


public class NotesNumberGrid extends Screen{

	protected int _currentIndex, _numCols, _numRows, _lastChanged;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<NoteCount> _notesCount;

	public NotesNumberGrid(AmbientVizMain p, String className, int numOfRows, int numOfColumns, String bannerURL) {
		super(p);
		setShapeBanner(bannerURL);
		if(className!=null) _className = className;
		_name = "notes_number_grid_class_"+className;
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
			if(_banner != null){
				_p.shape(_banner, _width/2, 0.93f*_height, _banner.width*_p.width/_banner.width, _banner.height*_p.height/7.4f/_banner.height);
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

	public void update(String _eSchool, String _eClass, String _eGroup, String _eNumNotes, int i) {
		_notesCount.get(i).updateCount(_eSchool, _eClass, _eGroup, _eNumNotes);
	}
}
