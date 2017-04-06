(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceLogDialogController', ProjectServiceLogDialogController);

    ProjectServiceLogDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProjectServiceLog'];

    function ProjectServiceLogDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ProjectServiceLog) {
        var vm = this;

        vm.projectServiceLog = entity;
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
            if (vm.projectServiceLog.id !== null) {
                ProjectServiceLog.update(vm.projectServiceLog, onSaveSuccess, onSaveError);
            } else {
                ProjectServiceLog.save(vm.projectServiceLog, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectServiceLogUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.commentDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
