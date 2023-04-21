# Project 4: TIS-100 Game Implementation
### Group Members: Joel Gloetzner, Roshan Subedi, and Aayush Kafle

### Description:
#### This is the project assignment of CS351 class. It is a group project and we designed the implementation of the game 'Tis-100' in JavaFX. The main class for the project is GUI_R.java which contains the drawing of the game area in the GUI. The explanation of the classes implementations can be found below for respective classes.

### GUI_R class:
#### This class is the implementation of the TIS-100 game GUI using JavaFX. It defines a GUI class that handles the drawing of the GUI, calling the Parsers and Interpreters, logics for the buttons, implementation of SiloGUI, and all sorts of drawing stuff along with their logic. The GUI class extends the Application class and overrides its start() method. The GUI is divided into different parts using layouts such as BorderPane, GridPane, and HBox. The game is started, paused, stepped, and stopped using the startButton, pauseStepButton, and stopButton. The startButton adds the instructions to the interpreter and starts the game. The pauseStepButton pauses or steps the game based on its current state. The stopButton stops the game and resets all of the silos. The GUI also displays input and output values in separate text areas using the createInput() and createOutput() methods. The isRunning boolean variable is used to determine the state of the game.

### SiloGUI class:
#### It is the GUI class for the components of Silo, including its arrows, BAK/ACC labels, and all of the Silo GUI implementations and logics. The drawSilo function takes in a list of strings representing the current instructions, the current instruction index, and the current i and j values. It then uses these values to draw the silo's arrows, ACC and BAK labels, and text area. The text area is used to input new instructions for the silo. The drawAccBak function adds the ACC and BAK labels to the GUI and sets their values to the ones currently stored in the Interpreter. Finally, the textAreaInputForNewInstructions function listens for new input in the text area and updates the list of instructions for the silo accordingly.

### Instruction class:
#### The Instruction class is an abstract class that provides the basic implementation for creating instruction nodes. It serves as a base class for all other classes that are used to execute instructions in the siloArea (TextArea) as well as the first commands given to the silos from the console. Each subclass of the Instruction class overrides the execute() function to provide custom functionality depending on the type of instruction being executed.

### Tokenizer_J class:
#### The Tokenizer_J class is an implementation of a tokenizer that produces tokens based on a given input string for all the basic commands of the Silo language. The class is designed to skip white spaces, allows commas like the game, and determine if a character is a number, negative sign, or alphabetic. It also handles labels and identifies errors if there is an unrecognized word pattern. The tokenizer uses an enumeration of TokenType to represent the type of token and includes methods for getting the token type and token value. The nextToken() method looks for the next token in the input string and sets the tokenType and tokenValue if found, returning true if not EOF.

### Interpreter_A class:
#### This is a Java class representing an interpreter to execute internal AST (Abstract Syntax Tree) from a silo in a TIS-100 game implementation using JavaFX. The class defines various instance variables, constructors, and methods. The initialStartFromCmd method reads in the game instructions and sets the row and column values. It finds input and output values from the instructions and stores them in appropriate lists. The addInstruction method adds an instruction to the silo based on input from the GUI, using a tokenizer and parser to work with. The runInstructions method calls the tokenizer and parser in the GUI to execute instructions. Other methods include getters and setters for instance variables.

### Parser_J class:
#### This is a Java class that represents a parser for the Silo language. The parser takes the Tokenizer_J object as input and uses it to tokenize the input code. The parser then matches the input code with a set of grammar rules that correspond to the instructions in Instruction class. If the input code matches one of the grammar rules, the parser returns an interface object that corresponds to the matched instruction. The parseStatement() method is responsible for matching the input code with the grammar rules. It uses a switch statement to handle each type of token that the Tokenizer_J produces. The method returns an interface object that corresponds to the matched instruction.

### Port_A class:
#### The Port_A class holds four side values for every silo. The four values represent the ports that connect the silo to its neighbors. The Port_A class can be used to represent the ports of a silo in the TIS:100 game implementation. The class can be instantiated and its methods can be called to set and retrieve the port values.

### Run_J class:
#### The Run_J class is a thread class that implements the Runnable interface. It handles threading for each silo in the TIS-100 game, and is called from the GUI_R class. It contains a constructor that takes in two integers as parameters representing the row and column indices of the silo, and an integer field index which is initialized to 0. It also contains a static integer field sleepTime which is initially set to 1000. The run method is the main method of this class which is executed when the thread is started. It contains a loop that executes the instructions of the silo until the game is stopped.

### Silo_A class:
#### This is the Silo_A class which represents a single silo in the TIS-100 game. It has instance variables such as acc and bak which store integer values, siloInsCount which stores the number of instructions in the silo, portA which is an object of Port_A class and is used to handle input/output, instructions which is an ArrayList of Instruction objects, listOfInstructions which is a List of String objects used to store the instruction code as a string, and instructionIndex which is an integer value representing the index of the current instruction being executed. The class provides methods to get and set the values of these instance variables, as well as methods to add instructions to the instructions list, execute all the instructions in the list, and get/set the instructionIndex variable.

## Errors and Bugs
1. No highlight feature to show the current line the code is working from.
