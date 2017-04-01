(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectprocessflowstepsDialogController', ProjectprocessflowstepsDialogController);

    ProjectprocessflowstepsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectprocessflowsteps'];

    function ProjectprocessflowstepsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectprocessflowsteps) {
        var vm = this;

        vm.projectprocessflowsteps = entity;
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
            if (vm.projectprocessflowsteps.id !== null) {
                Projectprocessflowsteps.update(vm.projectprocessflowsteps, onSaveSuccess, onSaveError);
            } else {
                Projectprocessflowsteps.save(vm.projectprocessflowsteps, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectprocessflowstepsUpdate', result);
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
