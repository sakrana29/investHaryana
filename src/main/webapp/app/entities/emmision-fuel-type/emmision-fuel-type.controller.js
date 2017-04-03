(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_fuel_typeController', Emmision_fuel_typeController);

    Emmision_fuel_typeController.$inject = ['Emmision_fuel_type'];

    function Emmision_fuel_typeController(Emmision_fuel_type) {

        var vm = this;

        vm.emmision_fuel_types = [];

        loadAll();

        function loadAll() {
            Emmision_fuel_type.query(function(result) {
                vm.emmision_fuel_types = result;
                vm.searchQuery = null;
            });
        }
    }
})();
