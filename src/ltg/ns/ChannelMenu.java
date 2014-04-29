package ltg.ns;


import java.util.ArrayList;
import java.util.Set;

import ltg.ns.objects.ChannelIcon;
import ltg.ns.objects.Screen;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PShape;
import processing.core.PVector;

public class ChannelMenu{
	protected int _currentIndex, _numCols, _numOfChannels, _width, _height, _x, _y;
	protected float _startX, _startY, _xSpace, _ySpace;
	protected ArrayList<ChannelIcon> _icons;
	int _maxIcons; 
	AmbientVizMain _p;
	PShape _selector;

	public ChannelMenu(AmbientVizMain p, int numOfColumns, int x, int y, ArrayList<String> iconspaths, int startChannel, String selectorURL) {
		_p = p;
		_x = x;
		_y = y;
		_icons = new ArrayList<ChannelIcon>();
		_numCols = numOfColumns;
		int numRows = (int)iconspaths.size()/_numCols;
		//initialize icons
		for(int i = 0; i<iconspaths.size(); i++){
			_icons.add(new ChannelIcon(_p, 0, 0, _p.height/(_numCols*2.2f), _p.height/(_numCols*1.9f), i+startChannel, iconspaths.get(i)));
		}
		_maxIcons = PApplet.ceil((float)_icons.size()/_numCols)*_numCols;
		setGridParametets();
		_selector = _p.loadShape(selectorURL);
	}

	//	public ChannelMenu(AmbientVizMain p, int numOfChannels, int numOfColumns, int x, int y) {
	//		_p = p;
	//		_x = x;
	//		_y = y;
	//		_icons = new ArrayList<ChannelIcon>();
	//		_numCols = numOfColumns;
	//		
	//		//initialize icons
	//		int i = 0;		
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.height/(_numCols*2.2f), _p.height/(_numCols*1.9f), i, "Menu_Selector_notes_s.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_no_notes_s.svg"));		
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_tags_s.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_pictures_s.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_wordle_s.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_notes_p.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_no_notes_p.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_tags_p.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_pictures_p.svg"));
	//		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1)/2, _p.width/(_numCols)/2, ++i, "Menu_Selector_wordle_p.svg"));
	//	
	////		for(int i = 0; i<numOfChannels; i++){
	////			ChannelIcon icon = new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols+1), i);
	////			icon.setShape("test1-02.svg");
	////			_icons.add(icon);
	////		}
	//		
	//		_maxIcons = PApplet.ceil((float)_icons.size()/_numCols)*_numCols;
	//		setGridParametets();
	//	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
		setGridParametets();
	}


	public void mouseClicked(float x, float y){
		int channel = -1;
		for(int i=0; i < _icons.size(); i++){
			int a = _icons.get(i).mouseClicked(x, y);
			if(a != -1){
				channel = a;
				break;
			}
		}
		if(channel != -1){
			_p.channelSelected(channel);			
		}
	}


	public void display(){	
		_p.shapeMode(_p.CORNER);
		_p.shape(_selector, _x, _y+0.3f*_height, 0.04f*_width, 0.9f*_height);
		for(int i=0; i<_maxIcons; i++){
			int currentRow = PApplet.floor((float)i / _numCols);
			int currentCol = i % _numCols;				
			if(i<_icons.size()){
				_icons.get(i).loc(new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY));
				_icons.get(i).display();
			}
		}
	}

	public void setGridParametets(){
		_xSpace  = _width/_numCols;
		_ySpace  = _height/(_maxIcons/_numCols)-0.05f*_height;
		_startX = _x + _xSpace/2;
		_startY = _y + _ySpace/2+(0.3f*_height);
	}
}
