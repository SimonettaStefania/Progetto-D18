<%@ page import="menu.DishType" %>
<%@ page import="menu.MenuElement" %>
<%@ page import="restaurant.Catalogue" %>
<%@ page import="restaurant.Restaurant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
          integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" crossorigin="anonymous"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" crossorigin="anonymous"
            integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
    <%  //  Da implementare nella versione effettiva
        // Catalogue catalogue = (Catalogue) request.getAttribute("catalogue");   %>

    <%!
        Catalogue catalogue = getCatalogue();

        private Catalogue getCatalogue() {
        try {
            Restaurant r = new Restaurant("Ristorante Italiano", 100, false);

            MenuElement starter_1 = new MenuElement("Bruschetta","S001", DishType.STARTER, 4.00, false, false, false);
            MenuElement starter_2 = new MenuElement("Aragosta","S002", DishType.STARTER, 6.00, false, false, false);
            MenuElement first_1 = new MenuElement("Spaghetti alla carbonara", "P001", DishType.FIRST_COURSE, 10.0, false, false, false);
            MenuElement first_2 = new MenuElement("Spaghetti allo scoglio", "P002", DishType.FIRST_COURSE,11.0, false, false, false);
            MenuElement main_1 = new MenuElement("Pepata di cozze", "M001", DishType.MAIN_COURSE,10.00, false, false, false);
            MenuElement main_2 = new MenuElement("Cozze", "M002", DishType.MAIN_COURSE,8.00, false, false, false);
            MenuElement dessert_1 = new MenuElement("Cheesecake ai lamponi", "D001", DishType.DESSERT, 4.00, false, false, false);
            MenuElement dessert_2 = new MenuElement("Tiramisù", "D002", DishType.DESSERT, 2.00, false, false, false);
            MenuElement drink_1 = new MenuElement("Acqua", "DR001", DishType.DRINK, 1.0, false, false, false);
            MenuElement drink_2 = new MenuElement("Ciaone biricone", "DR002", DishType.DRINK, 3.0, false, false, false);

            r.addToCatalogue(dessert_1);    r.addToCatalogue(dessert_2);
            r.addToCatalogue(drink_1);      r.addToCatalogue(drink_2);
            r.addToCatalogue(starter_1);    r.addToCatalogue(starter_2);
            r.addToCatalogue(first_1);      r.addToCatalogue(first_2);
            r.addToCatalogue(main_1);       r.addToCatalogue(main_2);

            return r.getDishesCatalogue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    %>

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
                content += '<tr><th>' + selectedList[i] + '</th><td>' + pricesList[i] + '</td></tr>';
            $('#checkout').html(content);
        });
    });
    </script>

</head>
<body background="../img/background.jpg">

<nav class="navbar navbar-expand-md navbar-dark bg-dark mb-4">
    <a class="navbar-brand" href="#">Progetto D-18 </a>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
            <li class="nav-item active"><a class="nav-link" href="#">Menu Creation</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="jumbotron" style="background: #ffffff">
        <h2>Filters</h2>
        <div class="custom-control custom-checkbox custom-control-inline">
            <input type="checkbox" id="vegetarian" name="vegetarian" class="custom-control-input">
            <label class="custom-control-label" for="vegetarian">Vegetarian</label>
        </div>
        <div class="custom-control custom-checkbox custom-control-inline">
            <input type="checkbox" id="vegan" name="vegan" class="custom-control-input">
            <label class="custom-control-label" for="vegan">Vegan</label>
        </div>
        <div class="custom-control custom-checkbox custom-control-inline">
            <input type="checkbox" id="celiac" name="celiac" class="custom-control-input">
            <label class="custom-control-label" for="celiac">Celiac</label>
        </div>
            <a style="float: right" href="#" class="btn btn-primary">Apply</a>
    </div>
</div>

