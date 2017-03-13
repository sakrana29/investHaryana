(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ParticularController', ParticularController);

    ParticularController.$inject = ['Particular'];

    function ParticularController(Particular) {

        var vm = this;

        vm.particulars = [];

        loadAll();

        function loadAll() {
            Particular.query(function(result) {
                vm.particulars = result;
                vm.searchQuery = null;
            });
        }
    }
})();
