(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentoneDialogController', WwtreatmentoneDialogController);

    WwtreatmentoneDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Wwtreatmentone'];

    function WwtreatmentoneDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Wwtreatmentone) {
        var vm = this;

        vm.wwtreatmentone = entity;
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
            if (vm.wwtreatmentone.id !== null) {
                Wwtreatmentone.update(vm.wwtreatmentone, onSaveSuccess, onSaveError);
            } else {
                Wwtreatmentone.save(vm.wwtreatmentone, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:wwtreatmentoneUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
