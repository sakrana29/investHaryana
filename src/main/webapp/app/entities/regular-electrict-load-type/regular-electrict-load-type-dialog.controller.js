(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Regular_electrict_load_typeDialogController', Regular_electrict_load_typeDialogController);

    Regular_electrict_load_typeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Regular_electrict_load_type'];

    function Regular_electrict_load_typeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Regular_electrict_load_type) {
        var vm = this;

        vm.regular_electrict_load_type = entity;
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
            if (vm.regular_electrict_load_type.id !== null) {
                Regular_electrict_load_type.update(vm.regular_electrict_load_type, onSaveSuccess, onSaveError);
            } else {
                Regular_electrict_load_type.save(vm.regular_electrict_load_type, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:regular_electrict_load_typeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
