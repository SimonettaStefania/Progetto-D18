<%@ page import="menu.Menu" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="restaurant.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Recap</title>

<!-- -------------------------------------- CSS LINK --------------------------------------------------- -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/RecapTemplateStyle.css">

    <%  Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");  %>
    <%!
        private String leftString(Menu menu) {
            StringBuilder s = new StringBuilder();
            for (MenuElement el : menu.getMenuElementsList())
                s.append(" - ").append(el.getName()).append("<br/>");
            s.append("<br/>People:<br/>Total price:");
            return s.toString();
        }
        private String rightString(Menu menu) {
            StringBuilder s = new StringBuilder();
            for (MenuElement el : menu.getMenuElementsList())
                s.append("&euro; ").append(String.format("%.2f", el.getPrice())).append("<br/>");
            s.append("<br/>").append(menu.getnMenuGuests());
            s.append("<br/>&euro; ").append(String.format("%.2f", menu.getMenuCost()));
            return s.toString();
        }
    %>
</head>

<body background ="../img/background.jpg" style="background-repeat: no-repeat">

<!-- -------------------------------------- NAVBAR --------------------------------------------------- -->

<nav class="navbar navbar-expand-md navbar-dark bg-dark">

    <a class="navbar-brand" href="#">Project D-18</a>
    <button class="navbar-toggler p-0 border-0" type="button"></button>

    <div class="navbar-collapse offcanvas-collapse">
        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="/home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/reservations">Reservations</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/catalogue">Catalogue</a>
            </li>
        </ul>
    </div>
</nav>

<!-- -------------------------------------- RESERVATION INFO (MAIN) --------------------------------------------------- -->

<div class="jumbotron" id="mainJumbotronRecap">

    <h1 style="text-align: center; margin-right: 27%">Reservation recap</h1>
    <br/>

    <div class="row">

        <div class="col-md-9">

            <!-- -------------------------------------- MENU INFO --------------------------------------------------- -->

            <div class="jumbotron" id="menuJumbotronRecap">

                <div class="tab-content" id="nav-tabContent">

                <%  int i = 0;  %>
                <%  for (Menu menu : reservation.getCreatedMenu()) {  %>
                    <div class="tab-pane fade show <%if (i == 0) out.print("active");%>" id="list-menu<%=i%>" role="tabpanel">
                        <div class="card bg-light mb-3">
                        <div class="card-header text-white"><%=menu.getName()%></div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-9">
                                    <%=leftString(menu)%>
                                </div>
                                <div class="col-3">
                                    <%=rightString(menu)%>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                    <%  i++;  %>
                <%  }  %>

                </div>

                <!-- -------------------------------------- TABLE INFO RESERVATION (total cost & guest) --------------------------------------------------- -->

                <table class="table table-borderless" style="margin-bottom: 0">

                    <tr>
                        <th scope="row"></th>

                        <td><button type="button" class="btn btn-lg" id="totalPerson" style="margin-left: 40%"> Total person: <span class="badge"><%=reservation.calculateGuests()%></span></button></td>
                        <td><button type="button" class="btn btn-lg" id="totalCost" style="margin-right: 40%">Total cost: <span class="badge"><%=String.format("%.2f &euro;", reservation.getReservationCost())%></span></button></td>
                    </tr>

                </table>
            </div>
        </div>

        <!-- -------------------------------------- LIST MENU INTERACTIVE--------------------------------------------------- -->

        <div class="col-md-3 sidebar" id="sidebar">
            <div class="list-group" id="list-tab" role="tablist">

                <%  i = 0;  %>
                <%  for (Menu menu : reservation.getCreatedMenu()) {  %>
                    <a class="list-group-item list-group-item-action <%if (i == 0) out.print("active");%>" data-toggle="list" href="#list-menu<%=i%>" role="tab"><%=menu.getName()%></a>
                    <%  i++;  %>
                <%  }  %>

            </div>
            <br/>

            <!-- -------------------------------------- FINAL BTN --------------------------------------------------- -->

            <%if (!reservation.checkPeople()) {%>
                <div class="alert alert-danger" role="alert" style="margin-right: 12%">
                    Number of people does not match: please check if a menu is present for each guest.
                </div>
            <%}%>

            <div class="row">
                <form action="/status" method="post" class="col-3">
                    <input type="hidden" name="backToStatus" value="true">
                    <input type="submit" class="btn btn-lg" id="btnBack" value="&laquo; Back">
                </form>

                <form action="/confirm" method="post" class="col-3">
                    <input type="submit" class="btn btn-success btn-lg" value="Confirm &checkmark;" <%if (!reservation.checkPeople()) out.print("disabled");%>>
                </form>
            </div>

        </div>

    </div>

</div>

<!-- -------------------------------------- BOOTSTRAP SCRIPT --------------------------------------------------- -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>
