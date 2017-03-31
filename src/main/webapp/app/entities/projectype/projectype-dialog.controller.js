(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectypeDialogController', ProjectypeDialogController);

    ProjectypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectype'];

    function ProjectypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectype) {
        var vm = this;

        vm.projectype = entity;
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
            if (vm.projectype.id !== null) {
                Projectype.update(vm.projectype, onSaveSuccess, onSaveError);
            } else {
                Projectype.save(vm.projectype, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
