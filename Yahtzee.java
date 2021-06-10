import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
/*
 * This program simulates the dice game Yahtzee
 * Authors: Kevin He & Zeeman Shuai
 * Date 6/9/21
 */
public class Yahtzee implements ActionListener {
	//global variables
	int rolledOnce = 0;
	JFrame frame = new JFrame ();
	Container diceContainer = new Container();
	JButton[] diceButtons = new JButton[5];
	ImageIcon[] imageIcons = new ImageIcon[6];
	int[] buttonState = new int[6];
	int[] dieValue = new int[6];
	final int HOT_DIE = 0;
	final int SCORE_DIE = 1;
	final int LOCKED_DIE = 2;
	int scoringMethod = 30;
	Container buttonContainer = new Container();
	Container methodContainer = new Container();
	JButton rollButton = new JButton("Roll");
	JButton lockButton = new JButton("Lock");
	JButton scoreButton = new JButton("Score");
	JButton stopButton = new JButton ("Stop");
	//Adding types of scoring methods
	JRadioButton Ones = new JRadioButton("Ones");
	JRadioButton Twos = new JRadioButton("Twos");
	JRadioButton Threes = new JRadioButton("Threes");
	JRadioButton Fours = new JRadioButton("Fours");
	JRadioButton Fives = new JRadioButton("Fives");
	JRadioButton Sixes = new JRadioButton("Sixes");
	JRadioButton threeOfaKind = new JRadioButton("Three Of a Kind");
	JRadioButton fourOfaKind = new JRadioButton("Four Of a Kind");
	JRadioButton fullHouse = new JRadioButton("Full House");
	JRadioButton smallStraight = new JRadioButton("Small Straight");
	JRadioButton largeStraight = new JRadioButton("Large Straight");
	JRadioButton Yahtzee = new JRadioButton("Yahtzee");
	JRadioButton Chance = new JRadioButton("Chance");
	
	/*JButton Ones = new JButton("Ones");
	JButton Twos = new JButton("Twos");
	JButton Threes = new JButton("Threes");
	JButton Fours = new JButton("Fours");
	JButton Fives = new JButton("Fives");
	JButton Sixes = new JButton("Sixes");
	JButton threeOfaKind = new JButton("Three Of a Kind");
	JButton fourOfaKind = new JButton("Four Of a Kind");
	JButton fullHouse = new JButton("Full House");
	JButton smallStraight = new JButton("Small Straight");
	JButton largeStraight = new JButton("Large Straight");
	JButton Yahtzee = new JButton("Yahtzee");
	JButton Chance = new JButton("Chance");*/
	
	
	//Tracks the scoring
	Container labelContainer = new Container();
	JLabel currentScoreLBL = new JLabel("Current Score: 0");
	JLabel totalScoreLBL = new JLabel("Highest Score: 0");
	JLabel currentRoundLBL = new JLabel("Current Round: 1");
	int currentScore = 0;
	int totalScore = 0;
	int currentRound = 1;
	
