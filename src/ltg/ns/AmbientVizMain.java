package ltg.ns;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import ltg.commons.ltg_event_handler.LTGEvent;
import ltg.commons.ltg_event_handler.SingleChatLTGEventHandler;
import ltg.commons.ltg_event_handler.SingleChatLTGEventListener;
import ltg.ns.objects.Screen;
import ltg.ns.select.SelectionScreen;
import ltg.ns.update.Updater;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import de.looksgood.ani.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class AmbientVizMain extends PApplet{

	protected SingleChatLTGEventHandler eh;
	protected boolean xmpp = true;
	protected int numOfChannels = 20;
	public String className;
	public String displayID;
	public boolean startTimer = false;
	public int startTime = 0;

	private String chatRoom = "nh-test@conference.ltg.evl.uic.edu";
	public String botUsername = "ns-display-a@ltg.evl.uic.edu";
	public String botPassword = "ns-display-a";
	
	public String botUsernameA = "ns-display-a@ltg.evl.uic.edu";
	public String botPasswordA = "ns-display-a";
	public String botUsernameB = "ns-display-b@ltg.evl.uic.edu";
	public String botPasswordB = "ns-display-b";
	public String botUsernameC = "ns-display-c@ltg.evl.uic.edu";
	public String botPasswordC = "ns-display-c";
	public String botUsernameD = "ns-display-d@ltg.evl.uic.edu";
	public String botPasswordD = "ns-display-d";
	public String botUsernameE = "ns-display-e@ltg.evl.uic.edu";
	public String botPasswordE = "ns-display-e";

	public MainScreen mainScreen;
	public ChannelMenu menuOur, menuAll;
	public WordleFull wordleCollectiveOur;
	public WordleGrid wordleGridOur;
	public ImageFull imageFullOur;
	public ImageGrid imageGridOur;
	public NotesNumberGrid numNotesGridOur;
	public NotesNumberFull numNotesFullOur;
	public NotesFull notesFullOur;
	public NotesGrid notesGridOur;
	public ScoreFull scoreFullOur;
	public ScoreGrid scoreGridOur;
	public WordleFull wordleCollectiveAll;
	public WordleGrid wordleGridAll;
	public ImageFull imageFullAll;
	public ImageGrid imageGridAll;
	public NotesNumberGrid numNotesGridAll;
	public NotesNumberFull numNotesFullAll;
	public NotesFull notesFullAll;
	public NotesGrid notesGridAll;
	public ScoreFull scoreFullAll;
	public ScoreGrid scoreGridAll;

	private SelectionScreen classDisplaySelector;
	
	private Updater updater;


	public float proportionWidth = 0.32f;
	public float proportionHeight = 0.195f;


	public int bgColor = color(255, 255, 255);
	public int last5MinColor = color(0, 125, 224);

	public ArrayList<Screen> screens = new ArrayList<Screen>(); 
	public PFont normalFont, boldFont;

	public int gridSquares = 9;
	protected int borderFullChannels;
	protected int borderGridChannels;

	protected boolean channelOffset = false;
	public boolean classDisplaySelected = false;
	public boolean xmppConnected = false;


	protected int currentChannel = -1;

	
	
	public void setup(){
		updater = new Updater(this);		
		size(displayWidth, displayHeight);
		background(bgColor);
		frameRate(60);
		smooth();
		Ani.init(this);
		normalFont = loadFont("HelveticaNeue-100.vlw");
		boldFont = loadFont("HelveticaNeue-Bold-100.vlw");
		borderFullChannels = (int) (0.05f*width);
		borderGridChannels = (int) (0.05f*width/2);
		//registerEventHandlers();
		mainScreen = new MainScreen(this);
		classDisplaySelector = new SelectionScreen(this);

	}

	public void draw(){
		if(classDisplaySelected && xmppConnected){
			mainScreen.display();
			for(Screen s : screens){
				s.display();
			}
		}
		else if(classDisplaySelected && !xmppConnected){
			checkTimer();
		}
		else{
			classDisplaySelector.display(width/2, height/2);
		}
	}

	public void mouseClicked(){
		if(classDisplaySelected){
			if(mainScreen.isActive()){
				mainScreen.mouseClicked(mouseX, mouseY);
			}
			else{
				goToMenu();	
			}
		}	
	}
	
	
	public void checkTimer(){
		if(startTimer){
			int current = millis();
			if(abs(current-startTime) > 3000){
				xmppConnected = true;
				sendGeneralInitMessage();
			}
		}
		
	}

	public void channelSelected(int channel){
		if(channelOffset){
			currentChannel = channel+6;
			channelOffset = false;
		}
		else currentChannel = channel;	
		goToScreen(currentChannel);
		println(currentChannel);
	}

	public void goToMenu(){
		for(int i=0; i < screens.size(); i++){
			screens.get(i).setActive(false);
		}
		mainScreen.setActive(true);
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		LTGEvent eventInit = new LTGEvent("menu_active", null, null, node);
		node.put("class", className);
		node.put("screen", "menu");
		node.put("display", displayID);
		this.eh.generateEvent(eventInit);
	}

	public void sendGeneralInitMessage(){
		if(this.xmpp){
			ObjectNode nodeAll = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInitAll = new LTGEvent("displays_init", null, null, nodeAll);
			nodeAll.put("class", "all");
			nodeAll.put("display_id", displayID);
			this.eh.generateEvent(eventInitAll);
			
			ObjectNode node = JsonNodeFactory.instance.objectNode();
			LTGEvent eventInit = new LTGEvent("displays_init", null, null, node);
			node.put("class", className);
			node.put("display_id", displayID);
			this.eh.generateEvent(eventInit);
		}
	}

	public void goToScreen(int channel){
		noStroke();
		mainScreen.setActive(false);
		for(int i=0; i < screens.size(); i++){
			screens.get(i).setActive(false);
		}
		if(channel < screens.size()){
			screens.get(channel).setActive(true);
		}
		ObjectNode node = JsonNodeFactory.instance.objectNode();
		LTGEvent eventInit = new LTGEvent("screen_active", null, null, node);
		node.put("class", className);
		node.put("screen", screens.get(channel)._name);
		node.put("display", displayID);
		this.eh.generateEvent(eventInit);
	}

	public static void main(String args[]) {
		System.out.println("Starting ambient displays");
//		PApplet.main(new String[] { "--present", "ltg.ns.AmbientVizMain", args[0]});
		PApplet.main(new String[] { "--present", "ltg.ns.AmbientVizMain"});
	}

	public void registerEventHandlers(){
		if(xmpp){
			eh = new SingleChatLTGEventHandler(botUsername, botPassword, chatRoom);

			eh.registerHandler("notes_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.notesFullInit(e);
				}
			});	

			eh.registerHandler("notes_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.notesGridInit(e);
				}
			});	

			eh.registerHandler("#_notes_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.numberNotesFullInit(e);
				}
			});	
			eh.registerHandler("#_notes_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.numberNotesGridInit(e);
				}
			});	

			eh.registerHandler("wordle_full_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.wordleFullInit(e);
				}
			});
			eh.registerHandler("wordle_grid_init_r", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.wordleGridInit(e);
				}
			});	

			eh.registerHandler("notes_full_update", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.notesFullUpdate(e);
				}
			});
			eh.registerHandler("notes_grid_update", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.notesGridUpdate(e);
				}
			});	

			eh.registerHandler("#_notes_full_update", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.numberNotesFullUpdate(e);
				}
			});
			eh.registerHandler("#_notes_grid_update", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.numberNotesGridUpdate(e);
				}
			});		

			eh.registerHandler("wordle_full_update", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.wordleFullUpdate(e);
				}
			});	

			eh.registerHandler("wordle_grid_update", new SingleChatLTGEventListener() {
				public void processEvent(LTGEvent e) {
					updater.wordleGridUpdate(e);
				}
			});	

			////////////////////////////////////////////////////////////////////////////
			//update
			//			eh.registerHandler("image_full_update", new SingleChatLTGEventListener() {
			//				public void processEvent(LTGEvent e) {
			//					updater.imageFullUpdate(e);
			//				}
			//			});	
			//			eh.registerHandler("tags_full_update", new SingleChatLTGEventListener() {
			//				public void processEvent(LTGEvent e) {
			//					updater.tagsFullUpdate(e);
			//				}
			//			});
			//			eh.registerHandler("tags_grid_update", new SingleChatLTGEventListener() {
			//				public void processEvent(LTGEvent e) {
			//					updater.tagsGridUpdate(e);
			//				}
			//			});	
			//			eh.registerHandler("images_grid_update", new SingleChatLTGEventListener() {
			//				public void processEvent(LTGEvent e) {
			//					updater.imageGridUpdate(e);
			//				}
			//			});	
			//			eh.registerHandler("tags_full_init_r", new SingleChatLTGEventListener() {
			//			public void processEvent(LTGEvent e) {
			//				updater.tagsFullInit(e);
			//			}
			//		});
			//		eh.registerHandler("tags_grid_init_r", new SingleChatLTGEventListener() {
			//			public void processEvent(LTGEvent e) {
			//				updater.tagsGridInit(e);
			//			}
			//		});	

			//init
			//		eh.registerHandler("images_full_init_r", new SingleChatLTGEventListener() {
			//			public void processEvent(LTGEvent e) {
			//				updater.imageFullInit(e);
			//			}
			//		});	
			//		eh.registerHandler("images_grid_init_r", new SingleChatLTGEventListener() {
			//			public void processEvent(LTGEvent e) {
			//				updater.imageGridInit(e);
			//			}
			//		});	
			
			eh.runAsynchronously();
			startTime = millis();
			startTimer = true;
		}
	}
	
	public void createObjects(){
		notesFullOur = new NotesFull(this, className, "banner_notes_s.svg");
		numNotesFullOur = new NotesNumberFull(this, className, "banner_no_notes_s.svg");
		wordleCollectiveOur = new WordleFull(this, className, "banner_wordle_s.svg");
		notesGridOur = new NotesGrid(this, className, 3, 3, "banner_notes_s_all.svg");
		numNotesGridOur = new NotesNumberGrid(this, className, 3, 3, "banner_no_notes_s_all.svg");
		wordleGridOur = new WordleGrid(this, className, 3, 3, "banner_wordle_s_all.svg");
		//		scoreFullOur = new ScoreFull(this, className);
		//		imageFullOur = new ImageFull(this, className);	
		//		scoreGridOur = new ScoreGrid(this, className, 3, 3);
		//		imageGridOur = new ImageGrid(this, className, 3, 3);


		notesFullAll = new NotesFull(this, null, "banner_notes_p.svg");
		numNotesFullAll = new NotesNumberFull(this, null, "banner_no_notes_p.svg");
		wordleCollectiveAll = new WordleFull(this, null, "banner_wordle_p.svg");
		notesGridAll = new NotesGrid(this, null, 3, 3, "banner_notes_p_all.svg");
		numNotesGridAll = new NotesNumberGrid(this, null, 3, 3, "banner_no_notes_p_all.svg");
		wordleGridAll = new WordleGrid(this, null, 3, 3, "banner_wordle_p_all.svg");
		//		scoreFullAll = new ScoreFull(this);
		//		imageFullAll = new ImageFull(this, className);	
		//		scoreGridAll = new ScoreGrid(this, 3, 3);
		//		imageGridAll = new ImageGrid(this, 3, 3);	

		//THIS CLASSES
		screens.add(notesFullOur);
		screens.add(numNotesFullOur);
		//		screens.add(scoreFullOur);
		//		screens.add(imageFullOur);
		screens.add(wordleCollectiveOur);
		screens.add(notesGridOur);
		screens.add(numNotesGridOur);
		//		screens.add(scoreGridOur);
		//		screens.add(imageGridOur);
		screens.add(wordleGridOur);

		//ALL CLASSES
		screens.add(notesFullAll);
		screens.add(numNotesFullAll);
		//		screens.add(scoreFullAll);
		//		screens.add(imageFullAll);
		screens.add(wordleCollectiveAll);
		screens.add(notesGridAll);
		screens.add(numNotesGridAll);
		//		screens.add(scoreGridAll);
		//		screens.add(imageGridAll);
		screens.add(wordleGridAll);

	}
}
