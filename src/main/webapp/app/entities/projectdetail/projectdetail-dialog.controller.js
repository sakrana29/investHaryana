(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailDialogController', ProjectdetailDialogController);

    ProjectdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectdetail'];

    function ProjectdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectdetail) {
        var vm = this;

        vm.projectdetail = entity;
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
            if (vm.projectdetail.id !== null) {
                Projectdetail.update(vm.projectdetail, onSaveSuccess, onSaveError);
            } else {
                Projectdetail.save(vm.projectdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectdetailUpdate', result);
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
