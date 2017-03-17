(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impactdetailDialogController', Environment_impactdetailDialogController);

    Environment_impactdetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Environment_impactdetail'];

    function Environment_impactdetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Environment_impactdetail) {
        var vm = this;

        vm.environment_impactdetail = entity;
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
            if (vm.environment_impactdetail.id !== null) {
                Environment_impactdetail.update(vm.environment_impactdetail, onSaveSuccess, onSaveError);
            } else {
                Environment_impactdetail.save(vm.environment_impactdetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:environment_impactdetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
