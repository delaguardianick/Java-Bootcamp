/*
CLASSES

Making general class for 2 sub classes:
Remove methods in common in sub classes
    and add them to super Class
Parent class has general info
Subclasses have specific info

ex.
-Animal: eat(), reproduce()
    -Lion: run(), 
    -Bird: fly(), layEgg()

Polymorphism:
Same method name for different implementation
-eat() is different for a bird and lion
    -different foods, etc.

    2 types of polymorphism:
    - Overloading
    - Overwriting

Abstact Classes:
- If class has at least 1 abstact method
    Abstact method:
    - Implementation of parent method is inside subclasses
        - Not defined in parent class
- Cant create object / cant instantiate

Interface: 
- All methods are abstact by default
- No implementation for them inside inside interface
- Cant create object / cant instantiate
    - Because of abstact methods

private variables can be accessed through public methods
Setters/getters

static methods: no need to create object to use
    Math.add();
non-static:
    Dog mydog = new Dog();
    mydog.bark();

Constructors
- Can have multiple
*/


