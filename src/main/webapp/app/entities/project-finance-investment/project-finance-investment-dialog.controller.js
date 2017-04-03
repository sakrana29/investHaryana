(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_finance_investmentDialogController', Project_finance_investmentDialogController);

    Project_finance_investmentDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Project_finance_investment'];

    function Project_finance_investmentDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Project_finance_investment) {
        var vm = this;

        vm.project_finance_investment = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.project_finance_investment.id !== null) {
                Project_finance_investment.update(vm.project_finance_investment, onSaveSuccess, onSaveError);
            } else {
                Project_finance_investment.save(vm.project_finance_investment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:project_finance_investmentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.project_construction_start_date = false;
        vm.datePickerOpenStatus.commercial_activity_start_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
