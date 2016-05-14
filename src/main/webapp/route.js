
var app = angular.module('myApp', ['ngRoute']);

app.config(['$routeProvider', function($routeProvide){
    $routeProvide
        .when('/', {
            templateUrl: 'jsp/login.html',
            controller:  'loginCtrl'
        })
        .when('/index_logistian', {
            templateUrl: 'jsp/logistian/index_logistian.html',
            controller:  'indexLogistCtrl'
        })
        .when('/show_checkpoints', {
            templateUrl: 'jsp/logistian/show_checkpoints.html',
            controller:  'checkPointCtrl'
        })
        .when('/show_routes', {
            templateUrl: 'jsp/logistian/show_routes.html',
            controller:  'routeCtrl'
        })
        .when('/create_route',{
            templateUrl: 'jsp/logistian/create_route.html',
            controller:  'routeCtrl'
        })
        .when('/create_checkpoint',{
            templateUrl: 'jsp/logistian/create_checkpoint.html',
            controller:  'createCheckPointCtrl'
        })
        .otherwise({
            redirectTo :'/'
        })
}]);

app.controller('indexLogistCtrl', ['$scope', '$location',
    function ($scope, $location) {

        $scope.viewCheckpoints = function () {
            $location.path('/show_checkpoints');
        };
        $scope.showRoutes = function () {
            $location.path('/show_routes');
        }
        $scope.createRoute = function (){
            $location.path('/create_route');
        }
        $scope.createCheckPoint = function() {
            $location.path('/create_checkpoint')
        }
    }]);

app.controller('loginCtrl', ['$scope', '$http', '$location', function($scope, $http, $location){
    $scope.userCredentials = {
        login: null,
        password: null
    };
    $scope.login = function (){
        $http.post('login', $scope.userCredentials).then(function(response) {
            if (response.status == 200) {
                console.log('success');
                $location.path('/index_logistian');
            }

        });
    }
}]);
app.controller('checkPointCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.pointLists = [];
    $http.get('viewCheckpoints').then(function(response) {
        $scope.pointLists = response.data;
    });

    $scope.collections = [];
       /* $http.post('delete', data).then(function (response) {
            if (response.status == 200) {
                $scope.collections = response.data;
                //delete collection from collections
            }
        })*/


}]);

app.controller('routeCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.routeList = [];
    $http.get('viewRoute').then(function(response) {
        $scope.routeList = response.data;
    });

    //$scope.collections = [];
    /* $http.post('delete', data).then(function (response) {
     if (response.status == 200) {
     $scope.collections = response.data;
     //delete collection from collections
     }
     })*/


}]);

app.controller('createCheckPointCtrl', ['$scope', '$http', function($scope, $http){
    $scope.types = [];
    $http.get('createCheckPoint').then(function(response){
        $scope.types = response.data;
    })
}])