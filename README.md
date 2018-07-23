# D18:
The goal of thee project is to realize a system that let a customer create a reservation for an event, allowing a deep customization of the menu and everything related. It allows him to:
<div align="center">
    <figcaption align"left">Choose every detail about the reservation:</figcaption>
    <img  src="https://image.ibb.co/npQj1y/home.png"> <br>
   <figcaption align"left">See the catalogue:</figcaption>
    <img src="http://i63.tinypic.com/wwku2v.png"><br>
    <figcaption>See all informations about the reservation the customer is creating:</figcaption>
    <img src="http://i63.tinypic.com/21aw8qe.png"><br>
    <figcaption>Choose dishes applying filters to allergenes and vegan/vegetarian/celiac:</figcaption>
    <img  src="http://i68.tinypic.com/sl5zd5.png"> <br>
    <figcaption>Create optimized menus starting from a budget and a number of guests:</figcaption>
    <img src="http://i63.tinypic.com/2qmjg1u.png"><br>
    <figcaption>See a recap of the reservation:</figcaption>
    <img src="http://i65.tinypic.com/97t9xs.png"><br>
    <figcaption>Get a unique ID that represents the reservation after the confirm:</figcaption>
    <img src="http://i68.tinypic.com/nnmadl.png"><br>
    <figcaption>Delete a previous reservation using customer e-mail and the unique ID:</figcaption>
    <img src="http://i65.tinypic.com/1zmgf40.png"><br>
</div>

## Members & responsibilities:
- Database/Code documentation: [nik1360](https://github.com/nik1360)
- Code quality: [zorzr](https://github.com/zorzr)
- Test and validation: [Woizbora](https://github.com/Woizbora)
- Graphical User Interface:  [chiaraBarbera](https://github.com/chiaraBarbera)
- Project documentation: [beard33](https://github.com/beard33)
- Requirements documentation: [SimonettaStefania](https://github.com/SimonettaStefania)

## How to run the project:

### What you need:
In order to run the program is necessary to have:
* Apache Tomcat Server 8.5.3 or higher
* JDK 8 or higher
* MySql Server (*progettoD18* user must be created with *delete, insert, select* permissions)

### Instructions:
* Clone the project from github
* Create a new WEB project from you IDE (Intellij/Eclipse etc)
* Add the mysql-connector-java-5.1.46.jar (that can be founded in */web/WEB-INF/lib*) to project structures -> modules -> dependencies
* Add all the jars you can find in the */lib* to the project structure
* Configure a local Tomcat server with the Web-War-Exploded artifact
* Create a localhost MySql user named *progettoD18* indentified by password *progettoD18* and run *create_restaurant_db.sql* that you can find in */database* folder
* Run the project 

