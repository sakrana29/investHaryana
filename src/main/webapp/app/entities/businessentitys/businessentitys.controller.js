(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentitysController', BusinessentitysController);

    BusinessentitysController.$inject = ['Businessentitys'];

    function BusinessentitysController(Businessentitys) {

        var vm = this;

        vm.businessentitys = [];

        loadAll();

        function loadAll() {
            Businessentitys.query(function(result) {
                vm.businessentitys = result;
                vm.searchQuery = null;
            });
        }
    }
})();
