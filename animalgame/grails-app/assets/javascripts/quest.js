var animalGameApp = angular.module('animalGameApp', []);

animalGameApp.controller('animalGameController', function ($scope) {
    // def global variables

    $scope.defGlobalVariables = function(){
        $scope.listDo = [];
        $scope.listDoNotDo = [];
        $scope.adjective = {};
        $scope.animal = {};
        $scope.userAnimal = {};
    }

    $scope.start = function(){
        $scope.defGlobalVariables();
        $(".quest").hide();
        $(".quest1").show();
    }

    $scope.convertArrayObjectToArrayId = function (arrayObj) {
        var length = arrayObj.length;
        var arrayId = "";
        for (var i = 0; i < length; i++){
            arrayId = arrayId + arrayObj[i].id ;
            if(i != (length-1))
                arrayId = arrayId+",";
        }
            
        return arrayId;

    }


    $scope.getAdjectiveFromUser = function () {
        if(!$scope.userAnimal.name)
            alert("Preencha o nome do animal");
        else{
            $(".quest").hide();
            $(".quest5").show();
        }
    }

    $scope.weAreWrong = function(){
        $(".quest").hide();
        $(".quest4").show();
    }


    $scope.weHit = function(){
        alert("Acertei!!!");
        $scope.start();
    }


    $scope.foundAnAnimal = function(response){
        $scope.animal = response[0];
        $scope.$apply();
        $(".quest3").show();

    }

    $scope.callUrl = function (url, HTTPmethod, parameter, callback) {
        $.ajax({
            type: HTTPmethod,
            url: url,
            data: parameter,
            success: callback
        });
    }

    $scope.getParameters = function () {
        return  {
            random: 1,
            listDo: $scope.convertArrayObjectToArrayId($scope.listDo),
            listDoNotDo: $scope.convertArrayObjectToArrayId($scope.listDoNotDo),
        };
    }

    $scope.tryHint = function () {

        var callback = function (response) {
            if (response.length > 1)
                $scope.play();
            else
               $scope.foundAnAnimal(response); 
        }
        $scope.callUrl("http://localhost:8080/animalgame/animal/", "POST", $scope.getParameters(), callback);

    }

    $scope.play = function (does) {
        $(".quest").hide();
        if (does == undefined) {
            var callback = function (response) {
                $scope.adjective = response;
                $scope.$apply();
                $(".quest2").show();

            };
            $scope.callUrl("http://localhost:8080/animalgame/adjective/", "POST", $scope.getParameters(), callback);
        } else {
            if (does)
                $scope.listDo.push($scope.adjective);
            else
                $scope.listDoNotDo.push($scope.adjective);

            $scope.adjective = {};
            $scope.tryHint();
        }
    }

    $scope.save = function(){
        var saveAdjective = function(response){
            var saveAnimal = function(response){
                alert("Obrigado, animal gravado :)");
                $scope.start();
            }
            $scope.callUrl("http://localhost:8080/animalgame/animal/save", "POST", {name : $scope.userAnimal.name , listDo:response.id , lists : $scope.getParameters()  }, saveAnimal);

        }
        $scope.callUrl("http://localhost:8080/animalgame/adjective/save", "POST", {description : $scope.userAnimal.adjective}, saveAdjective);

    }
    
     $scope.defGlobalVariables();

})