<%--
  Created by IntelliJ IDEA.
  User: fenicottero
  Date: 12/06/18
  Time: 12.11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Confermation</title>

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

    <div class="row">

        <div style="margin-left: 15%"><h1>Your reservation has been made successfully!</h1>
            <br/>
            <center>Thank you, we will see soon!</center>
        </div>

    </div>

    <hr class="my-4">
    <br/>

    <!-- -------------------------------------- BTN BACK HOME--------------------------------------------------- -->

    <div class="row" style="margin-left: 43%" >

        <form action="/home" method="post">
            <input type="hidden" name="backToHome" value="true">
            <input type="submit" class="btn btn-danger btn-lg" value="&laquo; Back Home">
        </form>

    </div>

</div>
</body>

<footer>
    <p>&copy; Lisanti non è il titolare del corso</p>
</footer>


