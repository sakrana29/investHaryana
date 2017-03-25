(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmenttwoController', WwtreatmenttwoController);

    WwtreatmenttwoController.$inject = ['Wwtreatmenttwo'];

    function WwtreatmenttwoController(Wwtreatmenttwo) {

        var vm = this;

        vm.wwtreatmenttwos = [];

        loadAll();

        function loadAll() {
            Wwtreatmenttwo.query(function(result) {
                vm.wwtreatmenttwos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
