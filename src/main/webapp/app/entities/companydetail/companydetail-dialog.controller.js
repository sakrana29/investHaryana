(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailDialogController', CompanydetailDialogController);

    CompanydetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Companydetail'];

    function CompanydetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Companydetail) {
        var vm = this;

        vm.companydetail = entity;
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
            if (vm.companydetail.id !== null) {
                Companydetail.update(vm.companydetail, onSaveSuccess, onSaveError);
            } else {
                Companydetail.save(vm.companydetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:companydetailUpdate', result);
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
