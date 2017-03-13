(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_naturetypeController', Waste_water_naturetypeController);

    Waste_water_naturetypeController.$inject = ['Waste_water_naturetype'];

    function Waste_water_naturetypeController(Waste_water_naturetype) {

        var vm = this;

        vm.waste_water_naturetypes = [];

        loadAll();

        function loadAll() {
            Waste_water_naturetype.query(function(result) {
                vm.waste_water_naturetypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
