CoffeeMaker - Golden Version
=============================
This project contains the golden (or "good") version of the CoffeeMaker example.

Below, are instructions to build and, if you have tests, execute tests on this code.  We describe the prerequisites and provide instructions for building and running the tests.  We also include information about the directory structure provided.


Package Prerequisites
---------------------
Java 8 JDK

### Installing Java 8
#### Windows / macOS / Linux
To install Java, download the **JDK** installer from: 
  http://www.oracle.com/technetwork/java/javase/downloads/index.html

#### Ubuntu
On Ubuntu 16.04 and up, to install Java (OpenJDK) run:

    sudo apt update
    sudo apt install default-jdk
    
If you'd rather run Oracle Java, run:

    sudo add-apt-repository ppa:webupd8team/java
    sudo apt update
    sudo apt install oracle-java8-installer
   
### Eclipse (recommended)
To install eclipse, visit https://www.eclipse.org/downloads/ and download the installer.  This is the most reliable method to get the latest version of eclipse.
   
    
Known Issues
------------
### Java 9
Currently, Java 9 is not supported for this project.  Please install Java 8 and update your `JAVA_HOME` environment variable to point to your Java 8 JDK.  Gradle will throw an exception if you try to use any other version of Java.

Alternatively, if you intend to run everything from command-line and you have multiple versions of Java installed, you can execute `./gradlew build -Dorg.gradle.java=<PATH_TO_JAVA_HOME>` or the similar Windows command to avoid updating your `JAVA_HOME` variable.  Note, however, that `PATH_TO_JAVA_HOME` cannot include spaces in it, even if the path is quoted.

On some systems, eclipse may have issues when run with Java 9 (when the `JAVA_HOME` variable points to the Java 9 directory).  If you must run eclipse using Java 9, make sure you install eclipse 4.7.1a or higher from the installer.  Using `umake` to install it on Ubuntu will likely result in errors due to the modular nature of Java 9.  If you want to troubleshoot this yourself, check out https://stackoverflow.com/questions/45494304/eclipse-installer-can-not-initialize-cryptographic-mechanism#45495743 .


### Eclipse: Running with HiDPI Displays
Eclipse should work out of the box with HiDPI displays (also called UHD, Retina, 4K/5K/...) on Windows and macOS.  Unfortunately, this isn't true for Linux (at least on the current release of Ubuntu with a GTK-based window manager).  If you encounter an issue running eclipse with a HiDPI display on Linux, you may need to disable SWT-GTK.  To do this, set the `SWT_GTK` property to 0 (`SWT_GTK=0`).  This will make the buttons and menus in eclipse readable.  I use the following command on my Ubuntu machine (from the directory containing my eclipse installation):
    
    export SWT_GTK3=0; ./eclipse


Building
---------
This contains instructions for building the project.  Note, the original project should build with no errors/failures.

### From Eclipse
#### Importing the Project to your Workspace
To import the project:
  1. Go to File > Import.  
  2. In the Import window, expand the  "Gradle" folder and select "Existing Gradle Project".  Click "Next".
  3. If you encounter the Welcome screen (a screen describing how to "experience the best Gradle integration"), click "Next.
  4. On the "Import Gradle Project" screen, enter the "Project root directory" (the directory containing this README) or click "Browse..." and navigate to it.  Once the root directory is entered, click "Finish".


#### Building
To build the project, you will need the "Gradle Tasks" view (normally it is a tab in the bottom frame next to "Problems", "Javadoc", "Console", etc.).  If you don't have it, go to Window > Show View > Other and from the Gradle folder, double click on "Gradle Tasks".

In the "Gradle Tasks" view, expand the project folder then expand "build".  Double click on "build".  You will be taken to the "Gradle Executions" view where you will see the results of running each step in the gradle build script.  The project should build successfully (at least before you make any changes).  To view what was printed to the screen by the build, open the "Console" view.


### From Command-Line
To compile from command-line, execute `./gradlew build` (on Linux\UNIX; including macOS) or `gradlew.bat build`.  This will download gradle and all required libraries (on the first run only).  Then, it will compile the code, and, if tests are provided, execute all tests.  The test results are emitted as a report to `build/reports/tests/test/index.html`.

To open the report in a browser, append "file://" before the full path to the file (on Windows, change the "/" to "\"), or navigate to the directory and open the file with the browser.  For example, `file:///\<pathToExpandedProject\>/build/reports/tests/test/index.html`, opens the JUnit test report on my system once I replace `pathToExpandedProject` with the actual path.


### From Other IDEs
You can run this project within any Gradle-capable IDE (e.g., InteliJ IDEA, NetBeans with the Gradle plugin).  Consult your IDE's instructions for how to set this up.


Adding Tests
------------
To add test cases, you will need to create a `src/test/java` directory (and, if required, a `src/test/resources` directory).  You can place your tests in there.  If you made these as part of a different project (for example, one that uses the "buggy" version of the CoffeeMaker), I recommend copying over the entire `src/test` directory.


Directory Structure
-------------------
 * `build.gradle` -- the build file that will help you build the SUT and tests as well as execute the tests and measure coverage
 * `gradlew` -- script to run gradle from a *NIX system
 * `gradlew.bat` -- script to run gradle from Windows
 * `Requirements-CoffeeMaker.pdf` -- the requirements 
 * `ClassDiagram-CoffeeMaker.pdf` -- the class diagram of the coffee maker software
 * `SequenceDiagram-CoffeeMaker.pdf` -- the sequence diagram (describes the interactions among the objects) of the coffee maker software
 * `src/main/java` -- contains the system under test (SUT; in this case, the coffee maker code) and all of its dependencies.  Do not modify any of this code.
   - `edu.ncsu.csc326.coffeemaker.*` -- the code for the system under test (the coffee maker)
 * `src/test/java` -- the test code -- We don't provide this.  If you want to run your own tests, you will need to create this directory and populate it with your tests.
 * `build/reports` -- contains the different reports generated by the build.  NOTE: This directory will only exist once test exist and a gradle build has been run! 
   - `tests/test/index.html` -- the JUnit test report (describing which tests passed and which failed); this file is only created if tests are provided and executed.
