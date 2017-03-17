(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('InvestorController', InvestorController);

    InvestorController.$inject = ['Investor'];

    function InvestorController(Investor) {

        var vm = this;

        vm.investors = [];

        loadAll();

        function loadAll() {
            Investor.query(function(result) {
                vm.investors = result;
                vm.searchQuery = null;
            });
        }
    }
})();
