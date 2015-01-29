CellSociety Design Document
===================
Callie Mao, Kevin Delgado, Le Qi

Introduction
-------------

The primary goals of the project are to allow a user to input rules and parameters regarding a CA simulation, present the user with a graphical representation of the simulation as it runs, and give the user the ability to start, pause, step-through, and adjust the rate of the simulation. At a high level, the program will parse a user defined XML file containing the rules underlying the simulation, the parameters required by these rules, and the initial conditions. 

The overarching design of the program focuses on two categories of classes: views and controllers. The views use the data contained in the XML file to implement the visual displays and UI elements for user interaction while the controllers specify the algorithms for updating these views through each iteration as well as controlling how the user’s interaction with the UI elements (eg start and pause buttons) affect the progression of the simulation. 

The program is designed to be most flexible where the simulation is actually controlled. In essence, the program should be able to handle any number of simulations due to the openness of the simulation controller for extension in order to handle a wide variety of logic cases. The simulation controller should be closed to modification in the sense that all simulations work via the same overarching rules (ie, checking the state of each cell, checking the state of neighboring cells, and updating the state of each cell based on the state of neighboring cells). The views of the program should not be flexible, because keeping each cell limited to only containing information relevant it current state should sufficiently handle any simulation so long as the controllers are robust and flexible.

----

Overview
-------------

The following chart depicts the relationship between our classes:

