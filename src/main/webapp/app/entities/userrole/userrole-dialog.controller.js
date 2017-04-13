(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UserroleDialogController', UserroleDialogController);

    UserroleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Userrole'];

    function UserroleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Userrole) {
        var vm = this;

        vm.userrole = entity;
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
            if (vm.userrole.id !== null) {
                Userrole.update(vm.userrole, onSaveSuccess, onSaveError);
            } else {
                Userrole.save(vm.userrole, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:userroleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
