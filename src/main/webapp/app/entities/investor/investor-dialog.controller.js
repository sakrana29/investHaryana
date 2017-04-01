(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('InvestorDialogController', InvestorDialogController);

    InvestorDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Investor'];

    function InvestorDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Investor) {
        var vm = this;

        vm.investor = entity;
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
            if (vm.investor.id !== null) {
                Investor.update(vm.investor, onSaveSuccess, onSaveError);
            } else {
                Investor.save(vm.investor, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:investorUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdate = false;
        vm.datePickerOpenStatus.updatedate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
