(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentityDialogController', BusinessentityDialogController);

    BusinessentityDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Businessentity'];

    function BusinessentityDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Businessentity) {
        var vm = this;

        vm.businessentity = entity;
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
            if (vm.businessentity.id !== null) {
                Businessentity.update(vm.businessentity, onSaveSuccess, onSaveError);
            } else {
                Businessentity.save(vm.businessentity, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:businessentityUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
