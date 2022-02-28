Feature: TestFeature

  Scenario: test regular expressions that should pass
    When test_posint 12345
    When test_posint 9
    When test_posint 77777777
    When test_int -1
    When test_int 1435
    When test_float 12345.543
    When test_float -13.56
    When test_ip_address 123.34.76.109
    When test_ip_address 123.34.76.109
    When test_ip_address 105.22.33.44
    When test_ip_address 1.2.3.4
    When test_splitter spill
    When test_splitter Sponge
    When test_splitter tap
    When test_splitter rebuild
    When test_splitter2 spill
    When test_splitter2 Sponge
    When test_splitter2 tap
    When test_splitter2 rebuild
  
  Scenario: fail 1
    When test_int 5y6
  Scenario: fail 2
    When test_int 56k
  Scenario: fail 3
    When test_int 57.45
  Scenario: fail 4
    When test_float 1235
  Scenario: fail 5
    When test_int 46 and more
  Scenario: fail 6
    When test_float 3.45 and more
  Scenario: fail 7
    When test_ip_address 123.45.47
  Scenario: fail 8
    When test_ip_address four score and 123.34.76.109
  Scenario: fail 9
    When test_ip_address 123.34.76.109 and more
    