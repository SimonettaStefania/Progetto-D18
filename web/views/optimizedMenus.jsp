<%@ page import="menu.Menu" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="restaurant.Reservation" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Optimized menu</title>

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

    <!-- -------------------------------------- CSS LINK --------------------------------------------------- -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/RecapTemplateStyle.css">

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

<!-- -------------------------------------- TITLE --------------------------------------------------- -->

<div class="jumbotron" id="mainJumbotronRecap">

    <h1 style="margin-right: 27%; text-align: center">Optimized Menus</h1>
    <br/>

    <div class="row">

        <div class="col-12 col-md-9">

            <!-- -------------------------------------- OPTIMIZED MENUS --------------------------------------------------- -->

            <div class="jumbotron" id="menuJumbotronRecap">
                <div class="tab-content" id="nav-tabContent">

            <%   int n = 0;
                 for (Menu menu : reservation.getOptimizedMenu()) {  %>
                    <div class="tab-pane fade show <%if (n == 0) out.print("active");%>" id="list-menu<%=n%>" role="tabpanel">

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

                                <form action="/status" method="post">

                                    <input type="hidden" name="backToStatus" value="sel-opt-menu">
                                    <input type="hidden" name="code" value="<%=n%>">
                                    <input type="submit" class="btn btn-success btn-lg" style="margin-left: 40% " value="Select menu &checkmark;">

                                </form>
                            </div>
                        </div>
                    </div>
            <%      n++;
                 }   %>

                </div>
            </div>
        </div>

        <!-- -------------------------------------- LIST MENU INTERACTIVE--------------------------------------------------- -->

        <div class="col-6 col-md-3 sidebar" id="sidebar">
            <div class="list-group" id="list-tab" role="tablist">

            <%  int i = 0;  %>
            <%  for (Menu menu : reservation.getOptimizedMenu()) {  %>
                    <a class="list-group-item list-group-item-action <%if (i == 0) out.print("active");%>" data-toggle="list" href="#list-menu<%=i%>" role="tab"><%=menu.getName()%></a>
                <%  i++;
                }   %>
            </div>

            <br/>

            <!-- -------------------------------------- BACK BTN --------------------------------------------------- -->

            <div class="row" style="margin-right: 20%">

                <form action="/status" method="post">
                    <input type="hidden" name="backToStatus" value="back">
                    <input type="submit" class="btn btn-lg" style="margin-left: 20%" id="btnBack" value="&laquo; Back">

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

<footer>
    <p>&copy; Lisanti non è il titolare del corso</p>
</footer>

