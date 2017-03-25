(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentserviceController', DepartmentserviceController);

    DepartmentserviceController.$inject = ['Departmentservice'];

    function DepartmentserviceController(Departmentservice) {

        var vm = this;

        vm.departmentservices = [];

        loadAll();

        function loadAll() {
            Departmentservice.query(function(result) {
                vm.departmentservices = result;
                vm.searchQuery = null;
            });
        }
    }
})();
