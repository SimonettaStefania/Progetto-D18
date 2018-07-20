# D18:
The goal of the project is to realize a system from which a customer can create a personalized reservation (for an event with a lot of guests) and deeply customize one or more menus. It's possible to create more than one menu for every reservation and to decide how many people to assign to each of them (*e.g. if you make a reservation for 50 people you can decide to create 3 menus: the first for 15 people, the second for 15 people and the third for 20*). 
In addition customer has two choices for every menu:
* He can decide to customize the menu and build one from nothing: in this case the customer can choose every dish he wants from the catalogue; moreover with this choice he can apply filters to the catalogue itself (*Vegeratian, Vegan, Celiac*) and at the same time read informations about every dish (including price and allergenes)

* He can decide to insert a budget and let the program create the menu: the system will create 4 menus (one giving more budget to the starters, one to the first, one to the mains and the last one balacing the budget between all of them); customer can then choose from the generated menus or decide to go back and create it's own menu (choice one)

When the reservation is confirmed it will be saved into the database and the customer will be given an alphanumeric random code: with his e-mail and the code he can use the site to view his reservations and delete them.


## Members & responsibilities:
- Database/Code documentation: [nik1360](https://github.com/nik1360)
- Code quality: [zorzr](https://github.com/zorzr)
- Test and validation: [Woizbora](https://github.com/Woizbora)
- Graphical User Interface:  [chiaraBarbera](https://github.com/chiaraBarbera)
- Project documentation: [beard33](https://github.com/beard33)
- Requirements documentation: [SimonettaStefania](https://github.com/SimonettaStefania)

## How to run the program:
In order to run the program is necessary to have:
* Apache Tomcat Server 8.5.3 or higher
* JDK 8 or higher
* MySql Server (progettoD18 user must be created with *delete, insert, select* permissions)

