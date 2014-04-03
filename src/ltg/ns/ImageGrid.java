package ltg.ns;


import java.util.ArrayList;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.ns.objects.ImageSet;
import ltg.ns.objects.Screen;
import processing.core.PVector;

public class ImageGrid extends Screen {
	protected int _currentIndex, _numCols, _numRows;
	protected float _startX, _startY, _xSpace, _ySpace, _widthContent, _heightContent;
	protected ArrayList<ImageSet> _imageSets;

	public ImageGrid() {
		super();
	}

	public ImageGrid(AmbientVizMain p, int numOfRows, int numOfColumns) {
		super(p);
		_numRows = numOfRows;
		_numCols = numOfColumns;
		setGridParameters();
		initImageSets();
		//sendInitRequest();
	}	

	public void sendInitRequest(){
		if(_p.xmpp){
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("images_grid_init", null, null, node);
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
				PVector loc = new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY);
				_imageSets.get(i).display(loc.x, loc.y);


			}
			if(checkTime(1000)){
				for(int i=0; i<_imageSets.size(); i++){
					_imageSets.get(i).changeBurstImage();
				}
			}
		}
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
