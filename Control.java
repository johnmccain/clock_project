/**
*  @author	John McCain <johnm.freestate@gmail.com>
*  @author	Alan Wang <ultimate801@gmail.com>
*  @version 1.0 &nbsp;
*  @since 2016-02-14
*
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

/**
*	A custom JFrame that contains JPanels which display the time information passed into this class and gives input from the user to CoolClockTimer.
*/
public class Control extends JFrame implements ActionListener
{
	/**
	*	JFrame used for pop up messages.
	*/
	JFrame popUpFrame;

	/**
	*	The clock digit display JPanel
	*/
	Display displayPanel;

	/**
	*	Instance of the CoolClockTimer for passing UI interaction information
	*/
	CoolClockTimer coolClock;

	/**
	*	Constructor.  Initializes the GUI, GUI components, and necessary variables with appropriate settings.
	*	@param myClock an instance of the CoolClockTimer that created this Control object, used for passing UI information (button presses) to the clock controller
	*/
	public Control(CoolClockTimer myClock)
	{
		super("Hyperclock 2000");

		//Set coolClock
		coolClock = myClock;
		
		//Set layout (how panels are organized within frame) 
		//Alan, feel free to change this if need be
		setLayout( new FlowLayout() );

		//Sets the size, width px X height px
		//Note: this includes the ~40px top bar
		setSize(1500, 600);

		//Make the background black
		this.getContentPane().setBackground(Color.BLACK);

		//Puts the window in the middle of the screen
		setLocationRelativeTo(null);
		
		//Make the window not resizable
		setResizable(false);
		
		//Exit the application when the "X" button is pressed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayPanel = new Display();
		this.getContentPane().add(displayPanel);

		//Button Interface Code

		//buttonPanel will hold all of the buttons
		//the GridLayout will arange the buttons in a 2 row grid, 0 means it can have any number of columns
		JPanel buttonPanel = new JPanel( new GridLayout(2, 0) );
		buttonPanel.setPreferredSize( new Dimension(1500, 150) );

		//Create the buttons
	    //Top Row
	    JButton addHour = new JButton("Hour +"); 
	    JButton addMinute = new JButton("Minutes +");
	    JButton addSecond = new JButton("Seconds +");
	    JButton toggleFormat = new JButton("Toggle 24 hour/12 hour format");
	    //Bottom Row
	    JButton subHour = new JButton("Hour -");
	    JButton subMinute = new JButton("Minutes -");
	    JButton subSecond = new JButton("Seconds -");
	    JButton togglePause = new JButton("Start/Pause");
	    
	    //Add actionListeners for the buttons
	    addHour.addActionListener(this);
	    addMinute.addActionListener(this);
		addSecond.addActionListener(this);
	    toggleFormat.addActionListener(this);

	    subHour.addActionListener(this);
	    subMinute.addActionListener(this);
	    subSecond.addActionListener(this);
	    togglePause.addActionListener(this);
	    
	    //Add the buttons to our buttonPanel
	    buttonPanel.add(addHour);
	    buttonPanel.add(addMinute);
	    buttonPanel.add(addSecond);
	    buttonPanel.add(toggleFormat);

	    buttonPanel.add(subHour);
	    buttonPanel.add(subMinute);
	   	buttonPanel.add(subSecond);
	    buttonPanel.add(togglePause);

	    //Add the buttonPanel to our JFrame
	    this.getContentPane().add(buttonPanel);

	    //Reveal ourselves to the world
		setVisible(true);

		//For pop up messages
		popUpFrame = new JFrame("Dialogue");
	}

	/**
	*	Handles GUI event responses such as button presses.
	*	@param	event the event that has occurred
	*	@post	the appropriate response to the event will be executed
	*/
	public void actionPerformed(ActionEvent event)
	{
		switch(event.getActionCommand())
		{
			case "Hour +":
				coolClock.addTime(3600);
				break;
			case "Minutes +":
                                coolClock.addTime(60);
				break;
                        case "Seconds +":
                                coolClock.addTime(1);
                                break;
                        case "Start/Pause":
                                coolClock.togglePause();
                                break;
                        case "Toggle 24 hour/12 hour format":
                                coolClock.toggleHourFormat();
                                break;
                        case "Hour -":
                                coolClock.addTime(-3600);
                                break;
                        case "Minutes -":
                                coolClock.addTime(-60);
                                break;
                        case "Seconds -":
                                coolClock.addTime(-1);
                                break;                            
			default:
				displayMessage("ERROR: Unrecognized event");
				break;
		}
	}

	/**
	*	Calls setDisplay in displayPanel
	*	@param 	digits a four digit number to be displayed on the clock face
	*	@param 	colon true if colon should be displayed, false if it should not
	*	@param 	msg the message to be displayed next to the digit display (am/pm)
	*/
	public void setDisplay(int[] digits, boolean colon, String msg)
	{
		displayPanel.setDisplay(digits, colon, msg);
	}

	/**
	*	Displays a message popup for errors or other messages.
	*	@param 	msg The string to be displayed in the dialogue window
	*/
	public void displayMessage(String msg)
	{
		JOptionPane.showMessageDialog(popUpFrame, msg);
	}
}