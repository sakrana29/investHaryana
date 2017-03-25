(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmenttwoDialogController', WwtreatmenttwoDialogController);

    WwtreatmenttwoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Wwtreatmenttwo'];

    function WwtreatmenttwoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Wwtreatmenttwo) {
        var vm = this;

        vm.wwtreatmenttwo = entity;
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
            if (vm.wwtreatmenttwo.id !== null) {
                Wwtreatmenttwo.update(vm.wwtreatmenttwo, onSaveSuccess, onSaveError);
            } else {
                Wwtreatmenttwo.save(vm.wwtreatmenttwo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:wwtreatmenttwoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
