
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


        <body background="../img/background.jpg"  >


        <!-- ============= NAVBAR =======================================================================-->

            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <a class="navbar-brand" href="welcome.jsp">Project D-18</a>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="welcome.jsp">Home </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="#">Reservations </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="catalogue.jsp">Catalogue </a>
                        </li>
                    </ul>
                </div>

            </nav>

        <!-- ============= TITLE AND DESCRIPTION =======================================================================-->


            <div class = "jumbotron">

                <div id = "welcome" class = "row justify-content-around" >

                    <div class = "col-8 ">

                        <h1 class="display-4" >Welcome to Project D-18 book page!</h1>
                        <p id="description">This website will guide you through the creation of a reservation. You can book your event and
                            add one or more menu for your guests. <br/>What are you waiting for ? See the options below to discover what our website
                            can offer!</p>

                    </div>

                </div>

            </div>

        <!-- ============= FORM CATALOGUE AND RESERVATIONS =======================================================================-->


            <div class = " jumbotron ">


                <div class = "row justify-content-around align-items-center">

                    <div class = "col col-md-5">

                        <h1 class = "display-4">New Reservation</h1>

                        <p>Want to make a new reservation? Compile the form below and organize your event!</p>

        <!-- --------------------------- NAME AND SURNAME ------------------------------------------------------------------>

                    <form action="../status" method="post">
                        <div class="input-group input-group-sm mb-3">

                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-names"> First Name | Last Name</span>
                            </div>
                            <input name="name" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required="">
                            <input name="surname" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required="" >

                        </div>

        <!-- ------------------------------------ EMAIL  ------------------------------------------------------------------>

            <!-- TODO : input type = email instead of text -->

                        <div class="input-group input-group-sm mb-3 ">

                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-mail" >E-mail</span>
                            </div>

                            <input name="text" type="email" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required="">

                        </div>

        <!-- ------------------------------------ DATE  ------------------------------------------------------------------>


                        <div class="input-group input-group-sm mb-3 ">

                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-date">Date</span>
                            </div>


                            <input name="date" id="reservationDay" type="date" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"
                                   style="font-size: 1em" required="" onchange="check()" />
                            <input type="boolean" id="DateValidity" value="false" hidden = "true" />

                        </div>

        <!-- ------------------------------------ GUESTS AND CONFIRM  ------------------------------------------------------------------>


                        <div class="input-group input-group-sm mb-3 ">

                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputGroup-guest">Total Guests</span>
                            </div>

                            <input type="number" id="guestsNumber" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"
                                   style="font-size: 1em" required="" onchange="checkGuests(300)" />
                            <input type="boolean" id="GuestValidity" value="false" hidden = "true" />

                        </div>

                        <input class="btn btn-dark" id="bookButton"  type="submit" value="Book &raquo;">

                    </form>

                    </div>


<!-- ============= CATALOGUE =============================================================================================-->


                    <div class = "col col-md-2">

                        <h1 class = "display-4" style = "padding-bottom:0.5em">Catalogue</h1>
                        <p id="descCatalogue">Want to see what we offer in our catalogue? Click on the button below and discover our dishes,from startes to desserts ! </p>
                        <form  action="../catalogue" method="post">
                            <input class="btn btn-dark" id="CatalogueBtn"  type="submit" value="Go to Catalogue&raquo;">
                        </form>
                    </div>

<!-- ============= RESERVATIONS ===============================================================================================-->


                    <div class="col col-md-2">
                        <h1 class = "display-4" style = "padding-bottom:0.5em">Reservations</h1>
                        <p id="descReservation">You made a reservation and now you want to see it? Nothing easier! Click on the button below to view your hires.</p>

                        <form action="../reservations" method="post">

                            <input class="btn btn-dark" id="ReservationsBtn"   type="submit" value="Go to Reservations&raquo;">

                        </form>


                    </div>


                </div>


            </div>


        <!-- ============= BOOTSTRAP SCRIPTS =======================================================================-->


            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>


        <!-- ============= RESERVATION DAY CHECK SCRIPT =======================================================================-->

        <script type="text/javascript">


            function isLater( date1, date2 ){

                if ( date1 > date2 )
                    return true ;
                else
                    return false;

            }

            function isEqual (date1, date2){

                if ( date1.getDate() == date2.getDate() && date1.getMonth() ==date2.getMonth() && date1.getFullYear() == date2.getFullYear() )
                    return true;
                else
                    return false ;

            }

            function tryDate(reservationDay, date){

                var today = new Date();

                if (isLater(reservationDay,today)){
                    if (! isEqual(reservationDay,date))
                        return true;
                }else
                    return false;


            }

            function checkValidity(reservationArray) {

                var tmp = document.getElementById("reservationDay").value;
                var reservationDay = new Date(tmp) ;
                var dates = reservationArray;
                var validity = undefined ;

                for (var i=0 ; i<2 ; i++) {

                    if (!tryDate(reservationDay, dates[i]))
                        validity=false ;

                }

                if (validity== undefined)
                    validity = true ;
                document.getElementById("DateValidity").value=validity;

                return validity;


            }

            function check(){

                var dates = new Array (new Date("2018-06-25"),new Date("2018-06-30"));
                if (checkValidity(dates) == false )
                    alert("ATTENTION!\nThe selected day is not free or is not valid. Please select another date." );

            }

        </script>

        <!-- ======================== GUEST CHECK SCRIPT ==================================================================-->

        <script type="text/javascript">

            function checkGuests(nCovers){

                var nGuests = document.getElementById("guestsNumber").value;
                var validity = undefined ;


                if (nGuests <= 0 || nGuests > nCovers ){
                    alert("ATTENTION!\nInsert a valid number of guests, from 1 up to " + nCovers );
                    validity = false;
                }
                else
                    validity = true;

                document.getElementById("GuestValidity").value = validity;
            }


        </script>



        </body>

</html>