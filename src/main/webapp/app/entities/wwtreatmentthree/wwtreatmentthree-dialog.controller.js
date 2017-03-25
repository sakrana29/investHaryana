(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentthreeDialogController', WwtreatmentthreeDialogController);

    WwtreatmentthreeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Wwtreatmentthree'];

    function WwtreatmentthreeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Wwtreatmentthree) {
        var vm = this;

        vm.wwtreatmentthree = entity;
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
            if (vm.wwtreatmentthree.id !== null) {
                Wwtreatmentthree.update(vm.wwtreatmentthree, onSaveSuccess, onSaveError);
            } else {
                Wwtreatmentthree.save(vm.wwtreatmentthree, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:wwtreatmentthreeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
