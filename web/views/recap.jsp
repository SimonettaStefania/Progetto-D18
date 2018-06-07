<%--
  Created by IntelliJ IDEA.
  User: Chiara
  Date: 05/06/2018
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Progetto D18</title>

    <link   rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
            integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</head>

<body background ="../img/background.jpg" style="background-repeat: no-repeat">

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Project D-18</a>
    <button class="navbar-toggler p-0 border-0" type="button"></button>

    <div class="navbar-collapse offcanvas-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Reservation</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Catalogue</a>
            </li>

        </ul>
    </div>
</nav>



<div class="jumbotron" style="background-color: #ffffffc2; margin-left: 5%; margin-right: 5%; margin-top: 3%; padding-top: 3%; padding-bottom: 2%">
    <h3 style="margin-left: 3.8%">RESERVATION ID: 87926248</h3>
    <br/>

    <div class="row">

        <div class="col-12 col-md-9">

            <div class="jumbotron" style="background-color: #ffffffe3; margin-left: 5%; margin-right: 5%; padding-top: 3%; padding-bottom: 3%">

                <h3><center>Reservation recapp</center></h3>
                <hr class="my-4">

                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list"><div class="card bg-light mb-3">
                        <div class="card-header text-white" style="background-color: #6576a5">MenuProva</div>
                        <div class="card-body">
                            <h5 class="card-title">menuCost: 0	 nGuest: 10</h5>
                            <p class="card-text">
                            <pre>
                                Bruschetta	4.0 €	STARTER
                                Spaghetti alla carbonara	10.0 €	FIRST_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre></p>
                        </div>
                    </div>
                    </div>
                    <div class="tab-pane fade" id="list-profile" role="tabpanel" aria-labelledby="list-profile-list"><div class="card bg-light mb-3">
                        <div class="card-header text-white" style="background-color: #6576a5">Menu2</div>
                        <div class="card-body">
                            <h5 class="card-title">menuCost: 0	 nGuest: 10</h5>
                            <p class="card-text">
                            <pre>
                                Bruschetta	4.0 €	STARTER
                                Spaghetti alla carbonara	10.0 €	FIRST_COURSE
                                ciaone biricone	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre></p>
                        </div>
                    </div></div>
                    <div class="tab-pane fade" id="list-messages" role="tabpanel" aria-labelledby="list-messages-list"><div class="card bg-light mb-3">
                        <div class="card-header text-white" style="background-color: #6576a5">Menu3</div>
                        <div class="card-body">
                            <h5 class="card-title">menuCost: 0	 nGuest: 10</h5>
                            <p class="card-text">
                            <pre>
                                Bruschetta	4.0 €	STARTER
                                aljjfjaklfj alla carbonara	10.0 €	FIRST_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre></p>
                        </div>
                    </div></div>
                    <div class="tab-pane fade" id="list-settings" role="tabpanel" aria-labelledby="list-settings-list"><div class="card bg-light mb-3">
                        <div class="card-header text-white" style="background-color: #6576a5">Menu4</div>
                        <div class="card-body">
                            <h5 class="card-title">menuCost: 0	 nGuest: 10</h5>
                            <p class="card-text">
                            <pre>
                                GEsu	4.0 €	STARTER
                                Spaghetti alla carbonara	10.0 €	FIRST_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre></p>
                        </div>
                    </div></div>


                </div>
                <table class="table" style="margin-bottom: 0%">
                    <tr>
                        <th scope="row"></th>
                        <td><button type="button" class="btn" align="left" style="background-color: #6576a5; color: white; margin-left: 40%"> Total person <span class="badge">30</span></button></td>
                        <td><button type="button" class="btn" style="background-color: #6576a5; color: white; margin-right: 40%">Total cost <span class="badge">300</span></button></td>
                    </tr>
                </table>
            </div>

        </div>
        <div class="col-6 col-md-3 sidebar-offcanvas" id="sidebar">

            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list" href="#list-home" role="tab" aria-controls="home">Menu 1</a>
                <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list" href="#list-profile" role="tab" aria-controls="profile">Menu 2</a>
                <a class="list-group-item list-group-item-action" id="list-messages-list" data-toggle="list" href="#list-messages" role="tab" aria-controls="messages">Is simonetta legit?</a>
                <a class="list-group-item list-group-item-action" id="list-settings-list" data-toggle="list" href="#list-settings" role="tab" aria-controls="settings">No, she isn't</a>
            </div>

        </div><!--/span-->
    </div><!--/row-->

</div>

</body>

<footer>
    <p>&copy; Lisanti non è il titolare del corso</p>
</footer>