![Design Overview](http://i.imgur.com/B5Hy8xJ.jpg)

The following components will be used to build our program:

* **Main**
Calls CellSociety to start the program.
* **CellSociety**
The CellSociety class will be the main class that holds the elements of the game, which would include instances of the UI Elements, the Game Manager class, the SimController class, and the Grid.
* **Grid**
	The Grid will be a JavaFX GridPane whose size is determined by the XML file (as parsed by the GameManager). It will have public methods to manipulate the cells inside it via the GameManager
* **Cell**
	The Cell will be closed to the grid but the grid will be able to access and update the cell’s state depending on the results dictated by the controller classes. The cell class itself will be designed for all visual elements of the cell as well as the cell’s state. The cell object extends the JavaFX rectangle class and holds the state of the cell as a CellState object and the color of the cell as a Color object. It should have methods that are able to return the state of the cell, as well as methods that can set the state for manipulation by other functions.  
* **CellState** 
	The CellState object will be the minimum. The CellState superclass will have fields myColor (the color of the cell in the Grid) and myLabel (a String that is the minimum a state of a cell in CA can be, such as “fire”, “shark”, or “live”). It will implement getColor() and getLabel. Some game rules require the state of a cell to hold more than just its label though, such as amount of energy or time until reproduction. The states are subclasses of CellState, they are listed below and are gone over in the design details section.
	* SharkState
	* FishState
* **UI elements**
	The UIElements will provide features that allow the user to start, pause, step through, stop, set frame rate, and upload a new XML file. The UIElements will interact with the GridView class to update the game. 
	* Speed (slider or text input)
	* Upload New XML
	* Button (from JavaFX)
		* Start
		* Pause
		* Step
* **XMLParser**
	XML parser will take in a filepath to an XML file to parse it in order to set the initial state of the cells on the grid as well as well as provide parameters for the controller classes’ algorithms. It will call constructors in the appropriate controllers and set parameters/values based on numbers/values passed in from the XML formatting. 
* **GameManager**
	 GameManager sets up the grid, calls the XMLParser class to parse the XML file, and updates the grid. In updating the grid, it will call SimController to access algorithms from the controller to update cells to their new states. When the play button is pressed it calls SimController.update() at the rate specified by the user. When the pause button is pressed it ceases calling SimController.update(). Each time the step button is pressed, SimController.update() is called a single time. When the UploadXML button is pressed, the GameManager ceases calling SimController.update() (if the simulation is playing) allows the user to input a new XML file. It calls the XML parsing object to determine the class fields of the GameManager. The fields of GameManager include, the SimController (ie. which specific implementation of it).
* **SimController**
The SimController abstract class handles the underlying logic of the simulation. It takes in the grid object and simulation parameters object as arguments to its constructor. It has an update() method that instantiates a new grid and loops through every cell in the grid and for each cell and does the following:
	* gets the state of the cell (cellState) by calling the method Cell.getState();
	* gets a list of adjacent cells by implementing and calling  List<Cell> hood = getHood(Cell, Grid)
	* gets the state of the cell’s neighborhood (hoodState) by calling the abstract method getHoodState(hood)
	* defines the updated state of the cell by calling the abstract method newState(cellState, hoodState) and sets the corresponding cell in the newly instantiated grid to be
The last thing the update() method does after iterating through each cell on the grid is to call method updateGrid() such that the Grid in the scene is replaced by the new Grid with updated cells.

	At a high level each of the four controllers below extend SimController and implement it’s abstract methods getHood(Cell, Grid) and newState(cellState, hoodState). How these abstract methods are implemented are gone over in detail in the Design Details section below
	* Fire Controller
	* Wa-Tor Controller
	* Segregation Controller
	* Life Controller

----
User Interface
----
In the scene, the user will be able to see the grid in place with all of the specific cells in any given state. The user will also be able to see multiple buttons that it can click in order to change the state of the simulation, including reset, start, pause/play in order to effectively manipulate the state of the program. An example can be seen in the picture below.

![UI Design](http://i.imgur.com/ifO0B9R.jpg?1)

When the start button is clicked, the simulation should start and automatically update its cells based on those cell values. When the play/pause button is clicked, the simulation should immediately stop in whichever state it is currently in. The view and state of the cells should no longer change. When the play/pause button is pressed again, the cell states should continue to update, and the scene should then change accordingly as well. Pressing the reset button at anytime should then reinitialize the state of the program, and restore all of the cells to its corresponding states, which should update the view as well to reflect that.

A speed textbox will allow the user to select how many frames per second to play the animation at. If the user enters a negative value, an error message should appear at the bottom, specifying the problem and asking the user to fix it. If a number above 200 is entered, a similar error message is reported. An alternative to this could be a speed slider that limits the numbers that the user 

If the initial XML file does not follow the format that we specify, a message should be displayed to the user saying that the format was wrong as well.At this point, the simulations should not run and the program should not be active because no information can be represented.

---

Design Details 
----
A broader overview of the classes outlined in the Overview is given below.

* **CellSociety** The CellSociety class functions as the view of the entire simulation. 
* **Grid** (Fully detailed in Overview)
* **Cell** (Fully detailed in Overview)
* **CellState**
	* **FishState** Has field reproductionTime
		* **SharkState** Extends FishState, has field energy
* **UI elements**
	* **Speed** (slider or text input) - a text input will exist to allow the user to select the frames/second to play the animation/simulation at. Doing so will access the CellSociety class and update the frames per second that the animation will play at. 
	* **Upload new XML** - selecting this option pulls up a screen that allows the user to select a different XML file. Doing so will stop the current game, reinitialize it with the new parameters, and play the game once again
	* **Button**
		* Start - Start the animation and allow the CA to progress 
		* Pause - Stop by the CA but maintain all current features/states
		* Step - This button will allow the user to progress through the CA frame by frame. Each click will advance the animation by one frame. 
* **XMLParser**
The XML Parser will read in elements from the XML file and set constructors to the controllers with the specified parameters. The XML file will be passed in in a format similar to the below with specific rules to be implemented later on:

```xml
<init>
	<simName>Fire</simName>
	<author>author</author>
	<xDim>xDimensions</xDim>
	<yDim>yDim</yDim>
</init>

<state>
	<num>numStates</num>
	<state type = “tree”>
		<color>0x000ff</color>
		<rules>rules organized here based on format appropriate with code</rules>
	<state type = “ash”>
		<color>0x00000</color>
		<rules></rules>
	<state type = “fire”>
		<color>0x000ee</color>
		<rules>rules organized here based on format appropriate with code
</rules>
</state>
```

The XMLParser will parse through the file and call constructors/methods from cell and the controller classes to set rules and values (ie. method calls similar to createNewCell(parsedColor, parsedState)) or new FireController(parsedRatio1, parsedTreeProbability, parsedFireProbability), etc.). This class is included in the game design in order to allow for specfic updating interactions involved with the initial setup of the simulation scene and simulation rules. It will contain the following methods:
	* set
	* setCell
<<<<<<< HEAD
* **GameManager**
GameManager will contain the following methods:
	* init(File file){
	//this method will initialize the game scene by 
	calling createControllers();
	calling createGrid();
};

	* createControllers(){
	//this method will interact with the XMLParser class to parse the XML file and set the properties to the revelvant controller
}	

	* createGrid(){
	//this method will create the grid by interacting with the Grid class which holds all specfications for the grid’s attributes and properties
};

	* updateGrid(){
	//this method will use setter methods in the Grid class to reset its state based on the results of running the current algorithms. It will need to have access to the SimController and/or the specific controller subclass in order to determine what changes need to be made. 

	* private void pause(){
	//this method will pause the simulation and stop receiving updates from the SimController. This method will need to interact with the Button class to determine when they have been pressed.
}

	* private void step(){
	//this method will only step through and play one frame of the simulation before pausing it once again. This method will need to interact with the Button class to determine when they have been pressed.
}
	
	* uploadXML(File file){
	//this method will need to interact with the UI Elements and buttons to determine when the Upload XML document has been selected and what filepath has been selected. The buttons will call uploadXML(). This method will then interact with XMLParser and call init with the new file passed in as a parameter}

* **SimController**
The SimController class will be an abstract class as detailed in “Overview”. 

* FireController
	FireController class extends SimController and implements its abstract methods as follows:
	* getHoodState(hood) returns “fire” if the state of any of the cells in hood are fire and returns “notfire” otherwise.
	* newState(cellState, hoodState):
		if cellState is “tree”, hoodState is “fire” and Random().nextInt(100) > probCatch:  return “fire”
else if cellState is “tree” return “tree”
else return “ash”
* WaTorController
	Extends SimController and implements its abstract methods as follows:
	* getHoodState(hood) returns array containing any combination of [“shark”,”fish”, “vacant”] if at least one cell in the neighborhood has the corresponding state.
	* newState(cellState, hoodState):

```
		if cellState[0] = “Shark”: (cellState = [“Shark”, surviveCount, energyCount]
		if hoodState contains “Fish” or “vacant”:
			if cellState[1] == reproduceParam:
				return [“Shark”, 0, startingEnergy]
			else:
				return [“vacant”, null, null]
		else:
			if cellState[2] > 0:
				return [“Shark, cellState[1]++, cellState[2]--]
			else:
				if cellState[1] == reproduceParam:
					return [“Shark”, 0, startingEnergy]
				else: 
					return [“vacant”, null, null]
		if cellState[0] = “Fish”
			if hoodState contains “Shark”:
				return [“Shark”, surviveCount++, energyCount++]
			else if hoodState contains “vacant”:
				return [“vacant”, null, null]
			else:
				return [“Fish”, cellState[1]++, null]
		if cellState[0] = “vacant”
			if hoodState contains “Shark”:
				return  [“Shark”, surviveCount++, enegyCount--]
			else if hoodState contains “Fish”:
				return [“Fish”, surviveCount++, null]
			else:
				return [“vacant”, null, null]
```

* SegController
	Extends SimController and implements abstract methods as follows:
	* getHoodState(hood) returns happy if (number of adjacent cells that are the same as cellState)/(number of occupied adjacent cells) > happyThreshold
	* newState(cellState, hoodState):
```
		if(hoodState == happy):
			return cellState
		else:
			return vacant
```

=======


* **GameManager**
GameManager will contain the following methods
	
    	* init(File file){//this method will initialize the game scene by calling createControllers(); calling createGrid();}

	* createControllers(){//this method will interact with the XMLParser class to parse the XML file and set the properties to the revelvant controller}    

	* createGrid(){//this method will create the grid by interacting with the Grid class which holds all specfications for the grid’s attributes and properties}

    	* updateGrid(){//this method will use setter methods in the Grid class to reset its state based on the results of running the current algorithms. It will need to have access to the SimController and/or the specific controller subclass in order to determine what changes need to be made. 

    	* private void pause(){//this method will pause the simulation and stop receiving updates from the SimController. This method will need to interact with the Button class to determine when they have been pressed.}

    	* private void step(){//this method will only step through and play one frame of the simulation before pausing it once again. This method will need to interact with the Button class to determine when they have been pressed.}
    
    	* uploadXML(File file){//this method will need to interact with the UI Elements and buttons to determine when the Upload XML document has been selected and what filepath has been selected. The buttons will call uploadXML(). This method will then interact with XMLParser and call init with the new file passed in as a parameter}

* **SimController**
    	The SimController class will be an abstract class as detailed in “Overview”. 
	* FireController
FireController class extends SimController and implements its abstract methods as follows:
		* getHoodState(hood) returns “fire” if the state of any of the cells in hood are fire and returns “notfire” otherwise.
		* newState(cellState, hoodState):
if cellState is “tree”, hoodState is “fire” and Random().nextInt(100) > probCatch:  return “fire”
else if cellState is “tree” return “tree”
else return “ash”
	* WaTorController
Extends SimController and implements its abstract methods as follows:
		* getHoodState(hood) returns array containing any combination of [“shark”,”fish”, “vacant”] if at least one cell in the neighborhood has the corresponding state.
		* newState(cellState, hoodState):
	
```pseudocode
if cellState[0] = “Shark”: (cellState = [“Shark”, surviveCount, energyCount]
    if hoodState contains “Fish” or “vacant”:
        if cellState[1] == reproduceParam:
            return [“Shark”, 0, startingEnergy]
        else:
            return [“vacant”, null, null]
    else:
        if cellState[2] > 0:
            return [“Shark, cellState[1]++, cellState[2]--]
        else:
            if cellState[1] == reproduceParam:
                return [“Shark”, 0, startingEnergy]
            else: 
                return [“vacant”, null, null]
if cellState[0] = “Fish”
    if hoodState contains “Shark”:
        return [“Shark”, surviveCount++, energyCount++]
    else if hoodState contains “vacant”:
        return [“vacant”, null, null]
    else:
        return [“Fish”, cellState[1]++, null]
if cellState[0] = “vacant”
if hoodState contains “Shark”:
return  [“Shark”, surviveCount++, enegyCount--]
else if hoodState contains “Fish”:
    return [“Fish”, surviveCount++, null]
else:
    return [“vacant”, null, null]
```

SegController
Extends SimController and implements abstract methods as follows:
	* getHoodState(hood) returns happy if (number of adjacent cells that are the same as cellState)/(number of occupied adjacent cells) > happyThreshold
	* newState(cellState, hoodState):
if(hoodState == happy):
    return cellState
else:
    return vacant
>>>>>>> 746ef3b572660248f08e1fd27caefec6afe03ca2
* LifeController
Extends SimController and implements abstract methods as follows:
	* getHoodState(hood) returns lively if  number of nonvacant elements within hood are within threshold range. Else it returns deadly
	* newState(cellState, hoodState):
<<<<<<< HEAD

```
		if(hoodState == lively):
			return life
		else:
			return vacant
```

Given that there could be updated requirements to this CellSociety project, our number one goal is to make this code extensible so that new changes can be implemented easily. For example, adding a new CA simulation with a new set of rules should be an incredibly easy fix to the program. Likewise, other possible additions like possibly running multiple simulations at once should be very easily implemented. In the design of the SimController class for example, our flexibility can be seen because attention is given to making sure that each individual controller subclass is able to extend the SimController superclass, rather than create separate methods or long "if" trees in order to specify behavior. The FireController, WaTorController, SegController, and LifeController all extend the SimController, which is something that we believe to be good code design.

In addition, our design captures some esscences of abstraction and encapsulation. For example, the class that implements the UIElements will likely have no knowledge of any of the any other classes except for event handlers for buttons and any grid paramters passed in. The UIElements class need not know the implementation of any of the other methods and their implementation, just so long as an arbitrary grid is passed with given states for the cells. Likewise, the GameManager and SimController need not have any idea about how the UIElements presents the view to the user. The two controller classes simply modify the current state of the simulation (frame rate, whether paused, cell states, etc.). It does not assume any knowledge of the presentation of this information. Separating the game flow control's and the user presentation's implementation details from one another makes it so that the code is very extensible, and changes can be made easily. If the two functions were heavily intertwined, then an additional requirement is much more difficult to implement, since dependencies between the functions need to be continously checked and rechecked.

Design Considerations
---

A number of design considerations were discussed at length. First of all, it was necessary to decide whether the Cell object would contain information regarding its neighbors or not. The advantage to this is that the SimController would not need the getHood() method as this would be intrinsic to the Cell. We decided, however to not have the Cell hold information on its neighboring cells though because, the SimController is dependent on the grid regardless (in order to iterate through every cell) and it would just be extraneous information in the Cell object.

Another consideration was whether to parse the XML file in a separate class or within the GameManger class. Putting it in the GameManager class was considered because originally the GameManager had little functionality other than parsing the XML file and calling SimControllers. It made more sense to have GameManager handle all the logic involved with the flow of the game (start, pause, step) and thus it made more sense to parse the XML with a separate XMLParser object.

An additional consideration was where to store the algorithm/rules for changing cell states. One option we considered was placing the code for determining a cell’s state directly within the cell class itself. The class could call update() on itself in order to determine its next state and update its state within the same class. However, several issues arose: the cell would need access to adjacent cells, and each cell would need a unique forms of the algorithms. A TreeCell would have to have an algorithm differentiated from a FireCell, and would require specific algorithms to first be hardcoded into each class before the parameters could be passed in. Instead, we decided a separate group of Controller classes could keep access to adjacent cell within the Grid and GameManager classes (reducing the amount that Cell can access) and also by allowing more generalities (and thus extendable functionality) within the Controllers. 

Team Responsibilities
---

=======
    if(hoodState == lively):
        return life
    else:
        return vacant

---

