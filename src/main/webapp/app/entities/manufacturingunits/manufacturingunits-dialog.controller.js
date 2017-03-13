(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingunitsDialogController', ManufacturingunitsDialogController);

    ManufacturingunitsDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Manufacturingunits'];

    function ManufacturingunitsDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Manufacturingunits) {
        var vm = this;

        vm.manufacturingunits = entity;
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
            if (vm.manufacturingunits.id !== null) {
                Manufacturingunits.update(vm.manufacturingunits, onSaveSuccess, onSaveError);
            } else {
                Manufacturingunits.save(vm.manufacturingunits, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:manufacturingunitsUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