	public Yahtzee() {
		//Setting up the GUI
		frame.setSize(600, 600);
		imageIcons[0] = resizeImage("./Dice Images/die_1.png");
		imageIcons[1] = resizeImage("./Dice Images/die_2.png");
		imageIcons[2] = resizeImage("./Dice Images/die_3.png");
		imageIcons[3] = resizeImage("./Dice Images/die_4.png");
		imageIcons[4] = resizeImage("./Dice Images/die_5.png");
		imageIcons[5] = resizeImage("./Dice Images/die_6.png");
		diceContainer.setLayout(new GridLayout(2, 3));
		for (int a = 0; a < diceButtons.length; a++) {
			diceButtons[a] = new JButton ();
			diceButtons[a].setIcon(imageIcons[a]);
			diceButtons[a].setEnabled(false);
			diceButtons[a].addActionListener(this);
			diceButtons[a].setBackground(Color.WHITE);
			diceContainer.add(diceButtons[a]);
		}
		buttonContainer.setLayout(new GridLayout(1, 3));
		buttonContainer.add(rollButton);
		rollButton.addActionListener(this);
		buttonContainer.add(lockButton);
		lockButton.addActionListener(this);
		buttonContainer.add(scoreButton);
		scoreButton.addActionListener(this);
		scoreButton.setEnabled(false);
		buttonContainer.add(stopButton);
		stopButton.addActionListener(this);
		stopButton.setEnabled(false);
		labelContainer.setLayout(new GridLayout(3, 1));
		labelContainer.add(currentScoreLBL);
		labelContainer.add(totalScoreLBL);
		labelContainer.add(currentRoundLBL);
		//Adding action listeners to scoring method buttons
		methodContainer.setLayout(new GridLayout(3, 1));
		methodContainer.add(Ones);
		Ones.addActionListener(this);
		methodContainer.add(Twos);
		Twos.addActionListener(this);
		methodContainer.add(Threes);
		Threes.addActionListener(this);
		methodContainer.add(Fours);
		Fours.addActionListener(this);
		methodContainer.add(Fives);
		Fives.addActionListener(this);
		methodContainer.add(Sixes);
		Sixes.addActionListener(this);
		methodContainer.add(threeOfaKind);
		threeOfaKind.addActionListener(this);
		methodContainer.add(fourOfaKind);
		fourOfaKind.addActionListener(this);
		methodContainer.add(fullHouse);
		fullHouse.addActionListener(this);
		methodContainer.add(smallStraight);
		smallStraight.addActionListener(this);
		methodContainer.add(largeStraight);
		largeStraight.addActionListener(this);
		methodContainer.add(Yahtzee);
		Yahtzee.addActionListener(this);
		methodContainer.add(Chance);
		Chance.addActionListener(this);
		//Setting up the frame layout 
		frame.setLayout(new BorderLayout());
		frame.add(diceContainer, BorderLayout.CENTER);
		frame.add(buttonContainer, BorderLayout.NORTH);
		frame.add(labelContainer, BorderLayout.EAST);
		frame.add(methodContainer, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public ImageIcon resizeImage(String path) { //Method to resize dice images
		ImageIcon imageIcon = new ImageIcon(path); //Loads the image to a ImageIcon
		Image image = imageIcon.getImage(); //Returns image
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); //Scales image
		imageIcon = new ImageIcon(newimg);  //Transforms it back
		return imageIcon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {//System to track which scoring method was selected
		//if you have time then make all of radio buttons unset when one is clicked
		
		if (e.getSource().equals(Ones)) {
			scoringMethod = 0;
		}
		if (e.getSource().equals(Twos)) {
			scoringMethod = 1;

		}
		if (e.getSource().equals(Threes)) {
			scoringMethod = 2;

		}
		if (e.getSource().equals(Fours)) {
			scoringMethod = 3;

		}
		if (e.getSource().equals(Fives)) {
			scoringMethod = 4;

		}
		if (e.getSource().equals(Sixes)) {
			scoringMethod = 5;

		}
		if (e.getSource().equals(threeOfaKind)) {
			scoringMethod = 6;

		}		
		if (e.getSource().equals(fourOfaKind)) {
			scoringMethod = 7;

		}
		if (e.getSource().equals(fullHouse)) {
			scoringMethod = 8;

		}
		if (e.getSource().equals(smallStraight)) {
			scoringMethod = 9;

		}
		if (e.getSource().equals(largeStraight)) {
			scoringMethod = 10;

		}
		if (e.getSource().equals(Yahtzee)) {
			scoringMethod = 11;

		}
		if (e.getSource().equals(Chance)) {
			scoringMethod = 12;

		}
		if (e.getSource().equals(rollButton)) { //What happens when the roll button is pressed
			if(rolledOnce != 2) {
				for (int a = 0; a < diceButtons.length; a++) {
					if (buttonState[a] != LOCKED_DIE) {
						int choice = (int)(Math.random() * 6);
						dieValue[a] = choice;
						diceButtons[a].setIcon(imageIcons[choice]);
						diceButtons[a].setEnabled(true);
						scoreButton.setEnabled(true);
						stopButton.setEnabled(true);
					}
				}
				rolledOnce++;
			}
			if(rolledOnce == 2) {
				rollButton.setEnabled(false);
			}
		}
		else if (e.getSource().equals(lockButton)) {//What happens when lock button is pressed
			for (int a = 0; a < diceButtons.length; a++) {
				if (buttonState[a] == SCORE_DIE) {//If the selected dice are scored, the buttons will turn blue and will become locked
					buttonState[a] = LOCKED_DIE;
					diceButtons[a].setBackground(Color.BLUE); 
				}
				diceButtons[a].setEnabled(false);
			}
			int lockedCount = 0;
			for (int a = 0; a < diceButtons.length; a++) {
				if (buttonState[a] == LOCKED_DIE) {
					lockedCount++;
				}
			}
		}
		else if (e.getSource().equals(scoreButton)) {//What happens when the score button is pressed
			if(scoringMethod != 30) {
				int[] valueCount = new int[7]; //Stores the number of each die at the index
				for (int a = 0; a < diceButtons.length; a++) {
					//if (buttonState[a] == SCORE_DIE) {
						valueCount[dieValue[a]+1]++;
						/*
						 * 0
						 * #of ones selected
						 * #of twos selected
						 * #of threes selected
						 * #of fours selected
						 * #of fives selected
						 * #of sixes selected
						 */
					//}
				}
				//Scoring methods
				if(scoringMethod == 0) {//checking for ones
					currentScore += valueCount[1] * 1;
					Ones.setEnabled(false);
				}
				if(scoringMethod == 1) {//checking for twos
					currentScore += valueCount[2] * 2;
					Twos.setEnabled(false);
	
				}
				if(scoringMethod == 2) {//checking for threes
					currentScore += valueCount[3] * 3;
					Threes.setEnabled(false);
	
				}
				if(scoringMethod == 3) {//checking for fours
					currentScore += valueCount[4] * 4;
					Fours.setEnabled(false);
	
				}
				if(scoringMethod == 4) {//checking for fives
					currentScore += valueCount[5] * 5;
					Fives.setEnabled(false);
	
				}			
				if(scoringMethod == 5) { //checking for sixes
					currentScore += valueCount[6] * 6;
					Sixes.setEnabled(false);
	
				}
				if(scoringMethod == 6) { //checking for three of a kind
					if (valueCount[1] == 3 || valueCount[2] == 3 || valueCount[3] == 3 || valueCount[4] == 3 || valueCount[5] == 3 || valueCount[6] == 3) {
						currentScore += valueCount[1] * 1 + valueCount[2] *2 + valueCount[3] *3 + valueCount[4] *4 + valueCount[5] *5 + valueCount[6] * 6;	
						System.out.println("You got a three of a kind!");
					}
					threeOfaKind.setEnabled(false);
				}
				if(scoringMethod == 7) {//checking for four of a kind
					if (valueCount[1] == 4 || valueCount[2] == 4 || valueCount[3] == 4 || valueCount[4] == 4 || valueCount[5] == 4 || valueCount[6] == 4) {
						currentScore += valueCount[1] * 1 + valueCount[2] *2 + valueCount[3] *3 + valueCount[4] *4 + valueCount[5] *5 + valueCount[6] * 6;
						System.out.println("You got a four of a kind!");
					}
					fourOfaKind.setEnabled(false);
	
				}
				if(scoringMethod == 8) {//checking for full house
					if (valueCount[1] == 3 && valueCount[2] == 2 || valueCount[1] == 3 && valueCount[3] == 2 || valueCount[1] == 3 && valueCount[4] == 2 
							|| valueCount[1] == 3 && valueCount[5] == 2 || valueCount[1] == 3 && valueCount[6] == 2) {
						currentScore += 25;
						System.out.println("Full house of ones!");
					}
					if (valueCount[2] == 3 && valueCount[1] == 2 || valueCount[2] == 3 && valueCount[3] == 2 || valueCount[2] == 3 && valueCount[4] == 2 
							|| valueCount[2] == 3 && valueCount[5] == 2 || valueCount [2] == 3 && valueCount[6] == 2) {
						currentScore += 25;
						System.out.println("Full house of twos!");
					}
					if (valueCount[3] == 3 && valueCount[1] == 2 || valueCount[3] == 3 && valueCount[2] == 2 || valueCount[3] == 3 && valueCount[4] == 2 
							|| valueCount[3] == 3 && valueCount[5] == 2 || valueCount [3] == 3 && valueCount[6] == 2) {
						currentScore += 25;
						System.out.println("Full house of threes!");
					}
					if (valueCount[4] == 3 && valueCount[1] == 2 || valueCount[4] == 3 && valueCount[2] == 2 || valueCount[4] == 3 && valueCount[3] == 2 
							|| valueCount[4] == 3 && valueCount[5] == 2 || valueCount [4] == 3 && valueCount[6] == 2) {
						currentScore += 25;
						System.out.println("Full house of fours!");
					}
					if (valueCount[5] == 3 && valueCount[1] == 2 || valueCount[5] == 3 && valueCount[2] == 2 || valueCount[5] == 3 && valueCount[3] == 2 
							|| valueCount[5] == 3 && valueCount[4] == 2 || valueCount [5] == 3 && valueCount[6] == 2) {
						currentScore += 25;
						System.out.println("Full hosue of fives!");
					}
					if (valueCount[6] == 3 && valueCount[1] == 2 || valueCount[6] == 3 && valueCount[2] == 2 || valueCount[6] == 3 && valueCount[3] == 2 
							|| valueCount[6] == 3 && valueCount[4] == 2 || valueCount [6] == 3 && valueCount[5] == 2) {
						currentScore += 25;
						System.out.println("Full hosue of sixes!");
					}
					fullHouse.setEnabled(false);
	
				}
				if(scoringMethod == 9) {//checking for small straight
					smallStraight.setEnabled(false);
					if (valueCount[1] == 1 && valueCount[2] == 1 && valueCount[3] == 1 && valueCount[4] == 1 || 
							valueCount[5] == 1 && valueCount[2] == 1 && valueCount[3] == 1 && valueCount[4] == 1 ||
							valueCount[5] == 1 && valueCount[6] == 1 && valueCount[3] == 1 && valueCount[4] == 1)
						
						currentScore += 30;
	
				}
				if(scoringMethod == 10) {//checking for large straight
					largeStraight.setEnabled(false);
					if (valueCount[1] == 1 && valueCount[2] == 1 && valueCount[3] == 1 && valueCount[4] == 1 && valueCount[5] == 1 ||
							valueCount[6] == 1 && valueCount[2] == 1 && valueCount[3] == 1 && valueCount[4] == 1 && valueCount[5] == 1) {
						currentScore += 40; 
					}
				}
				if(scoringMethod == 11) {//checking for Yahtzee
					if (valueCount[1] == 5 || valueCount[2] == 5 || valueCount[3] == 5 || valueCount[4] == 5 || valueCount[3] == 5 || valueCount[6] == 5) {
						currentScore += 50;
						System.out.println("Yahtzee!");
					}
					Yahtzee.setEnabled(false);
	
				}
				if(scoringMethod == 12) {//checking for chance
					currentScore += valueCount[1] * 1 + valueCount[2] *2 + valueCount[3] *3 + valueCount[4] *4 + valueCount[5] *5 + valueCount[6] * 6;		
					Chance.setEnabled(false);
				}
	
				
				currentScoreLBL.setText("Current Score: " + currentScore); //Tracks the number of points scored after each round
				for (int a = 0; a < diceButtons.length; a++) {
					if (buttonState[a] == SCORE_DIE) {//If the selected dice are scored, the buttons will turn blue and will become locked
						buttonState[a] = LOCKED_DIE;
						diceButtons[a].setBackground(Color.BLUE); 
					}
					diceButtons[a].setEnabled(false);
				}
				int lockedCount = 0;
				for (int a = 0; a < diceButtons.length; a++) {
					if (buttonState[a] == LOCKED_DIE) {
						lockedCount++;
					}
				}
				if (lockedCount == 6) {
					for ( int a = 0; a < diceButtons.length; a++) {
						buttonState[a] = HOT_DIE;
						diceButtons[a].setBackground(Color.WHITE);
					}
				}
				
				//reset all stuff
				scoringMethod = 30;
				rolledOnce = 0;
				if(currentRound == 13) {
					endGame();
				}
				currentRound++;
				currentRoundLBL.setText("Current Round: " + currentRound);
				
				resetDice();
				
				rollButton.setEnabled(true);
				scoreButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
			else {
				System.out.println("No Scoring Method!!!");
			}
		}
		else if (e.getSource().equals(stopButton)) {
			endGame();
		}
		else {
			for (int a = 0; a < diceButtons.length; a++) {
				if (e.getSource().equals(diceButtons[a])) {
					if (buttonState[a] == HOT_DIE) {
						diceButtons[a].setBackground(Color.RED);
						buttonState[a] = SCORE_DIE;//set to locked
					}
					else {
						diceButtons[a].setBackground(Color.WHITE);
						buttonState[a] = HOT_DIE;
						}
					}
				}
			}
		}

	void resetDice() {
		for (int a = 0; a < diceButtons.length; a++) {
			diceButtons[a].setEnabled(false);
			buttonState[a] = HOT_DIE;
			diceButtons[a].setBackground(Color.WHITE);
		}
		rolledOnce = 0;
		rollButton.setEnabled(true);
		scoreButton.setEnabled(false);
		stopButton.setEnabled(false);
	}
	
	public void endGame() {
		
		//Create a popup window that says the final score
		
		if(totalScore < currentScore) {
			totalScore = currentScore;
			//make the popup say high score
		}
		currentScore = 0; 
		currentScoreLBL.setText("Current Score: " + currentScore);
		totalScoreLBL.setText("Total Score: " + totalScore);
		currentRound = 0;
		currentRoundLBL.setText("Current Round: " + currentRound);
		resetDice();
		//set all the radio buttons to enabled
		Ones.setEnabled(true);
		Twos.setEnabled(true);
		Threes.setEnabled(true);
		Fours.setEnabled(true);
		Fives.setEnabled(true);
		Sixes.setEnabled(true);
		threeOfaKind.setEnabled(true);
		fourOfaKind.setEnabled(true);
		fullHouse.setEnabled(true);
		smallStraight.setEnabled(true);
		largeStraight.setEnabled(true);
		Yahtzee.setEnabled(true);
		Chance.setEnabled(true);
	}
	
	public static void main(String[] args) {
		new Yahtzee(); 
	}

}
