(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectAttachemntDialogController', ProjectAttachemntDialogController);

    ProjectAttachemntDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'ProjectAttachemnt'];

    function ProjectAttachemntDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, ProjectAttachemnt) {
        var vm = this;

        vm.projectAttachemnt = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectAttachemnt.id !== null) {
                ProjectAttachemnt.update(vm.projectAttachemnt, onSaveSuccess, onSaveError);
            } else {
                ProjectAttachemnt.save(vm.projectAttachemnt, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectAttachemntUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
