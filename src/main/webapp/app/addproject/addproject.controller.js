(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('addprojectController', addprojectController);

    addprojectController.$inject = ['$scope', 'Principal', 'LoginService', '$state','$http'];

    function addprojectController ($scope, Principal, LoginService, $state, $http) {
        var vm = this;
        //vm.statechange=statechange;

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
       $http.get("/api/businessentities").then(function(response) {
           vm.businesses = response.data;
       });
       $http.get("/api/sectors").then(function(response) {
           vm.sector = response.data;
       });
        $http.get("/api/industrysizes").then(function(response) {
           vm.sizeindustry = response.data;
       });
         $http.get("/api/projectypes").then(function(response) {
           vm.projecttype = response.data;
       });
        $http.get("/api/projectcategories").then(function(response) {
           vm.categoryproject = response.data;
       });
        $http.get("/api/foreignfundingresources").then(function(response) {
           vm.foreign = response.data;
       });
       $http.get("/api/approvalforms").then(function(response) {
           vm.applicationform = response.data;
       });
        $http.get("/api/blocks").then(function(response) {
           vm.block = response.data;
       });
        $http.get("/api/city-town-villages").then(function(response) {
           vm.city = response.data;
       });
        $http.get("/api/connectingroads").then(function(response) {
           vm.connecting = response.data;
       });
        $http.get("/api/landusezoneclassifications").then(function(response) {
           vm.landzone = response.data;
       });
       $http.get("/api/watersupplysources").then(function(response) {
           vm.watersource = response.data;
       });
       $http.get("/api/waste-water-disposal-modes").then(function(response) {
           vm.wastewatertreatment = response.data;
       });
       $http.get("/api/emmision-pollution-controlls").then(function(response) {
           vm.emissionpolution = response.data;
       });$http.get("/api/emmision-fuel-types").then(function(response) {
           vm.emissionfuel = response.data;
       });

//       function statechange(cntid){
//       alert(vm.cntid.id);
//             $http.get("/api/state/country/"+cntid).then(function(response) {
//                  vm.states = response.data;
//             });
//       }

    }
})();
