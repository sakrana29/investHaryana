(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementController', ElectricrequirementController);

    ElectricrequirementController.$inject = ['Electricrequirement'];

    function ElectricrequirementController(Electricrequirement) {

        var vm = this;

        vm.electricrequirements = [];

        loadAll();

        function loadAll() {
            Electricrequirement.query(function(result) {
                vm.electricrequirements = result;
                vm.searchQuery = null;
            });
        }
    }
})();
