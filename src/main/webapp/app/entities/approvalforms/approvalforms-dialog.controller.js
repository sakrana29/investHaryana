(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ApprovalformsDialogController', ApprovalformsDialogController);

    ApprovalformsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Approvalforms'];

    function ApprovalformsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Approvalforms) {
        var vm = this;

        vm.approvalforms = entity;
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
            if (vm.approvalforms.id !== null) {
                Approvalforms.update(vm.approvalforms, onSaveSuccess, onSaveError);
            } else {
                Approvalforms.save(vm.approvalforms, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:approvalformsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
