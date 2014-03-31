package ltg.ns;

import java.util.ArrayList;

import org.omg.CORBA._PolicyStub;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.commons.ltg_event_handler.SingleChatLTGEventHandler;
import ltg.commons.ltg_event_handler.SingleChatLTGEventListener;
import ltg.ns.objects.Screen;
import ltg.ns.update.Updater;
import processing.core.PApplet;
import processing.core.PFont;
import de.looksgood.ani.*;


public class AmbientVizMain extends PApplet{

	public SingleChatLTGEventHandler eh;
	boolean xmpp = true;
	
	private String chatRoom = "nh-test@conference.ltg.evl.uic.edu";
	private String botUsername = "hg-beacon-test@ltg.evl.uic.edu";
	private String botPassword = "hgbeacon";

	ArrayList<Screen> screens = new ArrayList<Screen>(); 
	
	public Screen menu, wordleCollective, wordleGrid, imageFull, imageGrid, scoreFull, scoreGrid, numNotesFull, numNotesGrid, notesFull, notesGrid;

	Updater updater;
	
	int numOfChannels = 8;
	public int bgColor = color(255, 255, 255);
	int currentChannel = -1;
	int borderFullChannels = 40;
	public PFont notesFont, notesNumFont;

	public void setup(){
		size(displayWidth, displayHeight);
		background(bgColor);
		smooth();
		Ani.init(this);
		notesFont = loadFont("AlNile-48.vlw");
		notesNumFont = loadFont("AlNile-250.vlw");
		updater = new Updater(this);

		if(xmpp){
			eh = new SingleChatLTGEventHandler(botUsername, botPassword, chatRoom);
			eh.registerHandler("notes_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.updateNoteFull(e);
				}
			});	
			eh.runAsynchronously();
		}
		
		menu = new ChannelMenu (this, numOfChannels, 5);
		imageFull = new ImageFull(this);	
		imageGrid = new ImageGrid(this, 3, 3);	
		wordleCollective = new WordleFull(this);
		wordleGrid = new WordleGrid(this, 3, 3);
		notesFull = new NotesFull(this);
		notesGrid = new NotesGrid(this, 3, 3);
		numNotesFull = new NotesNumberFull(this);
		numNotesGrid = new NotesNumberGrid(this, 3, 3);

		
		menu.setActive(false);

		screens.add(imageGrid);
		screens.add(imageFull);
		screens.add(wordleCollective);
		screens.add(wordleGrid);
		screens.add(notesFull);
		screens.add(notesGrid);
		screens.add(numNotesFull);
		screens.add(numNotesGrid);


		///


		goToMenu();
	}

	public void draw(){
		menu.display();
		for(Screen s : screens){
			s.display();
		}
	}

	public void mouseClicked(){
		if(menu.isActive()){
			menu.mouseClicked(mouseX, mouseY);
		}
		else{
			goToMenu();
		}
	}

	public void channelSelected(int channel){
		currentChannel = channel;
		goToScreen(currentChannel);
	}

	public void goToMenu(){
		for(int i=0; i < screens.size(); i++){
			screens.get(i).setActive(false);
		}
		menu.setActive(true);
	}

	public void goToScreen(int channel){
		menu.setActive(false);
		for(int i=0; i < screens.size(); i++){
			screens.get(i).setActive(false);
		}
		if(channel < screens.size()){
			screens.get(channel).setActive(true);
		}
	}

	public static void main(String args[]) {
		PApplet.main(new String[] { "--present", "ltg.ns.AmbientVizMain" });
	}

}
