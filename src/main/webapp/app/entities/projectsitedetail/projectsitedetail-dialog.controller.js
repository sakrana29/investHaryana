(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailDialogController', ProjectsitedetailDialogController);

    ProjectsitedetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectsitedetail'];

    function ProjectsitedetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectsitedetail) {
        var vm = this;

        vm.projectsitedetail = entity;
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
            if (vm.projectsitedetail.id !== null) {
                Projectsitedetail.update(vm.projectsitedetail, onSaveSuccess, onSaveError);
            } else {
                Projectsitedetail.save(vm.projectsitedetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectsitedetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
