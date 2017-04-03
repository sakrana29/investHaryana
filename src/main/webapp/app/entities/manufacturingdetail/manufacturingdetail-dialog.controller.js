(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingdetailDialogController', ManufacturingdetailDialogController);

    ManufacturingdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Manufacturingdetail'];

    function ManufacturingdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Manufacturingdetail) {
        var vm = this;

        vm.manufacturingdetail = entity;
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
            if (vm.manufacturingdetail.id !== null) {
                Manufacturingdetail.update(vm.manufacturingdetail, onSaveSuccess, onSaveError);
            } else {
                Manufacturingdetail.save(vm.manufacturingdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:manufacturingdetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
