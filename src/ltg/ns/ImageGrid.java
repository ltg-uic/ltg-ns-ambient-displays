package ltg.ns;


import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.looksgood.ani.Ani;
import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.ImageSet;
import ltg.ns.objects.ScoreLine;
import ltg.ns.objects.Screen;
import processing.core.PVector;

public class ImageGrid extends Screen {
	protected int _currentIndex, _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent, _difX, _difY;
	protected ArrayList<ImageSet> _imageSets;
	protected boolean _shifting;

	public ImageGrid() {
		super();
	}

	public ImageGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_numRows = numOfRows;
		_numCols = numOfColumns;
		_shifting = false;
		_difX = 0;
		_difY = 0;
		setGridParameters();
		initImageSets();
	}	

	public ImageGrid(AmbientVizMain p, String className, int numOfRows, int numOfColumns) {
		super(p);
		_className = className;
		_numRows = numOfRows;
		_numCols = numOfColumns;
		_shifting = false;
		setGridParameters();
		initImageSets();
	}	

	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			node.put("school", _className);
			LTGEvent eventInit = new LTGEvent("images_grid_init", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}


	public void sendUpdateRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			node.put("school", _className);
			LTGEvent eventInit = new LTGEvent("images_grid_update", null, null, node);
			_p.eh.generateEvent(eventInit);
		}
	}

	public void initImageSets(){
		_imageSets = new ArrayList<ImageSet>();
		for(int i = 0; i < _numCols*_numRows; i++){
			ImageSet s = new ImageSet(_p, 4);
			s.setDimensions(_widthContent, _heightContent);
			_imageSets.add(s);
		}
	}

	public void display(){

		super.display();

		if(isActive() && !isLoading()){

			_p.background(255);

			for(int i=0; i<_imageSets.size(); i++){

				int currentRow = _p.floor(i / _numCols);
				int currentCol = i % _numCols;
				PVector loc = new PVector();
				loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);

//				if(currentRow == 0){
//					if(currentCol < _numCols-1)
//						loc = new PVector(currentCol*_xSpace + _startX + _difX, currentRow*_ySpace + _startY);
//					else
//						loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY + _difY);
//				}
//				else if(currentRow == _numRows-1){
//					loc = new PVector(currentCol*_xSpace + _startX + _difX, currentRow*_ySpace + _startY);
//				}
//				else{
//					if(currentRow % 2 == 0){
//						if(currentCol < _numCols-1 )
//							loc = new PVector(currentCol*_xSpace + _startX + _difX, currentRow*_ySpace + _startY);
//						else if( currentCol == _numCols-1)
//							loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY + _difY);
//					}
//					else{
//						if(currentCol > 0 )
//							loc = new PVector(currentCol*_xSpace + _startX - _difX, currentRow*_ySpace + _startY);
//						else if( currentCol == 0)
//							loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY + _difY);		
//					}
//				}

				_imageSets.get(i).display(loc.x, loc.y);

			}
			//			if(checkTime(_p.updateInterval)){
			//				sendUpdateRequest();
			//			}
			if(checkTime(5000)){

			}
		}
	}

	public void shiftRightX(){
		Ani.to(this, 0.5f, "_difX",+_xSpace, Ani.LINEAR, "onEnd:resetDifs");
	}

	public void shiftDownY(){
		Ani.to(this, 0.5f, "_difY",+_ySpace, Ani.LINEAR, "onEnd:resetDifs");
	}

	public void resetDifs(){
		_difX = 0;
		_difY = 0;
		Ani.to(this, 0.2f, "_difX", 0, Ani.LINEAR);
		Ani.to(this, 0.2f, "_difY", 0, Ani.LINEAR);
		
		ImageSet tmp = _imageSets.get(_imageSets.size()-1);
		_imageSets.add(0, tmp);
		_imageSets.remove(_imageSets.size()-1);
	}


	public void update(ArrayList<String> urls, int i) {
		_imageSets.get(i).setImages(urls);
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
