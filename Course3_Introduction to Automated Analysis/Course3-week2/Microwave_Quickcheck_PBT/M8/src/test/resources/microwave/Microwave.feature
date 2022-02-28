Feature: Microwave

This feature defines a very simple microwave controller.  It has a 'start' button,
a 'stop/clear' button, digits 0-9, as well as a set of presets for cooking things like
popcorn that have known cook times.  It has a door sensor to determine whether 
the door of the microwave is open or closed.  

The microwave should allow users to choose a time to cook or a preset, then 
press 'start' to start cooking with the microwave.  The microwave should not
cook if the door is open.  If the user opens the door while cooking, the microwave
should go into the suspended mode.  Similarly, if the user presses the 'stop/clear' 
button, then the system should go into the suspended mode.  

Once in suspended mode, if the door is closed and the user presses the start button,
the system will continue to cook.  When the timer reaches zero, the microwave will 
stop cooking and go back to the 'setup' mode.  

The microwave runs by periodically polling once every 20 ms.  Each time a 20 ms
interval elapses, a tick() method is called on the microwave, which updates its state.

  Background:
    Given presets are 
       | name          | timeToCook | powerLevel | 
       | popcorn       |  55        | 10         |
       | pizza slice   |  30        | 5          | 
       | cup of coffee |  65        | 10         | 
       | cup of soup   |  60        | 5          |
    And polling rate is 20 ms   

  Scenario: A table illustration 
    Given foobars are 
       | name          | timeToCook | blah | 
       | popcorn       |  55        | de   |
       | pizza slice   |  30        | blah | 
       | cup of coffee |  65        | de   |
       | cup of soup   |  60        | blah |
    

  Scenario: Mike reheats a cookie for 1 second
    Given Mike presses the 2 key 
    And Mike presses the 0 key
    When Mike presses the start key
    And 1 seconds elapse
    Then digits reads 0019
    And mode is cooking


  Scenario: Bob reheats a coffee for 3 seconds
    Given Bob presses the following keys: 1 5 3
    When Bob presses the start key
    And 3 seconds elapse
    Then digits reads 0150
    And mode is cooking

  Scenario: Bob reheats a coffee again for 3 seconds
    Given Bob presses the following keys as a table: 
    | 1 | 
    | 5 | 
    | 3 | 
    When Bob presses the start key
    And 3 seconds elapse
    Then digits reads 0150
    And mode is cooking


  Scenario Outline: Cooking for different amounts of time.
    This scenario illustrates different manual cooking scenarios
    Given Mike presses the 2 key
    And Mike presses the 0 key 
    When Mike presses the start key
    When <time> seconds elapse
    Then digits reads <digits>
    And mode is <mode>

  Examples:
    | time | digits     | mode    | 
    | 5    | 0015       | cooking |
    | 10   | 0010       | cooking | 
    | 19   | 0001       | cooking | 
    | 20   | 0000       | setup   |
    | 21   | 0000       | setup   |


  
  Scenario: Trying out a preset.
    This scenario illustrates the nominal use case for presets
    Given Bob presses the 1 scenario key
    When Bob presses the start key
    When 5 seconds elapse
    Then digits reads 0050
    And mode is cooking
    
  Scenario: Preset out of range
    When Bob presses the 8 scenario key it will out-of-range fail
  
  Scenario: Mode incorrect for preset
    Given Bob presses the 1 scenario key
    When Bob presses the start key
    When 3 seconds elapse
    Then Bob presses the 1 scenario key it will mode fail  
    
   Scenario: User interrupts cooking 
    Given Bob presses the 1 scenario key
    When Bob presses the start key
    When 5 seconds elapse
    And Bob presses the clear key
    Then digits reads 0050
    And mode is suspended
   