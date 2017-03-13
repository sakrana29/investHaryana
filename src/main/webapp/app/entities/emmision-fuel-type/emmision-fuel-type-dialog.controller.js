(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_fuel_typeDialogController', Emmision_fuel_typeDialogController);

    Emmision_fuel_typeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Emmision_fuel_type'];

    function Emmision_fuel_typeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Emmision_fuel_type) {
        var vm = this;

        vm.emmision_fuel_type = entity;
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
            if (vm.emmision_fuel_type.id !== null) {
                Emmision_fuel_type.update(vm.emmision_fuel_type, onSaveSuccess, onSaveError);
            } else {
                Emmision_fuel_type.save(vm.emmision_fuel_type, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:emmision_fuel_typeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
