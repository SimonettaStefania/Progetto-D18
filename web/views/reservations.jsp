<%@ page import="restaurant.Reservation" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Reservations review</title>

    <%!
        private String summary(Reservation r) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            StringBuilder sb = new StringBuilder(r.getCustomerNameSurname());
            sb.append("<br/>").append(sdf.format(r.getEventDate())).append(" - ").append(r.getnGuests())
              .append(" guests<br/>Total price: &euro; ").append(String.format("%.2f", r.getReservationCost()));

            /*
            for (Menu m : r.getCreatedMenu()) {
                sb.append("<br/><br/>Price: &euro; ").append(String.format("%.2f", m.getMenuCost()))
                  .append("&emsp;&emsp; People: ").append(m.getnMenuGuests());
                for (MenuElement el : m.getMenuElementsList())
                    sb.append("<br/> - ").append(el.getName());
            }
            */

            return sb.toString();
        }
    %>

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
                <a class="nav-link" href="/home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/reservations">Reservations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/catalogue">Catalogue</a>
            </li>
        </ul>
    </div>
</nav>


<!-- ----------------------------------------------- TITLE, EMAIL AND BUTTON ------------------------------------------->


<div class="row"  id="r1" >
    <div class="col">

        <h1 style="margin-left: 10%">Your Reservations</h1>
        <br>
        <h6 style="margin-left: 10%; margin-right: 5%">Please insert your e-mail and the reservation ID to see the details.</h6>
        <form action="../reservations" method="post">

            <div class="input-group mb-3" style="width: 60%; margin-left: 10%;">
                <div class="input-group-prepend" >
                    <span class="input-group-text" style="background-color:#6576a5; color: white;">E-mail</span>
                </div>
                <input type="text" name="email" class="form-control">
            </div>

            <div class="input-group mb-3" style="width: 60%; margin-left: 10%;">
                 <div class="input-group-prepend">
                     <span class="input-group-text" style="background-color:#6576a5; color: white;">Code</span>
                 </div>

                 <input type="text" name="res-id" class="form-control">
             </div>

            <input type="submit" class="btn btn-dark" id="confirm" value="Confirm" style="width: 15%;">
        </form>
    </div>

<!------------------------------------------------------------ COLLAPSING LIST ------------------------------------------------------------------- -->

    <div class="col">
        <div class="container" id="container" >
            <div id="accordion">

            <%Reservation pickedReservation = (Reservation) request.getAttribute("pickedReservation");
              if (pickedReservation != null) {%>
                <div class="card" >
                    <div class="card-header">
                        <span style="color: white"><b><%=pickedReservation.getReservationCode()%></b></span>
                    </div>

                        <div class="card-body">
                            <%=summary(pickedReservation)%>

                            <form action="../reservations" method="get">
                                <input type="submit" class="btn btn-sm btn-dark"  value="Delete" style="float: right;">
                            </form>

                        </div>
                </div>
              <%}%>

            </div>
        </div>
    </div>
</div>


</body>
</html>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
