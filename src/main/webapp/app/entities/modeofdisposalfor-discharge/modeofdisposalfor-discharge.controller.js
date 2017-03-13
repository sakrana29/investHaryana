(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Modeofdisposalfor_dischargeController', Modeofdisposalfor_dischargeController);

    Modeofdisposalfor_dischargeController.$inject = ['Modeofdisposalfor_discharge'];

    function Modeofdisposalfor_dischargeController(Modeofdisposalfor_discharge) {

        var vm = this;

        vm.modeofdisposalfor_discharges = [];

        loadAll();

        function loadAll() {
            Modeofdisposalfor_discharge.query(function(result) {
                vm.modeofdisposalfor_discharges = result;
                vm.searchQuery = null;
            });
        }
    }
})();
