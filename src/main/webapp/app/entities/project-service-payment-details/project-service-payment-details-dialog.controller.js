(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServicePaymentDetailsDialogController', ProjectServicePaymentDetailsDialogController);

    ProjectServicePaymentDetailsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProjectServicePaymentDetails'];

    function ProjectServicePaymentDetailsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ProjectServicePaymentDetails) {
        var vm = this;

        vm.projectServicePaymentDetails = entity;
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
            if (vm.projectServicePaymentDetails.id !== null) {
                ProjectServicePaymentDetails.update(vm.projectServicePaymentDetails, onSaveSuccess, onSaveError);
            } else {
                ProjectServicePaymentDetails.save(vm.projectServicePaymentDetails, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectServicePaymentDetailsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.paymentDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
