/**
*  @author	John McCain <johnm.freestate@gmail.com>
*  @author
*  @version 0.2 &nbsp;
*  @since 2016-02-04
*
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Control extends JFrame implements ActionListener
{
	//Used for pop up messages
	JFrame popUpFrame;

	//The clock display panel
	Display displayPanel;

	/**
	*	Initializes the GUI and necessary variables with appropriate settings.
	*	
	*/
	public Control()
	{
		super("Hyperclock 2000");
		
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

		/******INTERFACE CODE******/

		//buttonPanel will hold all of the buttons
		//the GridLayout will arange the buttons in a 2 row grid, 0 means it can have any number of columns
		JPanel buttonPanel = new JPanel( new GridLayout(2, 0) );
		buttonPanel.setPreferredSize( new Dimension(1500, 150) );

		//Create the buttons
		//The string "Test1" or "Test2" will be the text displayed on the button and the string used to identify the button in the actionPerformed function
	    JButton buttonTest1 = new JButton("Test1"); 
	    JButton buttonTest2 = new JButton("Test2");
	    
	    //Add actionListeners for the buttons
	    buttonTest1.addActionListener(this);
	    buttonTest2.addActionListener(this);
	    
	    //Add the buttons to our buttonPanel
	    buttonPanel.add(buttonTest1);
	    buttonPanel.add(buttonTest2);

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
			case "Test1":
				displayMessage("You pressed Test1");
				break;
			case "Test2":
				displayMessage("You pressed Test2");
				break;
			default:
				displayMessage("ERROR: Unrecognized event");
				break;
		}
	}

	/**
	*	Calls setDisplay in displayPanel
	*	@param digits a four digit number to be displayed on the clock face
	*	@param colon true if colon should be displayed, false if it should not
	*	@param msg the message to be displayed next to the digit display (am/pm)
	*/
	public void setDisplay(int[] digits, boolean colon, String msg)
	{
		displayPanel.setDisplay(digits, colon, msg);
	}

	/**
	*	Displays a message popup for errors or other messages.
	*	@param msg The string to be displayed in the dialogue window
	*/
	public void displayMessage(String msg)
	{
		JOptionPane.showMessageDialog(popUpFrame, msg);
	}
}