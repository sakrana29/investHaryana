(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectservicedetailDialogController', ProjectservicedetailDialogController);

    ProjectservicedetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectservicedetail'];

    function ProjectservicedetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectservicedetail) {
        var vm = this;

        vm.projectservicedetail = entity;
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
            if (vm.projectservicedetail.id !== null) {
                Projectservicedetail.update(vm.projectservicedetail, onSaveSuccess, onSaveError);
            } else {
                Projectservicedetail.save(vm.projectservicedetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.requireMarkedOnDate = false;
        vm.datePickerOpenStatus.assigOnDate = false;
        vm.datePickerOpenStatus.formFilledOnDate = false;
        vm.datePickerOpenStatus.paymentMadeOnDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
