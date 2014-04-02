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
	boolean xmpp = false;
	int numOfChannels = 10;

	
	private String chatRoom = "nh-test@conference.ltg.evl.uic.edu";
	private String botUsername = "hg-beacon-test@ltg.evl.uic.edu";
	private String botPassword = "hgbeacon";

	ArrayList<Screen> screens = new ArrayList<Screen>(); 
	
	public ChannelMenu menu;
	public WordleFull wordleCollective;
	public WordleGrid wordleGrid;
	public ImageFull imageFull;
	public ImageGrid imageGrid;
	public NotesNumberGrid numNotesGrid;
	public NotesNumberFull numNotesFull;
	public NotesFull notesFull;
	public NotesGrid notesGrid;
	public ScoreFull scoreFull;
	public ScoreGrid scoreGrid;

	Updater updater;
	
	public int bgColor = color(255, 255, 255);
	public int last5MinColor = color(0, 125, 224);

	public int gridSquares = 9;
	public PFont normalFont, boldFont;
	public int borderFullChannels = 40;

	int currentChannel = -1;

	public void setup(){
		
		size(displayWidth, displayHeight);
		background(bgColor);
		smooth();
		Ani.init(this);
		normalFont = loadFont("HelveticaNeue-100.vlw");
		boldFont = loadFont("HelveticaNeue-Bold-100.vlw");
		updater = new Updater(this);
		
		if(xmpp){
			eh = new SingleChatLTGEventHandler(botUsername, botPassword, chatRoom);
			eh.registerHandler("notes_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initNoteFull(e);
				}
			});	
			eh.registerHandler("notes_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initNoteGrid(e);
				}
			});	
			eh.registerHandler("images_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initImageFull(e);
				}
			});	
			eh.registerHandler("images_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initImageGrid(e);
				}
			});	
			
			eh.registerHandler("wordle_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initWordleFull(e);
				}
			});	
			
			eh.registerHandler("wordle_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.initWordleGrid(e);
				}
			});	
			
			eh.runAsynchronously();
		}
		
		menu = new ChannelMenu (this, numOfChannels, 5);
		
		
		notesFull = new NotesFull(this);
		numNotesFull = new NotesNumberFull(this);
		scoreFull = new ScoreFull(this);
		imageFull = new ImageFull(this);	
		wordleCollective = new WordleFull(this);

		notesGrid = new NotesGrid(this, 3, 3);
		numNotesGrid = new NotesNumberGrid(this, 3, 3);
		scoreGrid = new ScoreGrid(this, 3, 3);
		imageGrid = new ImageGrid(this, 3, 3);	
		wordleGrid = new WordleGrid(this, 3, 3);
		
		menu.setActive(false);
		screens.add(notesFull);
		screens.add(numNotesFull);
		screens.add(scoreFull);
		screens.add(imageFull);
		screens.add(wordleCollective);

		screens.add(notesGrid);
		screens.add(numNotesGrid);
		screens.add(scoreGrid);
		screens.add(imageGrid);
		screens.add(wordleGrid);

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
