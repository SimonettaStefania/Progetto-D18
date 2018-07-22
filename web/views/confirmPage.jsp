<!-- CONFIRM PAGE : this page confirms to the user that the reservation has been made successfully and shows him
     the reservation ID. He can use the ID to view his resrevations in the reservation page -->

<%@ page import="restaurant.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Confirm page</title>

        <!-- -------------------------------------- CSS LINK --------------------------------------------------- -->

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="../stylesheets/ConfirmTemplateStyle.css">

    </head>

    <body background ="../img/background.jpg" >

    <!-- -------------------------------------- NAVBAR --------------------------------------------------- -->

            <jsp:include page="navbar.jsp"/>

    <!-- -------------------------------------- TEXT --------------------------------------------------- -->

            <div class="jumbotron" id="confirmJumbotron" style = "text-align: center">
                <div >
                    <h1>Your reservation has been successfully made!</h1>
                    <br/>
                    <%  Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
                        out.print("<h3>Reservation ID: &emsp;" + reservation.getReservationCode() + "</h3>");   %>

                    <br/>
                    You can use your e-mail and this reservation ID to see your reservation. Thank you, and see you soon!
                </div>

                <br/>

    <!-- -------------------------------------- BTN BACK HOME--------------------------------------------------- -->

                <form action="/home" method="post" >
                    <input type="submit" class="btn btn-danger btn-lg" value="Home">
                </form>

            </div>
    </body>
</html>