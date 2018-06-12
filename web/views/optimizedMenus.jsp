<%--
  Created by IntelliJ IDEA.
  User: Woizbora
  Date: 08/06/18
  Time: 23.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Optimized menu</title>

    <!-- -------------------------------------- CSS LINK --------------------------------------------------- -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/RecapTemplateStyle.css">

</head>

<body background ="../img/background.jpg" style="background-repeat: no-repeat">

<!-- -------------------------------------- NAVBAR --------------------------------------------------- -->

<nav class="navbar navbar-expand-md navbar-dark bg-dark">

    <a class="navbar-brand" href="#">Project D-18</a>
    <button class="navbar-toggler p-0 border-0" type="button"></button>

    <div class="navbar-collapse offcanvas-collapse">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Reservations<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Catalogue</a>
            </li>

        </ul>
    </div>
</nav>

<!-- -------------------------------------- TITLE --------------------------------------------------- -->

<div class="jumbotron" id="mainJumbotronRecap">

    <h1 style="margin-right: 27%"><center>Optimized Menus</center></h1>
    <br/>

    <div class="row">

        <div class="col-12 col-md-9">

            <!-- -------------------------------------- OPTIMIZED MENUS --------------------------------------------------- -->

            <div class="jumbotron" id="menuJumbotronRecap">

                <div class="tab-content" id="nav-tabContent">

                    <div class="tab-pane fade show active" id="list-menu1" role="tabpanel">

                        <div class="card bg-light mb-3">
                            <div class="card-header text-white">MenuStarter</div>
                            <div class="card-body">

                                <h5 class="card-title">menuCost: millemila	 nGuest: 10</h5>
                                <pre>
                                Caviale di Magikarp	400.0 €	STARTER
                                Spaghetti alla carbonara	10.0 €	FIRST_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre>

                                <hr class="my-4">

                                <form action="/selectOptimized" method="post">

                                    <input name="code" type="hidden" value="1">
                                    <input type="submit"class="btn btn-success btn-lg" style="margin-left: 40% "value="Select menu &checkmark;">

                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="list-menu2" role="tabpanel">

                        <div class="card bg-light mb-3">
                            <div class="card-header text-white">MenuPrimone</div>
                            <div class="card-body">

                                <h5 class="card-title">menuCost: tanti euro	 nGuest: 10</h5>
                                <pre>
                                Bruschetta	4.0 €	STARTER
                                Spaghetti alla Simonetta	1000.0 €	FIRST_COURSE
                                ciaone biricone	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre>

                                <hr class="my-4">

                                <form action="/selectOptimized" method="post">

                                    <input name="code" type="hidden" value="2">
                                    <input type="submit"class="btn btn-success btn-lg" style="margin-left: 40% "value="Select menu &checkmark;">

                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="list-menu3" role="tabpanel">

                        <div class="card bg-light mb-3">
                            <div class="card-header text-white">MenuSecondo</div>
                            <div class="card-body">

                                <h5 class="card-title">menuCost: 100 rublinGuest: 10</h5>
                                <pre>
                                Bruschetta	4.0 €	STARTER
                                Secondo Petaloso	10.0 petali	MAIN_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre>

                                <hr class="my-4">

                                <form action="/selectOptimized" method="post">

                                    <input name="code" type="hidden" value="3">
                                    <input type="submit" class="btn btn-success btn-lg" style="margin-left: 40% "value="Select menu &checkmark;">

                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="list-menu4" role="tabpanel">

                        <div class="card bg-light mb-3">
                            <div class="card-header text-white">MenuModerato</div>
                            <div class="card-body">

                                <h5 class="card-title">menuCost: 0	 nGuest: 10</h5>
                                <pre>
                                GEsu	4.0 €	STARTER
                                Democrazia cristiana	10.0 €	FIRST_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK
                                GEsu	4.0 €	STARTER
                                Democrazia cristiana	10.0 €	FIRST_COURSE
                                Caffè	1.0 €	DRINK
                                Acqua	1.0 €	DRINK</pre>

                                <hr class="my-4">

                                <form action="/selecOptimized" method="post">

                                    <input name="code" type="hidden" value="4">
                                    <input type="submit" class="btn btn-success btn-lg" style="margin-left: 39%; "value="Select menu &checkmark;">

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- -------------------------------------- LIST MENU INTERACTIVE--------------------------------------------------- -->

        <div class="col-6 col-md-3 sidebar" id="sidebar">



            <div class="list-group" id="list-tab" role="tablist">

                <a class="list-group-item list-group-item-action active" data-toggle="list" href="#list-menu1" role="tab">Menu 1</a>
                <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-menu2" role="tab" >Menu 2</a>
                <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-menu3" role="tab">Menu 3</a>
                <a class="list-group-item list-group-item-action" data-toggle="list" href="#list-menu4" role="tab">Menu 4</a>

            </div>

            <br/><br/><br/>

            <!-- -------------------------------------- BACK BTN --------------------------------------------------- -->

            <div class="row" style="margin-right: 20%">

                <form action="/status" method="post">
                    <input type="hidden", name="backToStatus", value="true">
                    <input type="submit" class="btn btn-lg" style="margin-left: 20%" id="btnBack" value="&laquo; Back">

                </form>

            </div>

        </div>

    </div>

</div>

<!-- -------------------------------------- BOOTSTRAP SCRIPT --------------------------------------------------- -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>

<footer>
    <p>&copy; Lisanti non è il titolare del corso</p>
</footer>

