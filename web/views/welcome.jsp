
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>


<html lang="en">

        <head>


            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <title>Welcome Page</title>

            <!-- Bootstrap core CSS -->
            <link  rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
            <link  rel="stylesheet" type="text/css" href="../stylesheets/WelcomeTemplateStyle.css"/>



        </head>


 <!-- ==========  BODY OF THE PAGE ===================================================================================================================-->


        <body background="../img/background.jpg" background-repeat=no-repeat; >


        <!-- ============= NAVBAR =======================================================================-->

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="#">Project D-18</a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="#">Reservations </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="#">Catalogue </a>
                        </li>
                    </ul>
                </div>

            </nav>

        <!-- ============= TITLE AND DESCRIPTION =======================================================================-->


            <div class = "container-fluid">

                <div id = "welcome" class = "row justify-content-around" >

                    <div class = "col-8 ">

                        <h1 class="display-4" >Welcome to Project D-18 book page!</h1>
                        <p id="description">This website will guide you through the creation of a reservation. You can book your event and
                            add one or more menu for your guests. What are you waiting for ? See the options below to discover what our website
                            can offer!</p>

                    </div>

                </div>

            </div>

        <!-- ============= FORM CATALOGUE AND RESERVATIONS =======================================================================-->


            <div class = " container-fluid">


            <div class = "row justify-content-around align-items-center">

                <div class = "col col-md-5">

                    <h1 class = "display-6">New Reservation</h1>

                    <p>Want to make a new reservation? Compile the form below and organize your event!</p>

                    <div class="input-group input-group-sm mb-3">

                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-names"> First Name | Last Name</span>
                        </div>
                        <input id = "name" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm">
                        <input id = "surname" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" >

                    </div>


                    <div class="input-group input-group-sm mb-3 ">

                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-mail">E-mail</span>
                        </div>

                        <input type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm">

                    </div>

                    <div class="input-group input-group-sm mb-3 ">

                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroup-date">Date</span>
                        </div>

                        <input type="date" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm">

                    </div>


                    <p><a class="btn btn-info btn-lg" href="#" role="button">Book &raquo;</a></p>



                </div>


                <div class = "col col-md-2">

                    <h1 class = "display-6" style = "padding-bottom:60px">Catalogue</h1>
                    <p>Want to see what we offer in our catalogue? Click on the button below and discover our dishes,from startes to desserts ! </p>
                    <p><a class="btn btn-info" href="#" role="button">Go to Catalogue&raquo;</a></p>

                </div>


                <div class="col col-md-2">
                    <h1 class = "display-6" style = "padding-bottom:60px">Reservations</h1>
                    <p >You made a reservation and now you want to see it? Nothing easier! Click on the button below to view your hires.</p>
                    <p><a class="btn btn-info" href="#" role="button">Go to Reservations &raquo;</a></p>
                </div>


            </div>



    </div>


        <!-- ============= BOOTSTRAP SCRIPTS =======================================================================-->


            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>



        </body>

</html>