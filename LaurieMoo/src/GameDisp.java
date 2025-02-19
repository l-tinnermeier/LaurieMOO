import java.awt.EventQueue;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

import game.GameLogic;
import utilites.Styles;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/*
 * The main class to generate the visuals for the code
 * @author Luke Tinnermeier
 * @date 12/2/2025
 * @version 1.0
 * @file GameDisp.java
 */

public class GameDisp {

	private JFrame frame;
	
    private JLabel aaaaa = new JLabel("9999");
    private JDialog dialog = new JDialog(frame, "Simple Dialog", true);
	private JPanel mainMenuPanel = new JPanel();
	private JPanel gamePanel = new JPanel();
	private JPanel fadePanel = new JPanel();
	
	private JLabel startBtnTextLabel = new JLabel("New Game");
    private JLabel numMOOSDisp = new JLabel("0");
    private JLabel numMoosDisp = new JLabel("0");
    private JLabel numberOutputDisp = new JLabel("Output");
    
    private JLabel cowOneOutput = new JLabel("");
    private JLabel cowTwoOutput = new JLabel("");
    private JLabel cowThreeOutput = new JLabel("");
    private JLabel cowFourOutput = new JLabel("");
    private JLabel winLossLabel = new JLabel("");
    
    private JTextArea guessesContainer = new JTextArea();
    private JButton playAgainBtn = new JButton("Play Again?");
    private JLabel guessNumberLabel = new JLabel("0");
    private JLabel guessLabel = new JLabel("Guesses");
    private JPanel guessPanel = new JPanel();
    
	private JTextField userGuessInput;
	private JLabel userGuessInputLabel = new JLabel("Enter a number: ");
	
	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	ImageIcon backgroundImagePath = new ImageIcon(getClass().getResource("/resources/testImage.png"));

	

	/**
	 * Launches the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameDisp window = new GameDisp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
	
	/**
	 * GameDisp()
	 * Runs the code to set up the UI and UI functionality
	 * @throws IOException 
	 */
	public GameDisp() {
		initializeContents();
		initializeFunctionality();
		
		GameLogic.addMoosDisp(cowOneOutput, cowTwoOutput, cowThreeOutput, cowFourOutput);
		GameLogic.setupGame(numberOutputDisp);
	}
	
