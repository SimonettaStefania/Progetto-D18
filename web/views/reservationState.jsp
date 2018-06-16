<%@ page import="menu.Menu" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="restaurant.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <link rel="stylesheet" href="../stylesheets/RecapTemplateStyle.css">

    <title>Reservation State </title>

    <%  Reservation reservation = (Reservation) request.getAttribute("reservation");  %>
    <%!
        private String shortString(Menu menu) {
            menu.calculateMenuCost();
            StringBuilder s = new StringBuilder("Price: " + menu.getMenuCost() + "&emsp;&emsp; People: " + menu.getnMenuGuests());
            for (MenuElement el : menu.getMenuElementsList())
                s.append("<br/> - ").append(el.getName());
            return s.toString();
        }
    %>
</head>
<body background="../img/background.jpg">

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#">Project D-18</a>
    <div class="navbar-collapse offcanvas-collapse" id="navbarProject">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
            <li class="nav-item"><a class="nav-link active" href="#">Reservations</a></li>
            <li class="nav-item"><a class="nav-link" href="#">Catalogue</a></li>
        </ul>
    </div>
</nav>


<div class="jumbotron" style="background-color:#f1f1f1d1; padding:2%; margin-top:3%; margin-left:5%;margin-right:5%">
    <h1 style="margin-left: 7%; color: black">Reservation State</h1>
    <div class="row"  id="row1_pg2">

        <div class="col-md-5">
            <ul class="list-group mb-3" style="margin-left: 10%; padding: 2%; margin-top: 2%; width: 100%">
                <div class="card-header bg-dark text-white" style="width: 100%; text-align: center;"><b>Added Menu</b></div>

            <%  int n = 0;      // TODO: rendere la lista dei menu delle tendine a (s)comparsa
                for (Menu menu : reservation.getCreatedMenu()) {  %>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><%=menu.getName()%></h6>
                            <small class="text-muted"><%=shortString(menu)%></small>
                        </div>
                        <form action="/selection" method="post">
                            <input type="hidden" value="<%=n%>">
                            <button class="btn" style="background-color: #6576a5; color: white;">Modify</button>
                        </form>
                    </li>
            <%      n++;
                }  %>

            </ul>
        </div>

        <div class="col-md-5" style=" margin-left:12%; margin-top: 2%">
            <span style="font-size: larger; font-style: inherit">You can create your own menu or we can do it for you: you've just to insert the budget!</span>

            <form action="/selection" method="post">
                <br>
                <input type="submit" class="btn" style="background:#6576a5; color: white; width: 100%" value="Create your own Menu &raquo;">
                <br>
            </form>

            <div style="font-size: larger; text-align: center;">OR</div>
            <br>
            <form action="/optimize" method="post">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="addon1" style="background-color:#6576a5;color: white; width: 100%">Budget</span>
                    </div>
                    <input name ="budget" type="number" class="form-control" min="20" placeholder="Insert Budget" aria-label="" aria-describedby="basic-addon1" style="width:50%">
                </div>
                <input type="submit" class="btn" style="background:#6576a5; color: white; width: 100%" value="Create a budget optimized Menu &raquo;">
            </form>

        </div>
    </div>

        <br/>

        <div class="row">
            <div class="col">
                <form action="/home" method="post" style="  margin-left: 19.3%">
                    <input type="hidden" name="backToHome" value="true">
                    <input type="submit" class="btn btn-danger btn-lg" id="btnBack" value=" &laquo; Cancel">
                </form></div>

            <div class="col">
            <form action="/recap" method="post" style="margin-left: 62%;">
                <input type="submit" class="btn btn-lg btn-success" style="width:50%" value="Next &raquo;">
            </form></div>
        </div>

</div>

</body>
</html>
