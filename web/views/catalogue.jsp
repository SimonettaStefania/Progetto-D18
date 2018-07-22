<!-- CATALOGUE PAGE : this page shows dishes and drinks offered by the restaurant ; for each category
     (starters, first courses, main courses, desserts, drinks) creates a list of dishes where the user
      can read name, price, ingredients, allergens ; it's also shown if each dish is vegetarian , vegan
      or gluten free ( filters )-->


<%@ page import="restaurant.Catalogue" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="menu.DishType" %>
<%@ page import="restaurant.Restaurant" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

        <head>

                <!-- Page informations ( name, CSS styles )-->

                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

                <title>Catalogue Page</title>

                <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css">
                <link rel="stylesheet" href="../stylesheets/CatalogueStyle.css">

                <!-- List of dishes and drinks ( catalogueElement ) taken from the restaurant -->

                <%  Catalogue catalogue = Restaurant.getRestaurantInstance().getDishesCatalogue();
                    ArrayList<MenuElement> catalogueElement = catalogue.getDishes();
                    catalogueElement.addAll(catalogue.getDrinks());
                    DishType[] types = DishType.values();
                    %>

                <!-- Method that creates a description for each dish with its price , ingredients, allergens, and filters ;
                     For the drinks, only allergens are not shown-->

                <%!
                    private String itemDetails(MenuElement element) {
                        StringBuilder s = new StringBuilder("<b>Price:</b> &euro; " + String.format("%.2f", element.getPrice()));
                        s.append("<br><b>Allergens:</b> <br>").append(element.showAllergenes());

                        if ( element.getType()!= DishType.DRINK ) {
                            s.append("<br><b>Ingredients:</b> <br>").append(element.showIngredients());
                            s.append("<br><b>Filters:</b> <br> ").append(element.showFilters());
                        }

                        return s.toString();

                    }
                %>

        </head>

        <body background ="../img/background.jpg" >

            <!-- Navigation Bar on the top of the page is included in the page -->

                <jsp:include page="navbar.jsp"/>

            <!-- Content of the page : categories and dishes -->


            <div class="jumbotron" id= "Jumbotron">

                <h1 style="margin-left: 8%">Catalogue</h1>
                <div class="row">
                    <div class="col-md-3" id="sidebar">

                        <p style="margin-left:2%">Click on the dish name to view its details :</p>

   <!-- ------------------------------------------------- CATEGORIES and HOME BUTTON --------------------------------------------------------------------->

                        <div class="list-group" id="list-tab" role="tablist">

                            <% for (int i=0 ; i < types.length ; i++) {
                                String category = types[i].toString();
                            %>

                                <a class="list-group-item list-group-item-action <%if (i==0) out.print("active");%>" id="label-<%=category%>" data-toggle="list" href="#list-<%=category%>" role="tab" >
                                    <%=category%>
                                </a>


                            <%}%>

                        </div>

                        <form action="/home" method="post">
                            <input type="submit" class="btn btn-success btn-lg" value="&laquo; Home" style="margin-top:6%"/>
                        </form>

                    </div>

  <!-- -------------------------------------------------- DISHES DETAILS ------------------------------------------------------------------------>
  <!-- For each category , takes all the elements in the catalogue that belongs to it and create a card element with the name of the dish
       and a description ( itemDetails method ) -->
                    <div class="col-md-9">

                        <div class="tab-content" id="nav-tabContent" >

                            <% for (int i=0 ; i < types.length ;  i++){
                                DishType nameType = types[i]; %>

                                <div class="tab-pane fade show <%if (i==0) out.print("active");%> " id="list-<%=nameType.toString()%>" role="tabpanel" aria-labelledby="label-<%=nameType.toString()%>">

                                    <div id="accordition<%=nameType.toString()%>">

                                        <%for ( MenuElement item : catalogueElement){
                                            if (item.getType().equals(nameType)) {%>

                                            <div class = "card" >
                                                <div class="card-header" id="<%=item.getElementCode()%>">
                                                    <button class="btn btn-info collapsed btt" data-toggle="collapse" data-target="#description<%=item.getElementCode()%>"
                                                            aria-controls="description<%=item.getElementCode()%>">
                                                        <%=item.getName()%>
                                                    </button>
                                                </div>

                                                <div id="description<%=item.getElementCode()%>" class="collapse " aria-labelledby="<%=item.getElementCode()%>" data-parent="#accordition<%=nameType.toString()%>">
                                                    <div class="card-body" style="font-size:13px;"><p><%=itemDetails(item)%></p></div>
                                                </div>

                                            </div>
                                    <%     }
                                        } %>
                                    </div>

                                </div>

                            <%}%>


                        </div>
                    </div>
                </div>


            </div>

        <!-- ----------------------------------------------- BOOTSTRAP SCRIPTS ----------------------------------------------------------------------------------- -->

                <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

                <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>

                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
                        integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>

        </body>

</html>