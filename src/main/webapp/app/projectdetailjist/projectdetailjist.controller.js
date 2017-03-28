(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('projectdetailjistController', projectdetailjistController);

    projectdetailjistController.$inject = ['$scope', 'Principal', 'LoginService', '$state','entity','Projectservicedetail','DepartmentService'];

    function projectdetailjistController ($scope, Principal, LoginService, $state, entity,Projectservicedetail,DepartmentService) {
        var vm = this;
        vm.selectedprojectdetail = entity;
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

        Projectservicedetail.query(function(result) {
            vm.projectservicedetails = result;
            vm.searchQuery = null;
        });

        DepartmentService.query(function(data) {
             vm.departmentServices = data;
             vm.searchQuery = null;
        });
    }
})();
