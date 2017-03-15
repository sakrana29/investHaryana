(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentityController', BusinessentityController);

    BusinessentityController.$inject = ['Businessentity'];

    function BusinessentityController(Businessentity) {

        var vm = this;

        vm.businessentities = [];

        loadAll();

        function loadAll() {
            Businessentity.query(function(result) {
                vm.businessentities = result;
                vm.searchQuery = null;
            });
        }
    }
})();
