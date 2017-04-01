(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceReportInfoDialogController', ProjectServiceReportInfoDialogController);

    ProjectServiceReportInfoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProjectServiceReportInfo'];

    function ProjectServiceReportInfoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ProjectServiceReportInfo) {
        var vm = this;

        vm.projectServiceReportInfo = entity;
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
            if (vm.projectServiceReportInfo.id !== null) {
                ProjectServiceReportInfo.update(vm.projectServiceReportInfo, onSaveSuccess, onSaveError);
            } else {
                ProjectServiceReportInfo.save(vm.projectServiceReportInfo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectServiceReportInfoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.assignDate = false;
        vm.datePickerOpenStatus.requireDate = false;
        vm.datePickerOpenStatus.finalActionDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
