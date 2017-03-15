(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WatersupplysourceController', WatersupplysourceController);

    WatersupplysourceController.$inject = ['Watersupplysource'];

    function WatersupplysourceController(Watersupplysource) {

        var vm = this;

        vm.watersupplysources = [];

        loadAll();

        function loadAll() {
            Watersupplysource.query(function(result) {
                vm.watersupplysources = result;
                vm.searchQuery = null;
            });
        }
    }
})();
