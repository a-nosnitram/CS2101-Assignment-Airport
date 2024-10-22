# CS2101-Assignment-Airport

This application simulates the operations of an airport, managing entities like planes, runways, terminals, airlines, passengers, and tickets. The core functionality includes managing planes landing and take-off, boarding passengers, and handling creating and viewing airport entities.

## Directory Structure
```
├── impl/         Contains main implementation classes
├── exceptions/   Contains custom exception classes
└── design/       Contains helper class (i.e, Colours.java for terminal colors)
└── README.md     This README file
```
## Prerequisites

Please ensure you have the following installed :
	•	Java Development Kit (JDK) 8 or higher.
	•	VS Code (or another editor) with the Java Extension Pack installed.

## Run the Application

1. Compile all the .java files
```
javac -d ../bin $(find . -name "*.java")
```

3. Run the Application
```
java -cp ../bin impl.AirportX
```
