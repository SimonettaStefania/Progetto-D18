
<%--
  Created by IntelliJ IDEA.
  User: SimonettaStefania
  Date: 08/06/2018
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Progetto D18</title>


<!-- ---------------------------------------------------- CSS LINK ------------------------------------------------------------------------- -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/ReservationsTemplate.css">


</head>

<body background ="../img/background.jpg" style="background-repeat: no-repeat">

<!-- ------------------------------------------------------ NAVBAR ------------------------------------------------------------------- -->

<nav class="navbar navbar-expand-md navbar-dark bg-dark">

    <a class="navbar-brand" href="#">Project D-18</a>
    <button class="navbar-toggler p-0 border-0" type="button"></button>

    <div class="navbar-collapse offcanvas-collapse">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="#" >Reservation</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Catalogue</a>
            </li>
        </ul>
    </div>
</nav>


<!-- ----------------------------------------------- TITLE, EMAIL AND BUTTON ------------------------------------------->


<div class="row"  id="r1" >

    <div class="col">

        <h1 style="margin-left: 8%">Your Reservations</h1>
        <br>
        <h6 style="margin-left: 8%; margin-right: 5%">Insert here your e-mail and click the button below to view your reservations</h6>

        <div class="row">

            <div class="input-group mb-3" id="list" >
                <div class="input-group-prepend" >
                    <span class="input-group-text" id="addon1" >E-mail</span>
                </div>
                <input type="text" class="form-control" id= "placeholder" placeholder="Insert your email" aria-label="Username" aria-describedby="basic-addon1" >
            </div>

        </div>

        <div class="row">

            <input type="submit" class="btn btn-dark" id="confirm"  value="Confirm">

        </div>

    </div>

    <div class="col">

<!------------------------------------------------------------ COLLAPSING LIST ------------------------------------------------------------------- -->

        <div class="container" id="container" >
            <div id="accordion" >

                <!-- -------------------------------------------- First element---------------------------------------------------- -->

                <div class="card" >

                    <div class="card-header">
                        <h5 class="mb-0">
                            <button class="btn collapsed btt" data-toggle="collapse" data-target="#collapseOne" aria-controls="collapseOne">
                                Reservation 1
                            </button>
                        </h5>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordion">

                        <div class="card-body">
                            Description of reservation 1 -------------
                            <pre>
                                Menu 1 ----------
                                Menu 2 ----------
                                Menu 3 ----------
                            </pre>

                            <div class="row">
                                <div class="col">
                                    <input type="submit" class="btn btn-sm btn-dark btt_m"  value="Modify">
                                </div>
                                <div class="col">
                                    <input type="submit" class="btn btn-sm btn-dark btt_d"  value="Delete">
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!--  ----------------------------------------------- Second element------------------------------------------ -->

                <div class="card">

                    <div class="card-header">
                        <h5 class="mb-0">
                            <button class="btn collapsed btt" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                Reservation 2
                            </button>
                        </h5>
                    </div>

                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">

                        <div class="card-body">
                            Description of reservation 2 -------------
                            <pre>
                                Menu veg ----------
                                Menu celiac ----------
                                Menu 3 ----------
                            </pre>

                            <div class="row">
                                <div class="col">
                                    <input type="submit" class="btn btn-sm btn-dark btt_m"  value="Modify">
                                </div>
                                <div class="col">
                                    <input type="submit" class="btn btn-sm btn-dark btt_d"  value="Delete">
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- ------------------------------------------------ Third element ----------------------------------------------- -->

                <div class="card">

                    <div class="card-header" id="headingThree">
                        <h5 class="mb-0">
                            <button class="btn collapsed btt" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                Reservation 3
                            </button>
                        </h5>
                    </div>

                    <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordion">

                        <div class="card-body">
                            Description of reservation 3 -------------
                            <pre></pre>

                            <div class="row">
                                <div class="col">
                                    <input type="submit" class="btn btn-sm btn-dark btt_m"  value="Modify">
                                </div>
                                <div class="col">
                                    <input type="submit" class="btn btn-sm btn-dark btt_d"  value="Delete">
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
