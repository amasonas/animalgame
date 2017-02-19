<!doctype html>
<html>
    <head>

        <title>Lógica Animal</title>

    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="quest.css"/>
</head>
<body>

    <div class="container-fluid" ng-app="animalGameApp" ng-controller="animalGameController">
        <div class="row">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Menu<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Pensar em outro animal</a>
                                </li>
                                <li>
                                    <a href="#">Limpar animais</a>
                                </li> 
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="row questRow">
            <div class="col-md-12 quest quest1" >


                <h3 class="text-center text-primary">
                    Pense Em um animal
                </h3> 
                <button ng-click="play()" type="button" class="btn btn-block btn-success">
                    Vamos começar !
                </button> 
            </div>
            <div class="col-md-12 quest quest2" style="display:none">
                <h3 class="text-center text-primary">
                    Este animal {{adjective.description}}?
                </h3>  
                <button ng-click="play(true)" type="button" class="btn btn-block btn-success">
                    Sim
                </button> 
                <button ng-click="play(false)" type="button" class="btn btn-block btn-warning">
                    Não
                </button>
            </div>
            <div class="col-md-12 quest quest3" style="display:none">
                <h3 class="text-center text-primary" >
                    Este animal é um <span >Macaco</span>?
                </h3> 
                <button type="button" class="btn btn-block btn-success">
                    Sim
                </button> 
                <button type="button" class="btn btn-block btn-warning">
                    Não
                </button>
            </div>
            <div class="col-md-12 quest quest4" style="display:none">
                <h3 class="text-center text-primary" >
                    Que animal você pensou?
                </h3> 
                <div class="form-group">
                    <input type="text" class="form-control" id="usr">
                </div>
                <button type="button" class="btn btn-block btn-success">
                    Ok
                </button> 
            </div>
            <div class="col-md-12 quest quest5" style="display:none">
                <h3 class="text-center text-primary" >
                    O que um <span>Animal</span> faz, que um <span>animal</span> não faz?
                </h3> 
                <div class="form-group">
                    <input type="text" class="form-control" id="usr">
                </div>
                <button type="button" class="btn btn-block btn-success">
                    Ok
                </button> 
            </div>
        </div>
    </div>
<asset:javascript src="jquery.min.js"/>
<asset:javascript src="bootstrap.min.js"/>
<asset:javascript src="angular.js"/>
<asset:javascript src="quest.js"/>
</body>
</html>
