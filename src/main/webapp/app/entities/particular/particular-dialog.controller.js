(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ParticularDialogController', ParticularDialogController);

    ParticularDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Particular'];

    function ParticularDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Particular) {
        var vm = this;

        vm.particular = entity;
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
            if (vm.particular.id !== null) {
                Particular.update(vm.particular, onSaveSuccess, onSaveError);
            } else {
                Particular.save(vm.particular, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:particularUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
