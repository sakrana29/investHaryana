(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceClearanceActionController', serviceClearanceActionController);

    serviceClearanceActionController.$inject = ['$timeout', '$scope',  '$stateParams','$state', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail'];

    function serviceClearanceActionController ($timeout, $scope, $stateParams, $state, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail) {
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
