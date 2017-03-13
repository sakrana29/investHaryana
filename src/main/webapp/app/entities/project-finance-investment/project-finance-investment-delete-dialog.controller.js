(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_finance_investmentDeleteController',Project_finance_investmentDeleteController);

    Project_finance_investmentDeleteController.$inject = ['$uibModalInstance', 'entity', 'Project_finance_investment'];

    function Project_finance_investmentDeleteController($uibModalInstance, entity, Project_finance_investment) {
        var vm = this;

        vm.project_finance_investment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Project_finance_investment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
