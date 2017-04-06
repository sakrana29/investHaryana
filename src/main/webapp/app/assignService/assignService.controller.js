(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('assignServiceController', assignServiceController);

    assignServiceController.$inject = ['$timeout', '$scope',  '$stateParams','$state', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail'];

    function assignServiceController ($timeout, $scope, $stateParams, $state, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail) {
        var vm = this;

        loadDepartmentServices();

        function loadDepartmentServices() {
            DepartmentService.query(function(result) {
                vm.departmentServices = result;
                vm.searchQuery = null;
            });
        }

       Principal.identity().then(function(account) {
               vm.account = account;
               });
    }
})();
