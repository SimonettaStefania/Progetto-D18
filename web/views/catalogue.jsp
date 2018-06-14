<%--
  Created by IntelliJ IDEA.
  User: Chiara
  Date: 08/06/2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Catalogue Page</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/CatalogueStyle.css">

</head>

<body background ="../img/background.jpg" >



<!-- ========== NAVBAR ======================================================================================================-->

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
                <a class="nav-link active" href="#">Catalogue</a>
            </li>

        </ul>
    </div>
</nav>




<!-- ========== CATEGORY LIST ======================================================================================================-->


<div class="jumbotron" id= "Jumbotron">
    <h1 style="margin-left: 8%">Catalogue</h1>
    <div class="row">
        <div class="col-md-4" id="sidebar">

            <p style="margin-left:2%">Click on the dish name to view its details :</p>



            <div class="list-group" id="list-tab" role="tablist">

                <a class="list-group-item list-group-item-action active" id="label-starters" data-toggle="list" href="#list-starters" role="tab" >Starters</a>
                <a class="list-group-item list-group-item-action" id="label-first" data-toggle="list" href="#list-first" role="tab" >First Courses</a>
                <a class="list-group-item list-group-item-action" id="label-main" data-toggle="list" href="#list-main" role="tab" >Main Courses</a>
                <a class="list-group-item list-group-item-action" id="label-desserts" data-toggle="list" href="#list-desserts" role="tab" >Desserts</a>
                <a class="list-group-item list-group-item-action" id="label-drinks" data-toggle="list" href="#list-drinks" role="tab" >Drinks</a>

            </div>

            <form action="/home" method="post">
                <input type="submit" class="btn btn-success btn-lg" value="&laquo; Home" style="margin-top:6%">
            </form>
        </div>

        <!-- =====================================================DISHES ELEMENTS ===========================================================-->

        <div class="col-md-8">

            <div class="tab-content" id="nav-tabContent" >

                <!-- -------------------------------------------------------- STARTERS -------------------------------------------------------->

                <div class="tab-pane fade show active" id="list-starters" role="tabpanel" aria-labelledby="label-starters">

                    <div id="accorditionStarter">

                        <div class="card">

                            <div class="card-header" id="starter1">

                                <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionStarter1" aria-controls="descriptionStarter1">
                                    Starter
                                </button>

                            </div>

                            <div id="descriptionStarter1" class="collapse" aria-labelledby="starter1" data-parent="#accorditionStarter">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>

                        <div class="card" >

                            <div class="card-header" id="starter2">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionStarter2" aria-controls="descriptionStarter2">
                                        Starter
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionStarter2" class="collapse " aria-labelledby="starter2" data-parent="#accorditionStarter">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>


                    </div>

                </div>

                <!-- -------------------------------------------------------- FIRST COURSE -------------------------------------------------------->

                <div class="tab-pane fade " id="list-first" role="tabpanel" aria-labelledby="label-first">

                    <div class="accordion" id="accorditionFirst">


                        <div class="card" >

                            <div class="card-header" id="first1">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionFirst1" aria-controls="descriptionFirst1">
                                        First
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionFirst1" class="collapse " aria-labelledby="first1" data-parent="#accorditionFirst">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>

                        <div class="card" >

                            <div class="card-header" id="first2">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionFirst2" aria-controls="descriptionFirst2">
                                        first
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionFirst2" class="collapse " aria-labelledby="first2" data-parent="#accorditionFirst">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>


                    </div>

                </div>

                <!-- -------------------------------------------------------- MAIN COURSE -------------------------------------------------------->

                <div class="tab-pane fade " id="list-main" role="tabpanel" aria-labelledby="label-main">

                    <div class="accordion" id="accorditionMain">


                        <div class="card" >

                            <div class="card-header" id="main1">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionMain1" aria-controls="descriptionMain1">
                                        Main
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionMain1" class="collapse " aria-labelledby="main1" data-parent="#accorditionMain">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>

                        <div class="card" >

                            <div class="card-header" id="main2">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionMain2" aria-controls="descriptionMain2">
                                        Main
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionMain2" class="collapse " aria-labelledby="main2" data-parent="#accorditionMain">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>


                    </div>

                </div>

                <!-- -------------------------------------------------------- DESSERTS -------------------------------------------------------->

                <div class="tab-pane fade " id="list-desserts" role="tabpanel" aria-labelledby="label-desserts">

                    <div class="accordion" id="accorditionDessert">


                        <div class="card" >

                            <div class="card-header" id="dessert1">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionDessert1" aria-controls="descriptionDessert1">
                                        Dessert
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionDessert1" class="collapse " aria-labelledby="dessert1" data-parent="#accorditionDessert">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>

                        <div class="card" >

                            <div class="card-header" id="dessert2">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionDessert2" aria-controls="descriptionDessert2">
                                        Dessert
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionDessert2" class="collapse " aria-labelledby="dessert2" data-parent="#accorditionDessert">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>


                    </div>

                </div>

                <!-- -------------------------------------------------------- DRINKS -------------------------------------------------------->

                <div class="tab-pane fade " id="list-drinks" role="tabpanel" aria-labelledby="label-drinks">

                    <div class="accordion" id="accorditionDrink">


                        <div class="card" >

                            <div class="card-header" id="drink1">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionDrink1" aria-controls="descriptionDrink1">
                                        Drink
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionDrink1" class="collapse " aria-labelledby="drink1" data-parent="#accorditionDrink">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>

                        <div class="card" >

                            <div class="card-header" id="drink2">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#descriptionDrink2" aria-controls="descriptionDrink2">
                                        Drink
                                    </button>
                                </h5>
                            </div>

                            <div id="descriptionDrink2" class="collapse " aria-labelledby="drink2" data-parent="#accorditionDrink">

                                <div class="card-body">
                                    Description

                                </div>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- =================== BOOTSTRAP SCRIPTS ======================================================================== -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>

</html>