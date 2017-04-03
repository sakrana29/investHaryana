(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FiletestingController', FiletestingController);

    FiletestingController.$inject = ['Filetesting'];

    function FiletestingController(Filetesting) {

        var vm = this;

        vm.filetestings = [];

        loadAll();

        function loadAll() {
            Filetesting.query(function(result) {
                vm.filetestings = result;
                vm.searchQuery = null;
            });
        }
    }
})();
