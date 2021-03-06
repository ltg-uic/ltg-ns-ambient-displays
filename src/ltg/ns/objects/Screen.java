package ltg.ns.objects;



import ltg.ns.AmbientVizMain;
import ltg.ns.update.Updater;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PShape;
import de.looksgood.ani.Ani;

public class Screen{
	protected float _width, _height, _x, _y;
	protected int _graphicsTint, _lastChanged;
	float _graphicsScale;
	protected boolean _active, _loading, _shifting;
	public AmbientVizMain _p;
	public String _className="all", _name="";
	protected boolean _initActivation;
	PGraphics _pg;
	protected PShape _banner;

	public Screen() {
	}

	public Screen(AmbientVizMain p) {
		_p = p;
		_active = _loading = _shifting = false;
		_initActivation = true;
		_width = _p.width;
		_height = _p.height;
		_x = _width/2;
		_y = _height/2;
		_graphicsScale = 1.0f;
		_graphicsTint = 255;
	}	

	public Screen(AmbientVizMain p, float w, float h, float x, float y) {
		_p = p;
		_active = _loading = _shifting = false;
		_width = w;
		_height = h;
		_x = x;
		_y = y;
		_graphicsScale = 1.0f;
		_graphicsTint = 255;
	}	


	public void setShapeBanner(String bannerURL){
		if(bannerURL != null){
			while(_banner == null){
				_banner = _p.loadShape(bannerURL);
			}
		}
	}


	public void setLocation(float x, float y){
		_x = x;
		_y = y;
	}

	public void setDimensions(float w, float h){
		_width = w;
		_height = h;
	}

	public void setActive(boolean active){
		_active = active;
		if(_active){
			_loading = true;
			_graphicsTint = 100;
			_graphicsScale = 0.0f;
			Ani.to(this, 1f, "_graphicsTint", 255);
			Ani.to(this, 1f, "_graphicsScale", 1.0f, Ani.EXPO_OUT, "onEnd:backgroundLoaded");
		}
		else if(!_active){
			Ani.to(this, 1, "_graphicsScale", 0.0f);
		}
	}

	public void backgroundLoaded(){
		_loading = false;
		_graphicsScale = 0;
		Ani.to(this, 1f, "_graphicsScale", 1.0f, Ani.EXPO_OUT);
	}

	public void display(){
		if(isLoading()){
			_p.fill(_p.bgColor);
			_p.rectMode(_pg.CENTER);
			_p.translate(_x, _y);
			_p.scale(_graphicsScale);
			_p.rect(0, 0, _width, _height);
		}
	}

	public boolean isActive() {
		return _active;
	}

	public boolean isLoading() {
		return _loading;
	}

	public void startGraphics(){
		_pg.beginDraw();
		_pg.noStroke();
		_pg.imageMode(_pg.CENTER);
		_pg.background(_p.bgColor);
	}

	public void endGraphics(){
		_pg.endDraw();
		_p.translate(_x, _y);
		_p.scale(_graphicsScale);
		_p.imageMode(_p.CENTER);
		_p.tint(_graphicsTint);
		_p.image(_pg, 0, 0);
	}

	public void mouseClicked(float x, float y) {
		// TODO Auto-generated method stub

	}

	protected boolean checkTime(int time){
		int now = _p.millis();	
		if(now - _lastChanged > time){
			_lastChanged = now;
			return true;
		}
		return false;
	}

}


