(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_phaseDialogController', Project_phaseDialogController);

    Project_phaseDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Project_phase'];

    function Project_phaseDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Project_phase) {
        var vm = this;

        vm.project_phase = entity;
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
            if (vm.project_phase.id !== null) {
                Project_phase.update(vm.project_phase, onSaveSuccess, onSaveError);
            } else {
                Project_phase.save(vm.project_phase, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:project_phaseUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.implementationdate = false;
        vm.datePickerOpenStatus.createdate = false;
        vm.datePickerOpenStatus.updatedate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
