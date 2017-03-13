(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Waste_water_naturetypeDialogController', Waste_water_naturetypeDialogController);

    Waste_water_naturetypeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Waste_water_naturetype'];

    function Waste_water_naturetypeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Waste_water_naturetype) {
        var vm = this;

        vm.waste_water_naturetype = entity;
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
            if (vm.waste_water_naturetype.id !== null) {
                Waste_water_naturetype.update(vm.waste_water_naturetype, onSaveSuccess, onSaveError);
            } else {
                Waste_water_naturetype.save(vm.waste_water_naturetype, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:waste_water_naturetypeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
