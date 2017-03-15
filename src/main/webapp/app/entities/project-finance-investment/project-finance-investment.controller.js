(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_finance_investmentController', Project_finance_investmentController);

    Project_finance_investmentController.$inject = ['Project_finance_investment'];

    function Project_finance_investmentController(Project_finance_investment) {

        var vm = this;

        vm.project_finance_investments = [];

        loadAll();

        function loadAll() {
            Project_finance_investment.query(function(result) {
                vm.project_finance_investments = result;
                vm.searchQuery = null;
            });
        }
    }
})();
