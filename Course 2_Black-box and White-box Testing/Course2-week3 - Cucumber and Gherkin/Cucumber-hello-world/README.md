Cucumber: Get in my belly!
==========================
In this assignment, you'll get some practice at building a few Gherkin scripts for this very silly "eating cucumbers" example. Your first task is to first be able to run Cucumber scripts through Java using Gradle. I have provided a project that is loadable in Eclipse (or other IDE) for this purpose. I have also included a build.gradle file that can be executed on the command line to build the project. 

After you get Cucumber up and running, your task is to extend the `belly.feature` file (located in `src/test/resources/skeleton`).

Below, are instructions to get you started.  We describe the requirements and provide instructions for building and running the tests.  We also include information about the directory structure provided.

### Deliverables
Your deliverable is an extended version of the file `belly.feature` file with some additional Gherkin scenarios to adequately test the `Belly` class.

This exercise focuses entirely on writing Gherkin.  You are NOT to alter any file EXCEPT `belly.feature`.


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

On some systems, eclipse may have issues when run with Java 9 (when the `JAVA_HOME` variable points to the Java 9 directory).  Set the `JAVA_HOME` system/shell varible to point to Java 8.


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

In the "Gradle Tasks" view, expand "CoffeeMaker_Cucumber" and "build".  Double click on "build".  You will be taken to the "Gradle Executions" view where you will see the results of running each step in the gradle build script.  The project should build successfully (at least before you make any changes).  To view what was printed to the screen by the build, open the "Console" view.


### From Command-Line
To compile from command-line, execute `./gradlew build` (on Linux\UNIX; including macOS) or `gradlew.bat build`.  This will download gradle and all required libraries (on the first run only).  Then, it will compile the code, execute the Cucumber tests.  The test results are emitted as reports to `build/reports/tests/test/index.html`. 

NOTE:  While we are using Cucumber for testing, we are using it within a JUnit test runner so we can use Cucumber with our existing tools (Gradle, etc.).  This also gives us a nice report to see the results of the Cucumber tests, if you choose to run the tests from command-line.

To open a report in a browser, append "file://" before the full path to the file (on Windows, change the "/" to "\"), or navigate to the directory and open the file with the browser.  For example, "file:///\<pathToExpandedProject\>/build/reports/tests/test/index.html", opens the JUnit test report on my system once I replace `pathToExpandedProject` with the actual path.


### From Other IDEs
You can run this project within any Gradle-capable IDE (e.g., InteliJ IDEA, NetBeans with the Gradle plugin).  Consult your IDE's instructions for how to set this up.


Directory Structure
-------------------
 * `build.gradle` -- the build file that will help you build the SUT and tests as well as execute the tests and measure coverage
 * `gradlew` -- script to run gradle from a *NIX system
 * `gradlew.bat` -- script to run gradle from Windows
 * `src/main/java` -- contains the system under test (SUT; in this case, the belly class).  Do not modify any of this code.
 * `src/test/java` -- the test code that will be executed by Cucumber and will process the `belly.feature` file.
   - `skeleton.RunCukesTest` -- the JUnit test runner that allow us to execute our Cucumber tests.  Do not modify this file!
   - `skeleton.Stepdefs` -- contains the implementation (the Java code) supporting your Gherkin-encoded tests.  Do not modify this file!
 * `src/test/resources` -- contains the non-code files needed to execute the tests (including your Gherkin-based specification of the system tests).
   - `skeleton/belly.feature` -- the Gherkin-based specification of your tests.  You will update this file and turn this in when you are done.
 * `build/reports` -- contains the different reports generated by the build.  NOTE: This directory will only exist once a gradle build has been run!
   - `tests/test/index.html` -- the JUnit test report (describing which tests passed and which failed); this file is only created if the unit tests are executed.
   