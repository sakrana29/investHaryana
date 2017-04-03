(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailcombinecodesDialogController', ProjectdetailcombinecodesDialogController);

    ProjectdetailcombinecodesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectdetailcombinecodes'];

    function ProjectdetailcombinecodesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectdetailcombinecodes) {
        var vm = this;

        vm.projectdetailcombinecodes = entity;
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
            if (vm.projectdetailcombinecodes.id !== null) {
                Projectdetailcombinecodes.update(vm.projectdetailcombinecodes, onSaveSuccess, onSaveError);
            } else {
                Projectdetailcombinecodes.save(vm.projectdetailcombinecodes, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectdetailcombinecodesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
