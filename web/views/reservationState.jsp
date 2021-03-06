<!-- RESERVATION STATUS PAGE : this page allows the customer to view the menu he created for the reservation
     and to add a new one ( customized or optimized according to the budget ) -->

<%@ page import="menu.Menu" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="restaurant.Reservation" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>


        <head>

            <meta name="viewport" content="width=device-width, initial-scale=1">

            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
            <link rel="stylesheet" href="../stylesheets/ReservStateStyle.css">

            <title>Reservation State </title>

            <!-- This method creates the list of dishes included in a menu , which is specified with its name, price ;
                 The number of people to whom the menu is addressed is shown too -->

            <%  Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
                String dateString = new SimpleDateFormat("dd/MM/yyyy").format(reservation.getEventDate()); %>
            <%!
                private String shortString(Menu menu) {

                    String cost = String.format("%.2f", menu.getMenuCost());
                    StringBuilder s = new StringBuilder("<b>Price : </b> &euro; ").append(cost);
                    s.append( "&emsp;&emsp; <b>People  : </b> ").append(menu.getnMenuGuests());

                    for (MenuElement el : menu.getMenuElementsList())
                        s.append("<br/> - ").append(el.getName());
                    return s.toString();

                }
            %>

        </head>


        <body background="../img/background.jpg">


            <jsp:include page="navbar.jsp"/>

                <div class="jumbotron" style="background-color:#f1f1f1d1; padding:2%; margin-top:3%; margin-left:5%;margin-right:5%">

            <!-- ------------- TITLE , DATE AND TOTAL GUESTS  ------------------------------------------------------------------------->



                    <h1 style="margin-left: 7%; color: black">Reservation State </h1>
                    <h6 style="margin-left: 7%; color: black">Date : <%=dateString%>  Total Guests : <%=reservation.getnGuests()%></h6>



                    <div class="row"  id="row1_pg2">

            <!-- ------------- MENUS ADDED FOR THE RESERVATION ------------------------------------------------------------------------->


                        <div class="col-md-5">
                            <ul class="list-group mb-3" style="margin-left: 7%; margin-top: 2%; width: 100%">

                                <div class="card-header bg-dark text-white" style="width: 100%; text-align: center;">
                                    <b>Added Menu</b>
                                </div>

                                <div id="accordion" >



                    <!------------------ For each menu, create an element with the menu details ------------------------>

                                    <%  int n = 0;
                                        for (Menu menu : reservation.getCreatedMenu()) {  %>

                                    <div class="card" >
                                        <div class="card-header" id="card<%=n%>">

                                            <div class="row">

                                                <div class="col-9">
                                                    <button class="btn collapsed btt head" style="background-color: transparent;color: white;" data-toggle="collapse" data-target="#description<%=n%>" aria-controls="description<%=n%>">
                                                        <%=menu.getName()%>
                                                    </button>
                                                </div>

                                                <div class="col-3">
                                                    <form action="/status" method="post">
                                                        <input type="hidden" name="backToStatus" value="rem-menu">
                                                        <input type="hidden" name="removedMenu" value="<%=n%>">
                                                        <button class="btn fa fa-trash" style="float: right; color: white; background-color: transparent"></button>
                                                    </form>
                                                </div>

                                            </div>

                                        </div>

                                        <div id="description<%=n%>" class="collapse" aria-labelledby="card<%=n%>" data-parent="#accordion">
                                            <div class="card-body">
                                                <small class="text-muted"><%=shortString(menu)%></small>
                                            </div>
                                        </div>

                                    </div>

                                    <%           n++;
                                    }  %>

                                </div>

                            </ul>
                        </div>


         <!-- ------------- CHOICES TO CREATE A NEW MENU ------------------------------------------------------------------------->


                        <div class="col-md-5" style=" margin-left:12%; margin-top: 1%">
                            <span style="font-size: larger; font-style: inherit"><b>You can create your own menu or let us do: it will be budget-optimized!</b></span>

                            <!-- Customized menu-->

                            <form action="/selection" method="post">
                                <br>
                                <input type="submit" class="btn" style="background:#6576a5; color: white; width: 100%" value="Create your own Menu &raquo;">
                                <br>
                            </form>

                            <div style="font-size: larger; text-align: center;">OR</div>
                            <br>

                            <!-- Optimized menu -->

                            <form action="/optimize" method="post">

                                <div class="row">

                                    <div class="form-group mb-3 col-sm-6">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="lab1" style="background-color:#6576a5;color: white">Budget</span>
                                            <input name="budget" type="number" class="form-control" required min="20" placeholder="Insert budget" aria-describedby="lab1">
                                        </div>
                                    </div>

                                    <div class="form-group mb-3 col-sm-6">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="lab2" style="background-color:#6576a5;color: white">People n.</span>
                                            <input name="people" type="number" class="form-control" required min="1" max = "<%=reservation.getnGuests()%>"
                                                   title="How many menus of this type do you want to order?" placeholder="Insert number" aria-describedby="lab2">
                                        </div>
                                    </div>

                                </div>

                                <input type="submit" class="btn" style="background:#6576a5; color: white; width: 100%" value="Create a budget optimized Menu &raquo;">

                            </form>

                        </div>

                    </div>

                        <br/>


           <!-------------------------- NEXT AND CANCEL BUTTONS ---------------------------------------------------------->

                        <div class="row">
                            <div class="col">

                                <form action="/home" method="post" style=" margin-left: 19.3%">
                                    <input type="hidden" name="backToHome" value="true">
                                    <input type="submit" class="btn btn-danger btn-lg" id="btnBack" value=" &laquo; Cancel">
                                </form>

                            </div>

                            <div class="col">

                                <form action="/recap" method="post" style=" margin-left: 62%; margin-top:0.9%;">
                                    <input type="submit" class="btn btn-lg btn-success" style="width:50%" value="Next &raquo;">
                                </form>

                            </div>
                        </div>

                </div>


        <!-- ------- BOOTSTRAP SCRIPTS ----------------------------------------------------------------------------------->


                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>


        </body>
</html>
