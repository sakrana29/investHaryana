(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('addprojectController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$http'];

    function addprojectController ($scope, Principal, LoginService, $state, $http) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        function register () {
            $state.go('register');
        }
        vm.IsVisible = false;
        vm.ShowPassport = function (value) {
            //If DIV is visible it will be hidden and vice versa.
            vm.IsVisible = value == "Y";
        }
        $http.get("/api/countries").then(function(response) {
           vm.countries = response.data;
       });
       $http.get("/api/states").then(function(response) {
           vm.states = response.data;
       });
       $http.get("/api/districts").then(function(response) {
           vm.districts = response.data;
       });
    }
})();
