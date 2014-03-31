package ltg.ns;


import processing.core.PImage;
import de.looksgood.ani.*;
import ltg.ns.objects.ImageSet;
import ltg.ns.objects.Screen;

public class ImageFull extends Screen {
	protected PImage _img;
	protected ImageSet _imageSet;

	public ImageFull(){
		super();
	}

	public ImageFull(AmbientVizMain p) {
		super(p);
		_imageSet = new ImageSet(_p, 4);
		_imageSet.setDimensions(_width, _height);
	}

	@Override
	public void display(){
		super.display();
		if(isActive() && !isLoading()){
			//drawImage();
			_p.background(255);
			_imageSet.display(_x, _y);
			if(checkTime(3000)){
				_imageSet.changeImage();
			}
		}
	}
}