	/*
	 * initializeFunctionality()
	 * Sets up the click & input events for the project
	 */
    private void initializeFunctionality() {
		startBtnTextLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Runnable task = () -> { 
					Styles.fadeOutJPanel(fadePanel, frame, 15, 50);
					
					mainMenuPanel.setVisible(false);
					gamePanel.setVisible(true);
				};
				
				fadePanel.setVisible(true);
				fadePanel.setBackground(new Color(0, 0, 0, 0));
				
				Styles.fadeInJPanel(fadePanel, frame, 15, 50);
				
				scheduler.schedule(task, 2, TimeUnit.SECONDS);
				scheduler.shutdown();
				//Styles.fadeOutJPanel(fadePanel, frame, 20);
				//fadePanel.repaint();
			}
		});
		
		/*
		 * Adds the functionality to the input label, allowing it to parse the answer, check the answer, and add the guess to the frame
		 */
        userGuessInput.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		// numberOutputDisp
        		
        		if (GameLogic.parseUserAnswer(userGuessInput, numberOutputDisp)) {
        			GameLogic.checkUserAnswer(numMOOSDisp, numMoosDisp, winLossLabel, playAgainBtn, userGuessInputLabel, userGuessInput, frame, dialog, aaaaa);
            		GameLogic.addGuess(frame, guessesContainer, guessNumberLabel);
        		}
        		
        		userGuessInput.setText("");
        	}
        });
        
        /*
         * Adds functionality to the button to play again, and calls the appropriate function upon clicking it
         */
        playAgainBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GameLogic.playAgain(playAgainBtn, numberOutputDisp, guessesContainer, userGuessInputLabel, userGuessInput, winLossLabel, frame);
        	}
        });
        
    }
    
	/**
	 * initalizeContents()
	 * Initialize the contents of the frame.
	 */
	private void initializeContents() {
		frame = new JFrame();
		frame.setFont(new Font("Monocraft", Font.PLAIN, 12));
		frame.setBounds(100, 100, 1920, 1080);
		
		/*
		GraphicsDevice graphics = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		
		if (graphics.isFullScreenSupported() && setFullscreen) {
			frame.setUndecorated(true);
			graphics.setFullScreenWindow(frame);
		} else {
			System.err.println("Full screen not supported");


		} */
		
		
		// 1920 x 1080
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		fadePanel.setBackground(new Color(0, 0, 0));
		fadePanel.setVisible(false);
		frame.getContentPane().add(fadePanel, "name_360271036785375");
		
		mainMenuPanel.setBackground(new Color(146, 146, 146));
		frame.getContentPane().add(mainMenuPanel, "name_354191416711166");
		mainMenuPanel.setVisible(true);
		mainMenuPanel.setLayout(null);
		
		JPanel startBtnPanel = new JPanel();
		startBtnPanel.setBackground(new Color(0, 0, 0, 0));
		startBtnPanel.setBounds(755, 488, 191, 61);
		mainMenuPanel.add(startBtnPanel);
		startBtnPanel.setLayout(null);
		
		startBtnTextLabel.setForeground(new Color(254, 255, 255));
		startBtnTextLabel.setFont(new Font("Monocraft", Font.PLAIN, 28));
		startBtnTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startBtnTextLabel.setBounds(6, 8, 179, 44);
		
		startBtnPanel.add(startBtnTextLabel);
		
		JLabel startBtnImageLabel = new JLabel("New label");
		startBtnImageLabel.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/stoneBtnBordered.png")));
		startBtnImageLabel.setBounds(0, -7, 191, 75);
		startBtnPanel.add(startBtnImageLabel);
        
		JLabel gameTitleHeaderLabel = new JLabel("The Infamous Game");
		gameTitleHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitleHeaderLabel.setFont(new Font("Monocraft", Font.PLAIN, 64));
		gameTitleHeaderLabel.setBounds(450, 90, 802, 216);
		mainMenuPanel.add(gameTitleHeaderLabel);
		
		JLabel gameTitleMidLabel = new JLabel("OF");
		gameTitleMidLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitleMidLabel.setFont(new Font("Monocraft", Font.PLAIN, 48));
		gameTitleMidLabel.setBounds(759, 263, 183, 79);
		mainMenuPanel.add(gameTitleMidLabel);
		
		JLabel gameTitleBottomLabel = new JLabel("LaurieMOO");
		gameTitleBottomLabel.setFont(new Font("Monocraft", Font.BOLD, 72));
		gameTitleBottomLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitleBottomLabel.setBounds(590, 354, 521, 61);
		mainMenuPanel.add(gameTitleBottomLabel);
		
		JPanel settingsPanel = new JPanel();
		settingsPanel.setBackground(new Color(0, 150, 255));
		frame.getContentPane().add(settingsPanel, "name_354267508725500");
		
		JLabel background = new JLabel();
		background.setIcon(backgroundImagePath);
        background.setBounds(0, 0, 1920, 1080);
        mainMenuPanel.add(background);
        

        gamePanel.setBackground(new Color(0, 142, 0));
        frame.getContentPane().add(gamePanel, "name_354217286523208");
        gamePanel.setLayout(null);
        

        numMOOSDisp.setHorizontalAlignment(SwingConstants.CENTER);
        numMOOSDisp.setFont(new Font("Monocraft", Font.PLAIN, 24));
        numMOOSDisp.setBounds(0, 0, 200, 75);
        gamePanel.add(numMOOSDisp);
        

        numMoosDisp.setFont(new Font("Monocraft", Font.PLAIN, 24));
        numMoosDisp.setHorizontalAlignment(SwingConstants.CENTER);
        numMoosDisp.setBounds(0, 87, 200, 75);
        gamePanel.add(numMoosDisp);
        winLossLabel.setBounds(465, 377, 756, 75);
        gamePanel.add(winLossLabel);
        
        winLossLabel.setFont(new Font("Monocraft", Font.PLAIN, 24));
        winLossLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JPanel bottomNavPanel = new JPanel();
        bottomNavPanel.setBounds(52, 825, 890, 146);
        gamePanel.add(bottomNavPanel);
        bottomNavPanel.setLayout(null);
        numberOutputDisp.setBounds(52, 174, 96, 37);
        gamePanel.add(numberOutputDisp);
        
        numberOutputDisp.setFont(new Font("Monocraft", Font.PLAIN, 24));
        numberOutputDisp.setHorizontalAlignment(SwingConstants.CENTER);
        
        userGuessInput = new JTextField();
        userGuessInput.setBounds(367, 35, 295, 75);
        bottomNavPanel.add(userGuessInput);
        userGuessInput.setFont(new Font("Monocraft", Font.PLAIN, 24));
        userGuessInput.setColumns(10);
        
        userGuessInputLabel.setFont(new Font("Monocraft", Font.PLAIN, 24));
        userGuessInputLabel.setBounds(87, 35, 256, 75);
        bottomNavPanel.add(userGuessInputLabel);
        
        playAgainBtn.setVisible(false);
        playAgainBtn.setFont(new Font("Monocraft", Font.BOLD, 18));
        playAgainBtn.setBounds(361, 44, 168, 58);
        bottomNavPanel.add(playAgainBtn);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/stoneBorderLong.png")));
        lblNewLabel_1.setBounds(0, 0, 890, 150);
        bottomNavPanel.add(lblNewLabel_1);
        
        guessPanel.setBounds(1450, 50, 225, 300);
        gamePanel.add(guessPanel);
        guessPanel.setLayout(null);
        
        guessNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        guessNumberLabel.setFont(new Font("Monocraft", Font.PLAIN, 24));
        guessNumberLabel.setBounds(44, 81, 136, 39);
        guessPanel.add(guessNumberLabel);
        
        guessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        guessLabel.setFont(new Font("Monocraft", Font.BOLD, 32));
        guessLabel.setBounds(6, 5, 213, 64);
        guessPanel.add(guessLabel);
        
        JScrollPane guessPanelScrollPane = new JScrollPane();
        guessPanelScrollPane.setBounds(34, 116, 157, 167);
        guessPanel.add(guessPanelScrollPane);
        guessesContainer.setLocation(27, 0);
        guessesContainer.setFont(new Font("Monocraft", Font.PLAIN, 24));
        guessesContainer.setEditable(false);
        
        guessPanelScrollPane.setViewportView(guessesContainer);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/stoneBorderLarge.png")));
        lblNewLabel.setBounds(0, 0, 225, 300);
        guessPanel.add(lblNewLabel);
        
        JPanel cowOne = new JPanel();
        cowOne.setBounds(0, 445, 482, 328);
        cowOne.setBackground(new Color(0, 0, 0, 0));
        gamePanel.add(cowOne);
        cowOne.setLayout(null);
        

        cowOneOutput.setFont(new Font("Monocraft", Font.PLAIN, 24));
        cowOneOutput.setHorizontalAlignment(SwingConstants.CENTER);
        cowOneOutput.setBounds(175, 87, 131, 53);
        cowOne.add(cowOneOutput);
        
        JLabel cowOneImage = new JLabel("New label");
        cowOneImage.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/cowStandard.png")));
        cowOneImage.setBounds(141, 122, 200, 200);
        cowOne.add(cowOneImage);
        
        JPanel cowTwo = new JPanel();
        cowTwo.setLayout(null);
        cowTwo.setBackground(new Color(0, 0, 0, 0));
        cowTwo.setBounds(645, 498, 482, 328);
        gamePanel.add(cowTwo);
        
        cowTwoOutput.setHorizontalAlignment(SwingConstants.CENTER);
        cowTwoOutput.setFont(new Font("Monocraft", Font.PLAIN, 24));
        cowTwoOutput.setBounds(175, 87, 131, 53);
        cowTwo.add(cowTwoOutput);
        
        JLabel cowTwoImage = new JLabel("New label");
        cowTwoImage.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/cowLarge.png")));
        cowTwoImage.setBounds(141, 122, 200, 200);
        cowTwo.add(cowTwoImage);
        
        JPanel cowThree = new JPanel();
        cowThree.setLayout(null);
        cowThree.setBackground(new Color(0, 0, 0, 0));
        cowThree.setBounds(1262, 570, 482, 328);
        gamePanel.add(cowThree);
        
        cowThreeOutput.setHorizontalAlignment(SwingConstants.CENTER);
        cowThreeOutput.setFont(new Font("Monocraft", Font.PLAIN, 24));
        cowThreeOutput.setBounds(175, 6, 131, 53);
        cowThree.add(cowThreeOutput);
        
        JLabel cowThreeImage = new JLabel("New label");
        cowThreeImage.setBounds(91, 22, 300, 300);
        cowThree.add(cowThreeImage);
        cowThreeImage.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/cowLargeFlipped.png")));
        
        JPanel cowFour = new JPanel();
        cowFour.setBounds(387, 464, 482, 328);
        gamePanel.add(cowFour);
        cowFour.setLayout(null);
        cowFour.setBackground(new Color(0, 0, 0, 0));
        
        cowFourOutput.setHorizontalAlignment(SwingConstants.CENTER);
        cowFourOutput.setFont(new Font("Monocraft", Font.PLAIN, 24));
        cowFourOutput.setBounds(135, 6, 131, 53);
        cowFour.add(cowFourOutput);
        
        JLabel cowFourImage = new JLabel("");
        cowFourImage.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/cowStandardFlipped.png")));
        cowFourImage.setBounds(91, 22, 300, 300);
        cowFour.add(cowFourImage);
        
        JLabel background_1 = new JLabel();
        background_1.setBounds(0, -25, 1920, 1080);
        gamePanel.add(background_1);
        background_1.setIcon(new ImageIcon(GameDisp.class.getResource("/resources/testImage.png")));
        
        //guessesContainer.setBackground(new Color(0, 0, 0, 0));
        guessPanel.setBackground(new Color(0, 0, 0, 0));
        bottomNavPanel.setBackground(new Color(0, 0, 0, 0));
        
        numMOOSDisp.setVisible(false);
        numMoosDisp.setVisible(false);
        numberOutputDisp.setVisible(false);
        

        dialog.setSize(200, 150);
        dialog.setLayout(null);

        // Add a label to the dialog

        dialog.add(aaaaa);
        aaaaa.setBounds(0, 0, 200, 200);
	}
}
