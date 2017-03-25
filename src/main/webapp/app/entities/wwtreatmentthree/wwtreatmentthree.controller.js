(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentthreeController', WwtreatmentthreeController);

    WwtreatmentthreeController.$inject = ['Wwtreatmentthree'];

    function WwtreatmentthreeController(Wwtreatmentthree) {

        var vm = this;

        vm.wwtreatmentthrees = [];

        loadAll();

        function loadAll() {
            Wwtreatmentthree.query(function(result) {
                vm.wwtreatmentthrees = result;
                vm.searchQuery = null;
            });
        }
    }
})();
