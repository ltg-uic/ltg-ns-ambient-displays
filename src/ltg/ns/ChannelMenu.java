package ltg.ns;


import java.util.ArrayList;
import java.util.Set;

import ltg.ns.objects.ChannelIcon;
import ltg.ns.objects.Screen;
import de.looksgood.ani.Ani;
import processing.core.PApplet;
import processing.core.PVector;

public class ChannelMenu extends Screen{
	protected int _currentIndex, _numCols, _numOfChannels;
	protected float _startX, _startY, _xSpace, _ySpace;
	protected ArrayList<ChannelIcon> _icons;
	int _maxIcons; 

	public ChannelMenu(AmbientVizMain p, int numOfChannels, int numOfColumns) {
		super(p);
		_icons = new ArrayList<ChannelIcon>();
		_numCols = numOfColumns;
		

		//initialize icons
		int i = 0;		
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), i, "Menu_Selector_notes_s.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_no_notes_s.svg"));		
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_tags_s.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_pictures_s.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_wordle_s.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_notes_p.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_no_notes_p.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_tags_p.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_pictures_p.svg"));
		_icons.add(new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols), ++i, "Menu_Selector_wordle_p.svg"));

		
//		for(int i = 0; i<numOfChannels; i++){
//			ChannelIcon icon = new ChannelIcon(_p, 0, 0, _p.width/(_numCols+1), _p.width/(_numCols+1), i);
//			icon.setShape("test1-02.svg");
//			_icons.add(icon);
//		}

		_active = true;
		_maxIcons = PApplet.ceil((float)_icons.size()/_numCols)*_numCols;
		setGridParametets();
	}

	@Override
	public void setActive(boolean active){
		_active = active;
	}

	public void display(){
		if(isActive()){
			_p.fill(_p.bgColor);
			_p.rectMode(_p.CORNER);
			_p.rect(0, 0, _p.displayWidth, _p.displayHeight);
			
			for(int i=0; i<_maxIcons; i++){
				int currentRow = PApplet.floor((float)i / _numCols);
				int currentCol = i % _numCols;
				
				if(i<_icons.size()){

					_icons.get(i).loc(new PVector(currentCol*_xSpace + _startX, currentRow*_ySpace + _startY));
					_icons.get(i).display();
				}
			}
		}
	}


	@Override
	public void mouseClicked(float x, float y){
		float _x = x;
		float _y = y;
		int channel = -1;
		for(int i=0; i < _icons.size(); i++){
			int a = _icons.get(i).mouseClicked(_x, _y);
			if(a != -1){
				channel = a;
				break;
			}
		}
		if(channel != -1){
			_p.channelSelected(channel);
		}
	}

	private void setGridParametets(){
		_xSpace  = _p.width/_numCols;
		_ySpace  = _p.height/(_maxIcons/_numCols);
		_startX = _xSpace/2;
		_startY = _ySpace/2;
	}
}
