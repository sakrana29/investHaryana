(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingunitsController', ManufacturingunitsController);

    ManufacturingunitsController.$inject = ['Manufacturingunits'];

    function ManufacturingunitsController(Manufacturingunits) {

        var vm = this;

        vm.manufacturingunits = [];

        loadAll();

        function loadAll() {
            Manufacturingunits.query(function(result) {
                vm.manufacturingunits = result;
                vm.searchQuery = null;
            });
        }
    }
})();
