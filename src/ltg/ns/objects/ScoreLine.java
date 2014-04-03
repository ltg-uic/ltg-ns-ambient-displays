package ltg.ns.objects;

import org.jivesoftware.smackx.packet.Header;

import ltg.ns.AmbientVizMain;
import processing.core.PGraphics;
import de.looksgood.ani.Ani;

public class ScoreLine {
	int _width, _height, _x, _y;
	String _tag, _countToday, _count5Min, _countAll;
	AmbientVizMain _p;
	int _gTint;
	PGraphics _pg1;

	public ScoreLine(AmbientVizMain p) {
		_p = p;
		_gTint = 255;
		_tag = "squirrel";
		_countToday = _p.str(10);
		_count5Min = _p.str(25);
		_countAll = _p.str(170);
		_width = (int)(_p.width);
		_height = 100;
		_x = _width/2;
		_height = _height/2;
	}

	public void setDimensions(float width, float height) {
		_width = (int)width;
		_height = (int)height;
	}

	public void updateScoreLine(){
		Ani.to(this, 2f, "_gTint", 255);
		_gTint = 0;
		//updatePosition
		resetTint();
	}
	
	public void resetTint(){
		Ani.to(this, 2f, "_gTint", 255);
	}
	
	public PGraphics display(float x, float y) {
		
		_pg1 = _p.createGraphics(_width, _height);

		_pg1.beginDraw();
		_pg1.background(_p.color(200));
		_pg1.rectMode(_p.CENTER);
		_pg1.noStroke();
		_pg1.fill(255);
		_pg1.rect(_pg1.width/2, _pg1.height/2, _pg1.width-0.01f*_pg1.width, _pg1.height-0.02f*_pg1.height, 4);
		_pg1.fill(0);
		_pg1.textAlign(_p.LEFT);
		_pg1.textFont(_p.boldFont);
		_pg1.textSize(0.06f*_pg1.width);


		_pg1.text(_tag+":", 0.05f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
		_pg1.fill(_p.last5MinColor);

		_pg1.text(_count5Min, 0.4f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
		_pg1.fill(0);
		_pg1.fill(0);
		_pg1.text(_countToday, 0.6f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
		_pg1.text(_countAll, 0.8f*_pg1.width, _pg1.height/2+0.25f*_pg1.height);
		_pg1.endDraw();
		
		
		return _pg1;
	}

	public void updateScoreLine(String title, String num1, String num2, String num3) {
		_tag = title;
		_count5Min = num1;
		_countToday = num2;
		_countAll = num3;
	}
}