<div class="jumbotron" style="margin-left: 5%; margin-right: 5%; background: #ffffffc0">
    <div class="row" style="margin-left: 1%; margin-right: 1%">
        <div class="col-sm-2">
            <div class="list-group" id="list-tab" role="tablist">
                <a class="list-group-item list-group-item-action active" id="starters-tab" data-toggle="list"
                   href="#starters" role="tab" aria-controls="starters"><b>Starters</b></a>
                <a class="list-group-item list-group-item-action" id="first-tab" data-toggle="list"
                   href="#first-courses" role="tab" aria-controls="first"><b>First courses</b></a>
                <a class="list-group-item list-group-item-action" id="main-tab" data-toggle="list"
                   href="#main-courses" role="tab" aria-controls="main"><b>Main courses</b></a>
                <a class="list-group-item list-group-item-action" id="desserts-tab" data-toggle="list"
                   href="#desserts" role="tab" aria-controls="desserts"><b>Desserts</b></a>
                <a class="list-group-item list-group-item-action" id="drinks-tab" data-toggle="list"
                   href="#drinks" role="tab" aria-controls="drinks"><b>Drinks</b></a>
            </div>
        </div>
        <form class="col-sm-7" action="/status" method="post" id="selected-dishes">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="starters" role="tabpanel" aria-labelledby="starters-tab">
                    <div class="card">
                        <div class="card-body">

                        <%  for (MenuElement item : catalogue.getDishes()) {
                                    if (item.getType().equals(DishType.STARTER)) {  %>
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="<%=item.getElementCode()%>" name="selected-id" value="<%=item.getElementCode()%>">
                                        <label class="custom-control-label" for="<%=item.getElementCode()%>" data-price="<%=item.getPrice()%>"> <%=item.getName()%> </label>
                                     </div>
                                <%  }
                                }   %>
                        </div>
                    </div>
				</div>

                <div class="tab-pane fade" id="first-courses" role="tabpanel" aria-labelledby="first-tab">
                    <div class="card">
                        <div class="card-body">

                            <%  for (MenuElement item : catalogue.getDishes()) {
                                    if (item.getType().equals(DishType.FIRST_COURSE)) {  %>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="<%=item.getElementCode()%>" name="selected-id" value="<%=item.getElementCode()%>">
                                <label class="custom-control-label" for="<%=item.getElementCode()%>" data-price="<%=item.getPrice()%>"> <%=item.getName()%> </label>
                            </div>
                            <%  }
                            }   %>

                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="main-courses" role="tabpanel" aria-labelledby="main-tab">
                    <div class="card">
                        <div class="card-body">

                            <%  for (MenuElement item : catalogue.getDishes()) {
                                if (item.getType().equals(DishType.MAIN_COURSE)) {  %>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="<%=item.getElementCode()%>" name="selected-id" value="<%=item.getElementCode()%>">
                                <label class="custom-control-label" for="<%=item.getElementCode()%>" data-price="<%=item.getPrice()%>"> <%=item.getName()%> </label>
                            </div>
                            <%  }
                            }   %>

                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="desserts" role="tabpanel" aria-labelledby="desserts-tab">
                    <div class="card">
                        <div class="card-body">

                            <%  for (MenuElement item : catalogue.getDishes()) {
                                if (item.getType().equals(DishType.DESSERT)) {  %>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="<%=item.getElementCode()%>" name="selected-id" value="<%=item.getElementCode()%>">
                                <label class="custom-control-label" for="<%=item.getElementCode()%>" data-price="<%=item.getPrice()%>"> <%=item.getName()%> </label>
                            </div>
                            <%  }
                            }   %>

                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="drinks" role="tabpanel" aria-labelledby="drinks-tab">
                    <div class="card">
                        <div class="card-body">

                            <%  for (MenuElement item : catalogue.getDrinks()) {    %>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="<%=item.getElementCode()%>" name="selected-id" value="<%=item.getElementCode()%>">
                                <label class="custom-control-label" for="<%=item.getElementCode()%>" data-price="<%=item.getPrice()%>"> <%=item.getName()%> </label>
                            </div>
                            <%  }  %>

                        </div>
                    </div>
                </div>
            </div>
        </form>
        <div class="col-sm-3">
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
            <input type="hidden" name="backToStatus" form="selected-dishes" value="true">
            <input type="submit" class="btn btn-success" value="Submit" form="selected-dishes" style="float: right">
            <form action="/status" method="post">
                <input type="hidden" name="backToStatus" value="true">
                <input type="submit" class="btn btn-dark" value="Back" style="float: right; margin-right: 1%">
            </form>
        </div>
    </div>
</div>

</body>
</html>
