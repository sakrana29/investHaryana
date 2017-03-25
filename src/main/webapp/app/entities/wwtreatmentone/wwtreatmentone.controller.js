(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentoneController', WwtreatmentoneController);

    WwtreatmentoneController.$inject = ['Wwtreatmentone'];

    function WwtreatmentoneController(Wwtreatmentone) {

        var vm = this;

        vm.wwtreatmentones = [];

        loadAll();

        function loadAll() {
            Wwtreatmentone.query(function(result) {
                vm.wwtreatmentones = result;
                vm.searchQuery = null;
            });
        }
    }
})();
