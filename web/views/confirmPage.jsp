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

<!-- -------------------------------------- TEXT --------------------------------------------------- -->

<div class="jumbotron" id="confirmJumbotron">
    <div style="text-align: center;">
        <h1>Your reservation has been successfully made!</h1>
        <br/>

        <%  Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
            out.print("<h3>Reservation ID: &emsp;" + reservation.getReservationCode() + "</h3>");   %>

        <br/>
        Thank you, and see you soon!
    </div>

    <br/>

    <!-- -------------------------------------- BTN BACK HOME--------------------------------------------------- -->

    <form action="/home" method="post" style="text-align: center">
        <input type="submit" class="btn btn-danger btn-lg" value="Home">
    </form>

</div>
</body>
