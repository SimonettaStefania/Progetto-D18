<!-- Welcome page : this page is the home of the application, where the customer can book a reservation , see the ones
he has already created and view the restaurant catalogue -->

<%@ page import="restaurant.Restaurant" %>
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


        <body background="../img/background.jpg"  >

            <jsp:include page="navbar.jsp"/>

        <!-- ============= TITLE AND DESCRIPTION =======================================================================-->


            <div class = "jumbotron jumbotron-theme1">
                <div id = "welcome" class = "row justify-content-around" >
                    <div class = "col-8 ">

                        <h1 class="display-4">Welcome to Project D-18 book page!</h1>
                        <p id="description">
                        This website will guide you through the creation of a reservation: complete the form below and start organizing your event !
                        You can create different types of menus according to the guests' tastes and needs. For each menu, you have to specify
                        how many people will order it : in the end, the total number of ordered menus will correspond to the total number of guests .

                        </p>

                    </div>
                </div>
            </div>

        <!-- ============= FORM CATALOGUE AND RESERVATIONS =============================================================-->

            <div class="jumbotron jumbotron-theme2">

                <div class="row">
                    <div class = "col col-md-6">
                        <h1 class = "display-4">New Reservation</h1>

                        <p>Want to make a new reservation? Compile the form below and organize your event!</p>

                        <!-- --------------------------- NAME AND SURNAME --------------------------------------------->

                        <form action="../status" method="post">
                            <div class="input-group input-group-sm mb-3">

                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-names"> First Name | Last Name</span>
                                </div>
                                <input name="name" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required="" />
                                <input name="surname" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required="" />

                            </div>

                        <!-- ------------------------------------ EMAIL  ---------------------------------------------->

                            <div class="input-group input-group-sm mb-3 ">

                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-mail" >E-mail</span>
                                </div>

                                <input name="email" type="email" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required=""/>

                            </div>

                        <!-- ------------------------------------ DATE  ----------------------------------------------->


                            <div class="input-group input-group-sm mb-3 ">

                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-date">Date</span>
                                </div>


                                <input name="date" id="reservationDay" type="date" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" required=""/>


                            </div>

                        <!-- ------------------------------------ GUESTS NUMBER  -------------------------------------->


                            <div class="input-group input-group-sm mb-3 ">

                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroup-guest">Total Guests</span>
                                </div>

                                <input name ="guestsNumber" type="number" id="guestsNumber" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm"
                                       min = "1" max = "<%=Restaurant.getStaticCovers()%>"/>


                            </div>
                        <!-- ------------------------------------ CONFIRM BUTTON  ------------------------------------->

                            <input class="btn btn-dark" id="bookButton"  type="submit" value="Book &raquo;" onclick="dateControl()" />
                            <input name = "validity" type="hidden" id="Validity" value="0"  />

                        </form>

                    </div>


        <!-- ============= CATALOGUE ===================================================================================-->


                    <div class = "col col-md-3">
                        <h1 class = "display-4">Catalogue</h1>
                        <p>Want to see what we offer in our catalogue? Click on the button below and discover our dishes,from startes to desserts!</p>

                        <form action="/catalogue" method="get">
                            <input class="btn btn-dark" type="submit" value="Go to Catalogue &raquo;">
                        </form>
                    </div>

        <!-- ============= RESERVATIONS ================================================================================-->


                    <div class="col col-md-3">
                        <h1 class = "display-4">Reservations</h1>
                        <p id="descReservation">You made a reservation and now you want to see it? Nothing easier! Click on the button below to view your hires.</p>

                        <form action="/reservations" method="get">
                            <input class="btn btn-dark" id="ReservationsBtn"   type="submit" value="Go to Reservations &raquo;">
                        </form>

                    </div>


                </div>
            </div>


        <!-- ============= BOOTSTRAP SCRIPTS ===========================================================================-->


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>


        <!-- ============= RESERVATION DAY CHECK SCRIPT ================================================================-->

        <script type="text/javascript">

            function isLater( date1, date2 ){
                if ( date1 > date2 )
                    return true ;
                else
                    return false;
            }

            function dateControl(){
                var today = new Date();
                var tmp = document.getElementById("reservationDay").value ;
                var reservationDate = new Date(tmp);

                if ( isLater(reservationDate, today))
                    document.getElementById("Validity").value = 1 ;
                else {
                    document.getElementById("Validity").value = 0;
                    alert("ATTENTION!\n The selected day is not valid. Please select another date. ")
                }

            }

        </script>


        </body>

</html>