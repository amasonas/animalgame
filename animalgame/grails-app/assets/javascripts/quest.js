var animalGameApp = angular.module('animalGameApp', []);

animalGameApp.controller('animalGameController', function ($scope) {

    $scope.listDo = [];
    $scope.listDoNotDo = [];
    $scope.adjective = {};

    $scope.convertArrayObjectToArrayId = function (arrayObj) {
        var length = arrayObj.length;
        var arrayId = [];
        for (var i = 0; i < length; i++)
            arrayId.push(arrayObj[i].id);
        return arrayId;

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

})