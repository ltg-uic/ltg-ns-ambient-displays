package ltg.ns;


import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.looksgood.ani.Ani;
import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.ImageSet;
import ltg.ns.objects.Note;
import ltg.ns.objects.Screen;
import processing.core.PVector;

public class NotesGrid extends Screen{

	protected int _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent,  _difX, _difY;;
	protected ArrayList<Note> _notes;

	public NotesGrid() {
		super();
	}

	public NotesGrid(AmbientVizMain p, String className, int numOfRows, int numOfColumns) {
		super(p);
		if(className!=null) _className = className;
		_name = "notes_grid_class_"+className;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		_startX = 0;
		setGridParameters();
		initNotes();
	}	
	
	public NotesGrid(AmbientVizMain p, String className, int numOfRows, int numOfColumns, String bannerURL) {
		super(p);
		setShapeBanner(bannerURL);
		if(className!=null) _className = className;
		_name = "notes_grid_class_"+className;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		_startX = 0;
		setGridParameters();
		initNotes();
	}	

	public void initNotes(){
		_notes = new ArrayList<Note>();
		for(int i = 0; i < _numCols*_numRows; i++){
			Note n = new Note(_p);
			n.setDimensions(_widthContent, _heightContent);
			_notes.add(n);
		}
	}

	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			_p.background(_p.bgColor);
			for(int i=0; i<_notes.size(); i++){
				int currentRow = _p.floor(i / _numCols);
				int currentCol = i % _numCols;
				PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);

				//					PVector loc = new PVector();
				//					if(currentRow == 0){
				//						if(currentCol < _numCols-1)
				//							loc = new PVector(currentCol*_xSpace + _startX + _difX, currentRow*_ySpace + _startY);
				//						else
				//							loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY + _difY);
				//					}
				//					else if(currentRow == _numRows-1){
				//						loc = new PVector(currentCol*_xSpace + _startX + _difX, currentRow*_ySpace + _startY);
				//					}
				//					else{
				//						if(currentRow % 2 == 0){
				//							if(currentCol < _numCols-1 )
				//								loc = new PVector(currentCol*_xSpace + _startX + _difX, currentRow*_ySpace + _startY);
				//							else if( currentCol == _numCols-1)
				//								loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY + _difY);
				//						}
				//						else{
				//							if(currentCol > 0 )
				//								loc = new PVector(currentCol*_xSpace + _startX - _difX, currentRow*_ySpace + _startY);
				//							else if( currentCol == 0)
				//								loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY + _difY);		
				//						}
				//					}
				_notes.get(i).display(loc.x, loc.y);
			}
			if(_banner != null){
				_p.shape(_banner, _width/2, 0.93f*_height, _banner.width*_p.width/_banner.width, _banner.height*_p.height/7.4f/_banner.height);
			}
		}	
	}

	public void shiftRightX(){
		Ani.to(this, 1f, "_difX",+_xSpace, Ani.LINEAR, "onEnd:resetDifs");
	}

	public void shiftDownY(){
		Ani.to(this, 1f, "_difY",+_ySpace, Ani.LINEAR, "onEnd:resetDifs");
	}

	public void resetDifs(){
		_difX = 0;
		_difY = 0;
		Ani.to(this, 0.2f, "_difX", 0, Ani.LINEAR);
		Ani.to(this, 0.2f, "_difY", 0, Ani.LINEAR);

		Note tmp = _notes.get(_notes.size()-1);
		_notes.add(0, tmp);
		_notes.remove(_notes.size()-1);
	}

	public void setGridParameters(){

		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/_numRows;

		_startX = _xSpace/2;
		_startY = _ySpace/2;

		_widthContent = (_p.proportionWidth*_p.width);
		_heightContent = (_p.proportionHeight*_p.width);
	}

	public void update(String school, String classname, String group, String note, int i){
		_notes.get(i).updateNote(school, classname, group, note);
	}
}
