(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandusezoneclassificationController', LandusezoneclassificationController);

    LandusezoneclassificationController.$inject = ['Landusezoneclassification'];

    function LandusezoneclassificationController(Landusezoneclassification) {

        var vm = this;

        vm.landusezoneclassifications = [];

        loadAll();

        function loadAll() {
            Landusezoneclassification.query(function(result) {
                vm.landusezoneclassifications = result;
                vm.searchQuery = null;
            });
        }
    }
})();
