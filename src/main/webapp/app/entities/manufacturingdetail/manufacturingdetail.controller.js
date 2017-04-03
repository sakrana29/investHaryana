(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingdetailController', ManufacturingdetailController);

    ManufacturingdetailController.$inject = ['Manufacturingdetail'];

    function ManufacturingdetailController(Manufacturingdetail) {

        var vm = this;

        vm.manufacturingdetails = [];

        loadAll();

        function loadAll() {
            Manufacturingdetail.query(function(result) {
                vm.manufacturingdetails = result;
                vm.searchQuery = null;
            });
        }
    }
})();
