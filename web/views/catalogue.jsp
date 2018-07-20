<%@ page import="restaurant.Catalogue" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="menu.DishType" %>
<%@ page import="restaurant.Restaurant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Catalogue Page</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="../stylesheets/CatalogueStyle.css">

    <%  Catalogue catalogue = Restaurant.getRestaurantInstance().getDishesCatalogue();    %>
    <%!
        private String itemDetails(MenuElement element) {
            StringBuilder s = new StringBuilder("<b>Price:</b> &euro; " + String.format("%.2f", element.getPrice()));

            if ( element.getType()!= DishType.DRINK ) {
                s.append("<br><b>Ingredients:</b> <br>" + element.showDetails() + "<br><b>Allergens:</b> <br>" + element.showAllergenes());
                s.append("<br><b>Filters:</b> <br> " + element.showFilters());
            }else
                s.append("<br><b>Allergens:</b> <br>" + element.showAllergenes());

            return s.toString();

        }
    %>
</head>

<body background ="../img/background.jpg" >



<!-- ========== NAVBAR ======================================================================================================-->

<jsp:include page="navbar.jsp"/>





<!-- ========== CATEGORY LIST ======================================================================================================-->


<div class="jumbotron" id= "Jumbotron">
    <h1 style="margin-left: 8%">Catalogue</h1>
    <div class="row">
        <div class="col-md-3" id="sidebar">

            <p style="margin-left:2%">Click on the dish name to view its details :</p>



            <div class="list-group" id="list-tab" role="tablist">

                <a class="list-group-item list-group-item-action active" id="label-starters" data-toggle="list" href="#list-starters" role="tab" >Starters</a>
                <a class="list-group-item list-group-item-action" id="label-first" data-toggle="list" href="#list-first" role="tab" >First Courses</a>
                <a class="list-group-item list-group-item-action" id="label-main" data-toggle="list" href="#list-main" role="tab" >Main Courses</a>
                <a class="list-group-item list-group-item-action" id="label-desserts" data-toggle="list" href="#list-desserts" role="tab" >Desserts</a>
                <a class="list-group-item list-group-item-action" id="label-drinks" data-toggle="list" href="#list-drinks" role="tab" >Drinks</a>

            </div>

            <form action="/home" method="post">
                <input type="submit" class="btn btn-success btn-lg" value="&laquo; Home" style="margin-top:6%">
            </form>
        </div>

        <!-- =====================================================DISHES ELEMENTS ===========================================================-->

        <div class="col-md-9">

            <div class="tab-content" id="nav-tabContent" >

                <!-- -------------------------------------------------------- STARTERS -------------------------------------------------------->

                <div class="tab-pane fade show active" id="list-starters" role="tabpanel" aria-labelledby="label-starters">

                    <div id="accorditionStarter">

                        <%  for (MenuElement item : catalogue.getDishes()) {
                                if (item.getType().equals(DishType.STARTER)) {  %>
                        <div class="card">
                            <div class="card-header" id="<%=item.getElementCode()%>">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#description<%=item.getElementCode()%>"
                                            aria-controls="description<%=item.getElementCode()%>"><%=item.getName()%></button>
                                </h5>
                            </div>
                            <div id="description<%=item.getElementCode()%>" class="collapse " aria-labelledby="<%=item.getElementCode()%>" data-parent="#accorditionStarter">
                                <div class="card-body" style="font-size:13px;"><p><%=itemDetails(item)%></p></div>
                            </div>
                        </div>
                            <%  }
                            } %>

                    </div>

                </div>

                <!-- -------------------------------------------------------- FIRST COURSE -------------------------------------------------------->

                <div class="tab-pane fade " id="list-first" role="tabpanel" aria-labelledby="label-first">

                    <div class="accordion" id="accorditionFirst">

                        <%  for (MenuElement item : catalogue.getDishes()) {
                            if (item.getType().equals(DishType.FIRST_COURSE)) {  %>
                        <div class="card">
                            <div class="card-header" id="<%=item.getElementCode()%>">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#description<%=item.getElementCode()%>"
                                            aria-controls="description<%=item.getElementCode()%>"><%=item.getName()%></button>
                                </h5>
                            </div>
                            <div id="description<%=item.getElementCode()%>" class="collapse " aria-labelledby="<%=item.getElementCode()%>" data-parent="#accorditionFirst">
                                <div class="card-body" style="font-size:13px;"><%=itemDetails(item)%></div>
                            </div>
                        </div>
                        <%  }
                        } %>

                    </div>

                </div>

                <!-- -------------------------------------------------------- MAIN COURSE -------------------------------------------------------->

                <div class="tab-pane fade " id="list-main" role="tabpanel" aria-labelledby="label-main">

                    <div class="accordion" id="accorditionMain">

                        <%  for (MenuElement item : catalogue.getDishes()) {
                            if (item.getType().equals(DishType.MAIN_COURSE)) {  %>
                        <div class="card">
                            <div class="card-header" id="<%=item.getElementCode()%>">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#description<%=item.getElementCode()%>"
                                            aria-controls="description<%=item.getElementCode()%>"><%=item.getName()%></button>
                                </h5>
                            </div>
                            <div id="description<%=item.getElementCode()%>" class="collapse " aria-labelledby="<%=item.getElementCode()%>" data-parent="#accorditionMain">
                                <div class="card-body" style="font-size:13px;"><%=itemDetails(item)%></div>
                            </div>
                        </div>
                        <%  }
                        } %>
                    </div>

                </div>

                <!-- -------------------------------------------------------- DESSERTS -------------------------------------------------------->

                <div class="tab-pane fade " id="list-desserts" role="tabpanel" aria-labelledby="label-desserts">

                    <div class="accordion" id="accorditionDessert">

                        <%  for (MenuElement item : catalogue.getDishes()) {
                            if (item.getType().equals(DishType.DESSERT)) {  %>
                        <div class="card">
                            <div class="card-header" id="<%=item.getElementCode()%>">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#description<%=item.getElementCode()%>"
                                            aria-controls="description<%=item.getElementCode()%>"><%=item.getName()%></button>
                                </h5>
                            </div>
                            <div id="description<%=item.getElementCode()%>" class="collapse " aria-labelledby="<%=item.getElementCode()%>" data-parent="#accorditionDessert">
                                <div class="card-body" style="font-size:13px;"><%=itemDetails(item)%></div>
                            </div>
                        </div>
                        <%  }
                        } %>
                    </div>

                </div>

                <!-- -------------------------------------------------------- DRINKS -------------------------------------------------------->

                <div class="tab-pane fade " id="list-drinks" role="tabpanel" aria-labelledby="label-drinks">

                    <div class="accordion" id="accorditionDrink">

                        <%  for (MenuElement item : catalogue.getDrinks()) {    %>
                        <div class="card">
                            <div class="card-header" id="<%=item.getElementCode()%>">
                                <h5 class="mb-0">
                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#description<%=item.getElementCode()%>"
                                            aria-controls="description<%=item.getElementCode()%>"><%=item.getName()%></button>
                                </h5>
                            </div>
                            <div id="description<%=item.getElementCode()%>" class="collapse " aria-labelledby="<%=item.getElementCode()%>" data-parent="#accorditionDrink">
                                <div class="card-body" style="font-size:13px;"><%=itemDetails(item)%></div>
                            </div>
                        </div>
                        <%  }   %>

                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- =================== BOOTSTRAP SCRIPTS ======================================================================== -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

</body>

</html>