(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WastewaterdetailDialogController', WastewaterdetailDialogController);

    WastewaterdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Wastewaterdetail'];

    function WastewaterdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Wastewaterdetail) {
        var vm = this;

        vm.wastewaterdetail = entity;
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
            if (vm.wastewaterdetail.id !== null) {
                Wastewaterdetail.update(vm.wastewaterdetail, onSaveSuccess, onSaveError);
            } else {
                Wastewaterdetail.save(vm.wastewaterdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:wastewaterdetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
