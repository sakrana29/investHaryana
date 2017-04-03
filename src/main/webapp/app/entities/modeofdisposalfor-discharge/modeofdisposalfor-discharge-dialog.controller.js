(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Modeofdisposalfor_dischargeDialogController', Modeofdisposalfor_dischargeDialogController);

    Modeofdisposalfor_dischargeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Modeofdisposalfor_discharge'];

    function Modeofdisposalfor_dischargeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Modeofdisposalfor_discharge) {
        var vm = this;

        vm.modeofdisposalfor_discharge = entity;
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
            if (vm.modeofdisposalfor_discharge.id !== null) {
                Modeofdisposalfor_discharge.update(vm.modeofdisposalfor_discharge, onSaveSuccess, onSaveError);
            } else {
                Modeofdisposalfor_discharge.save(vm.modeofdisposalfor_discharge, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:modeofdisposalfor_dischargeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
