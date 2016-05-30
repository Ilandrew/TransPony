
var app = angular.module('myApp', ['ngRoute']);

app.config(['$routeProvider', function($routeProvide){
    $routeProvide
        //-------------LOGIN
        .when('/login', {
            templateUrl: 'jsp/login.html',
            controller:  'loginCtrl'
        })
        //-------------LOGISTIAN
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
        .when('/edit_checkpoint',{
            templateUrl: 'jsp/logistian/edit_checkpoint.html',
            controller:  'editCheckPointCtrl'
        })
        .when('/edit_route',{
            templateUrl: 'jsp/logistian/edit_route.html',
            controller:  'editRouteCtrl'
        })
        //-------------ACCOUNTANT
        .when('/index_accountant',{
            templateUrl: 'jsp/accountant/index_accountant.html',
            controller:  'indexAccountantCtrl'
        })
        .when('/choose_trip',{
            templateUrl: 'jsp/accountant/choose_trip.html',
            controller:  'fuelUsageCtrl'
        })
        .when('/create_waybill',{
            templateUrl: 'jsp/accountant/create_waybill.html',
            controller:  'createWaybillCtrl'
        })
        .when('/edit_waybill',{
            templateUrl: 'jsp/accountant/edit_waybill.html',
            controller:  'editWaybillCtrl'
        })
        .when('/set_fuel_usage',{
            templateUrl: 'jsp/accountant/set_fuel_usage.html',
            controller:  'fuelUsageCtrl'
        })
        .when('/show_waybills',{
            templateUrl: 'jsp/accountant/show_waybills.html',
            controller:  'showWaybillsCtrl'
        })
        //-------------ADMIN
        .when('/index_admin',{
            templateUrl: 'jsp/admin/index_admin.html',
            controller:  'indexAdminCtrl'
        })
        .when('/add_employee',{
            templateUrl: 'jsp/admin/add_employee.html',
            controller:  'addEmployeeCtrl'
        })
        .when('/edit_employee',{
            templateUrl: 'jsp/admin/edit_employee.html',
            controller:  'editEmployeeCtrl'
        })
        .when('/show_employees',{
            templateUrl: 'jsp/admin/show_employees.html',
            controller:  'showEmployeesCtrl'
        })
        //-------------DRIVER
        .when('/index_driver',{
            templateUrl: 'jsp/driver/index_driver.html',
            controller:  'indexDriverCtrl'
        })
        .when('/change_trip_status',{
            templateUrl: 'jsp/driver/change_trip_status.html',
            controller:  'tripStatusCtrl'
        })
        .when('/get_waybill',{
            templateUrl: 'jsp/driver/get_waybill.html',
            controller:  'driverWaybillCtrl'
        })
        .when('/route_info',{
            templateUrl: 'jsp/driver/route_info.html',
            controller:  'routeInfoCtrl'
        })
        //-------------LEADER
        .when('/index_leader',{
            templateUrl: 'jsp/leader/index_leader.html',
            controller:  'indexLeaderCtrl'
        })
        .when('/create_trip',{
            templateUrl: 'jsp/leader/create_trip.html',
            controller:  'createTripCtrl'
        })
        .when('/edit_trip',{
            templateUrl: 'jsp/leader/edit_trip.html',
            controller:  'editTripCtrl'
        })
        .when('/fuel_usage_report',{
            templateUrl: 'jsp/leader/fuel_usage_report.html',
            controller:  'fuelReportCtrl'
        })
        .when('/profit_report',{
            templateUrl: 'jsp/leader/profit_report.html',
            controller:  'profitReportCtrl'
        })
        .when('/show_trips',{
            templateUrl: 'jsp/leader/show_trips.html',
            controller:  'showTripsCtrl'
        })
        //-------------ELSE
        .otherwise({
            redirectTo :'/'
        })
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

app.controller('indexLogistCtrl', ['$scope', '$location', function ($scope, $location) {
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
    $scope.home = function (){
        $location.path('/index_logistian')
    }
}]);

app.controller('checkPointCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.pointLists = [];
    $http.get('viewCheckpoints').then(function(response) {
        $scope.pointLists = response.data;
    });

       /*\\ $http.post('delete', data).then(function (response) {
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

app.controller('createCheckPointCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.point = {
        x: null,
        y: null,
        name: null,
        pointType: null
    };
    $scope.type = null;
    $scope.types = [];
     $http.get('createCheckPoint').then(function(response) {
        $scope.types = response.data;
     });

    $scope.saveCheckpoint = function(){
        $http.post('saveCheckpoint', $scope.point).then(function(response){
            if (response.status = 200) {
                console.log('success');
            }
        })
    };
    /*\\ $http.post('delete', data).then(function (response) {
     if (response.status == 200) {
     $scope.collections = response.data;
     //delete collection from collections
     }
     })*/


}]);