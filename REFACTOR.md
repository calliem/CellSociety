Le Qi and Rubens Martins Bezerra Farias

In our CellSocietyView class, there were two functions that set 
the permissions of enabling of the buttons, yet they had the 
same exact code except for whether certain buttons should be 
enabled or not when called. The design decision was to combine 
the two into one function, which simply took a single boolean 
parameter to determine whether the entire set of buttons in the 
view should have been enabled. Another alternative would have 
been to extract the method to another class that already 
definitively knew the permissions, but this would have led to 
less abstraction.