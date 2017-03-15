(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementController', ElectricrequirementController);

    ElectricrequirementController.$inject = ['DataUtils', 'Electricrequirement'];

    function ElectricrequirementController(DataUtils, Electricrequirement) {

        var vm = this;

        vm.electricrequirements = [];
        vm.openFile = DataUtils.openFile;
        vm.byteSize = DataUtils.byteSize;

        loadAll();

        function loadAll() {
            Electricrequirement.query(function(result) {
                vm.electricrequirements = result;
                vm.searchQuery = null;
            });
        }
    }
})();
