<!-- DISHES SELECTION PAGE : this page allows the user to create a customized menu ; he can apply filters to select
     vegan, vegetarian and celiac dishes or to select elements of the menu without some specified allergens. In this page
     the dishes the user selects are automatically added to the menu recap on the right side of the page ; he can also
     add a menu name and has to insert the number of people to whom the menu is addressed -->

<%@ page import="menu.DishType" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="restaurant.Catalogue" %>
<%@ page import="restaurant.Restaurant" %>
<%@ page import="menu.Allergen" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="restaurant.Reservation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
        <head>

            <title>Menu</title>

            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
                  integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
            <link rel="stylesheet" href="../stylesheets/SelectionTemplateStyle.css">


            <!-- ----------------- BOOTSTRAP, JAVASCRIPT and AJAX SCRIPTS -------------------------------------------- -->

            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"
                    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" crossorigin="anonymous"
                    integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" crossorigin="anonymous"
                    integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

            <!-- --------------------- Page scripts --------------------------------------------------------------------->

            <script>

                // Method to create the filters based on the checkboxes selected by the user

                <%  Catalogue catalogue = Restaurant.getRestaurantInstance().getDishesCatalogue();
                    Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

                    String filters[] = request.getParameterValues("filter");
                    boolean veg = false, vgt = false, cel = false;
                    if (filters != null) {
                        for (String f : filters) {
                            if (f.equalsIgnoreCase("veg"))
                                veg = true;
                            else if (f.equalsIgnoreCase("vgt"))
                                vgt = true;
                            else if (f.equalsIgnoreCase("cel"))
                                cel = true;
                        }
                    }

                    String allergens = "";
                    String selectedAllergens[] = request.getParameterValues("allergen");
                    if (selectedAllergens != null)
                        allergens = String.join("", selectedAllergens);

                    ArrayList<MenuElement> filteredList = catalogue.getFilteredList(veg, vgt, cel, allergens);

                    DishType[] types = DishType.values();
                    String[] category = {"Starters", "First-Courses", "Main-Courses", "Desserts", "Drinks"};    %>

                // Function to update the checkout box with the selected dishes and to show the allergens in the dropdown menu

                $(document).ready(function(){
                    $("input[name='selected-id']").on("change", function() {
                        var selectedList = [];
                        var pricesList = [];
                        $('input[name=\'selected-id\']:checked').each(function() {
                            var label = $(this).siblings('label');
                            selectedList.push(label.text());
                            pricesList.push(label.attr('data-price'));
                        });

                        var content = '';
                        for(var i = 0; i < selectedList.length; i++)
                            content += '<tr><th>' + selectedList[i] + '</th><td>' + pricesList[i] + '0 &euro;</td></tr>';
                        $('#checkout').html(content);
                    });

                    $('.dropdown-menu').on('click', function(e) {
                        e.stopPropagation();
                    });
                });
            </script>

            <!-- Method that creates a description for each dish with its price , ingredients, allergens, and filters ;
                For the drinks, only allergens are not shown-->


            <script>


                <%!
                    private String itemDetails(MenuElement element) {
                        StringBuilder s = new StringBuilder("<b>Price:</b> &euro; " + String.format("%.2f", element.getPrice()));
                        s.append("<br><b>Allergens:</b> <br>").append(element.showAllergenes());

                        if (element.getType()!= DishType.DRINK) {
                            s.append("<br><b>Ingredients:</b> <br>").append(element.showIngredients());
                            s.append("<br><b>Filters:</b> <br> ").append(element.showFilters());
                        }

                        return s.toString();

                    }
                %>

            </script>

        </head>

        <body background="../img/background.jpg">

                <jsp:include page="navbar.jsp"/>

                <br/>
                <div class="container">
                    <form action="/selection" method="post" class="jumbotron" style="background: #ffffff">

                        <!------------------------------ VEGAN, VEGETARIAN, CELIAC FILTERS ------------------------------>

                        <h2>Filters</h2>
                        <div class="custom-control custom-checkbox custom-control-inline">
                            <input type="checkbox" id="vegetarian" name="filter" class="custom-control-input" value="vgt" <% if (vgt) out.print("checked"); %>>
                            <label class="custom-control-label" for="vegetarian">Vegetarian</label>
                        </div>
                        <div class="custom-control custom-checkbox custom-control-inline">
                            <input type="checkbox" id="vegan" name="filter" class="custom-control-input" value="veg" <% if (veg) out.print("checked"); %>>
                            <label class="custom-control-label" for="vegan">Vegan</label>
                        </div>
                        <div class="custom-control custom-checkbox custom-control-inline">
                            <input type="checkbox" id="celiac" name="filter" class="custom-control-input" value="cel" <% if (cel) out.print("checked"); %>>
                            <label class="custom-control-label" for="celiac">Celiac</label>
                        </div>

                        <!------------------------------ ALLERGENS FILTERS ------------------------------------------------->


                        <div class="dropdown" style="display: inline">
                            <button class="btn btn-primary btn-styled dropdown-toggle" type="button" id="dropbtn" data-toggle="dropdown"
                                    aria-haspopup="true" aria-expanded="false" style="margin-left: 15%;">Allergens to avoid</button>

                            <div class="dropdown-menu" aria-labelledby="dropbtn">
                                <%for (Allergen elem : catalogue.getAllergens()) {%>
                                <div class="custom-control custom-checkbox" style="margin-left: 0.9em; margin-right: 0.9em">
                                    <input type="checkbox" id="<%=elem.getAllergenCode()%>" name="allergen" class="custom-control-input"
                                           value="<%=elem.getAllergenCode()%>" <% if (allergens.contains(elem.getAllergenCode())) out.print("checked"); %>>
                                    <label class="custom-control-label" for="<%=elem.getAllergenCode()%>"><%=elem.toString()%></label>
                                </div>
                                <%}%>
                            </div>
                        </div>

                        <!-- ------------------------- APPLY FILTERS BUTTON ----------------------------------------------->

                        <input type="submit" style="float: right;" class="btn btn-primary btn-styled" value="Apply">
                    </form>
                </div>

    <!-- ======================================== DISHES SELECTION =============================================================-->

                <div class="jumbotron" style="margin-left: 5%; margin-right: 5%; background: #ffffffc0">
                    <div class="row" style="margin-left: 1%; margin-right: 1%">

                        <!-- ------------------------ Categories of dishes ------------------------->

                        <div class="col-sm-2">
                            <div class="list-group" id="list-tab" role="tablist">

                                <% for (int i=0 ; i < types.length ; i++){
                                    String categoryLabel = category[i].toLowerCase();
                                %>
                                <a class="list-group-item list-group-item-action <%if (i==0) out.print("active");%>"
                                   id="<%=categoryLabel%>-tab" data-toggle="list" href="#<%=categoryLabel%>" role="tab" >
                                    <b><%=category[i]%></b>
                                </a>
                                <%}%>

                            </div>
                        </div>

                        <!-- ------------------------ Selected dishes ------------------------------>

                        <form class="col-md-6" action="/status" method="post" id="selected-dishes">

                            <div class="tab-content" id="nav-tabContent">

                                <% for (int i = 0; i < types.length; i++) {
                                    String categoryLabel = category[i].toLowerCase();%>

                                <div class="tab-pane fade show <%if (i==0) out.print("active");%>" id="<%=categoryLabel%>" role="tabpanel" aria-labelledby="<%=categoryLabel%>-tab">
                                    <div class="card">
                                        <div class="card-body">

                                            <%  for (MenuElement item : filteredList) {
                                                if (item.getType().equals(types[i])) {  %>
                                            <div class="custom-control custom-checkbox">
                                                <div class="row">

                                                    <!-- Chechbox with dish name-->
                                                    <div class="col-sm-10">
                                                        <input type="checkbox" class="custom-control-input" id="<%=item.getElementCode()%>" name="selected-id" value="<%=item.getElementCode()%>">
                                                        <label class="custom-control-label" for="<%=item.getElementCode()%>" data-price="<%=item.getPrice()%>">
                                                            <%=item.getName()%>
                                                        </label>

                                                    </div>

                                                    <!-- Button to open a modal pop-up with dish details -->

                                                    <div class="col-sm-2">

                                                        <button type="button" class="btn btn-info btnDetails" data-toggle="modal" data-target="#details<%=item.getElementCode()%>">Details</button>

                                                        <div class="modal fade" id="details<%=item.getElementCode()%>" role="dialog">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">

                                                                    <div class="modal-header">
                                                                        <h4 class="modal-title"><%=item.getName()%></h4>
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>

                                                                    <div class="modal-body">
                                                                        <p><%=itemDetails(item)%></p>
                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>

                                                </div>




                                            </div>
                                            <%  }
                                            }   %>
                                        </div>
                                    </div>
                                </div>

                                <%}%>

                            </div>
                        </form>

                        <!-- -------------------------------------Menu Checkout ----------------------------------------------->

                        <div class="col-md-4">
                            <div class="card">
                                <div class="card-header bg-dark text-white"><b>Menu checkout</b></div>
                                <div class="card-body">
                                    <table class="table table-sm">
                                        <thead>
                                        <tr>
                                            <th><span style="color: dodgerblue">Dish</span></th>
                                            <th><span style="color: dodgerblue">Price</span></th>
                                        </tr>
                                        </thead>
                                        <tbody id="checkout">
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <br/>

                            <!---------------------------------------- Menu Name and Quantity of Menus ------------------------------->

                            <div class="form-group row">
                                <label for="menuName" class="col-sm-4 col-form-label">Menu name</label>
                                <input type="text" autocomplete="off" class="form-control col-sm-7" id="menuName" name="menuName" form="selected-dishes" placeholder="(Optional)">
                            </div>

                            <div class="form-group row" style="margin-top: -3%">
                                <label for="people" class="col-sm-4 col-form-label">People n.</label>
                                <input type="number" autocomplete="off" class="form-control col-sm-4" id="people" name="people" form="selected-dishes"
                                       title=" How many menus of this type do you want to order ? " required min="1" max="<%=reservation.getnGuests()%>">
                            </div>

                            <!------------------------------------- BACK and NEXT BUTTONS ----------------------------------------------->

                            <input type="hidden" name="backToStatus" form="selected-dishes" value="new-menu">
                            <input type="submit" class="btn btn-success" value="Submit" form="selected-dishes" style="float: right">

                            <form action="/status" method="post">
                                <input type="hidden" name="backToStatus" value="back">
                                <input type="submit" class="btn btn-dark" value="&laquo;Back" style="float: right; margin-right: 2%; background-color: #e34d4d; border-color: #e34d4d">
                            </form>

                        </div>
                    </div>
                </div>


        </body>


</html>
