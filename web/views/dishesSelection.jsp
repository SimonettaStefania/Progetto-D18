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
        </p>
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
        <div class="col-sm-7">
            <div class="tab-content" id="nav-tabContent">
                <div class="tab-pane fade show active" id="starters" role="tabpanel" aria-labelledby="starters-tab">
                    <div class="card">
                        <div class="card-body">
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="starter_1">
                                <label class="custom-control-label" for="starter_1">Bruschetta al Jigglypuff</label>
                            </div>
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="starter_2">
                                <label class="custom-control-label" for="starter_2">Oddish all'ascolana</label>
                            </div>
                        </div>
                    </div>
				</div>

                <div class="tab-pane fade" id="first-courses" role="tabpanel" aria-labelledby="first-tab">
                    <div class="card">
                        <div class="card-body">
                            Lorem ipsum
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="main-courses" role="tabpanel" aria-labelledby="main-tab">
                    <div class="card">
                        <div class="card-body">
                            Lorem ipsum
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="desserts" role="tabpanel" aria-labelledby="desserts-tab">
                    <div class="card">
                        <div class="card-body">
                            Lorem ipsum
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="drinks" role="tabpanel" aria-labelledby="drinks-tab">
                    <div class="card">
                        <div class="card-body">
                            Lorem ipsum
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="card">
                <div class="card-header bg-dark text-white"><b>Menu checkout</b></div>
                <div class="card-body">
                    <p>I'm a dish</p>
                    <p>I'm another dish</p>
                    <p>I'm Groot</p>
                    <p>I'm <span style="color: blue">blue</span> <span style="font-size: xx-small">♫ dabba di dabba dai ♫</span></p>
                </div>
            </div>

            <br/>
            <a style="float: right" href="/status" class="btn btn-success">Submit</a>
        </div>
    </div>
</div>

</body>
</html>
