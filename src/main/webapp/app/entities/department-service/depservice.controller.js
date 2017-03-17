(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('depserviceController', depserviceController);

    depserviceController.$inject = ['$scope','$http'];

    function depserviceController($scope, $http) {
        $http.get("/api/departments").then(function(response) {
           return $scope.students = response.data;
       });
    }
//    function distcountryController($scope, $http) {
//       // $http.get("country.js").then(function(response) {
//           return $scope.students = [
//            {
//            "id":"20c7501a-b84a-4f32-891c-289a0d76eeb8",
//            "cname":"INDIA"
//            },
//            {
//                "id":"7f2bbaa1-c6b0-4310-9d27-b013e20f5c70",
//                "cname":"ENGLAND"
//            }
//        ];
       //});
    //}



})();

