(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentServiceController', DepartmentServiceController);

    DepartmentServiceController.$inject = ['DepartmentService'];

    function DepartmentServiceController(DepartmentService) {

        var vm = this;

        vm.departmentServices = [];

        loadAll();

        function loadAll() {
            DepartmentService.query(function(result) {
                vm.departmentServices = result;
                vm.searchQuery = null;
            });
        }
    }
})();
