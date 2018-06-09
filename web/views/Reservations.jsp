
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Reservations</title>

        <!-- -------------------------------------- CSS LINK --------------------------------------------------- -->

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="../stylesheets/ReservationsTemplate.css">


    </head>

    <body background ="../img/background.jpg" >


    <!-- ======== NAVBAR ================================================================================================-->


    <nav class="navbar navbar-expand-md navbar-dark bg-dark">

        <a class="navbar-brand" href="#">Project D-18</a>
        <button class="navbar-toggler p-0 border-0" type="button"></button>

        <div class="navbar-collapse offcanvas-collapse">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="#" >Reservations</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Catalogue</a>
                </li>
            </ul>
        </div>
    </nav>

    <!--  ====== INSERT EMAIL ======================================================================================-->

    <div class = "jumbotron">

        <div class="row">

            <div class = "col-md-5">

                <h1 class = "display-4">Your Reservations</h1>
                <p>Insert your e-mail and click on the button below to view your reservations</p>


            </div>

        </div>

        <!-- ======== RESERVATIONS RECAP ================================================================================-->

        <div class = "row">

            <div class = "col-md-5">

                <div class="input-group mb-3" id="list" >
                    <div class="input-group-prepend" >
                        <span class="input-group-text" id="emailLabel" >E-mail</span>
                    </div>
                    <input type="text" class="form-control" id= "placeholder" placeholder="Insert your email" aria-describedby="emailLabel" >
                </div>

                <input type = "button" class="btn btn-dark " id="confirmEmail"  value="View">


            </div>


            <div class = "col-md-6">

                <div class="accordion" id="accorditionStarter">


                    <div class="card">

                        <div class="card-header" id="starter1">
                            <h5 class="mb-0">
                                <button class="btn btn-info " type="button" data-toggle="collapse" data-target="#descriptionStarter1" aria-expanded="false">
                                    Reservation 1
                                </button>
                            </h5>
                        </div>

                        <div id="descriptionStarter1" class="collapse" aria-labelledby="starter1" >
                            <div class="card-body">

                                <p>Description<p/>

                                <div class = "col"> <input type = "button" class="btn btn-danger"  value="Delete"> </div>

                            </div>
                        </div>

                    </div>

                    <div class="card">

                        <div class="card-header" id="starter2">
                            <h5 class="mb-0">
                                <button class="btn btn-info " type="button" data-toggle="collapse" data-target="#descriptionStarter2" aria-expanded="false">
                                    Reservation 2
                                </button>
                            </h5>
                        </div>

                        <div id="descriptionStarter2" class="collapse" aria-labelledby="starter2" >
                            <div class="card-body">
                                <p>Description<p/>

                                <div class = "col"> <input type = "button" class="btn btn-danger"  value="Delete"> </div>

                            </div>
                        </div>

                    </div>


                </div>



            </div>

        </div>


    </div>


    <!-- ============== BOOTSTRAP SCRIPTS ==================================================================================================-->


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>



    </body>

</html>