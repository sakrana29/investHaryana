(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandusezoneclassificationDialogController', LandusezoneclassificationDialogController);

    LandusezoneclassificationDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Landusezoneclassification'];

    function LandusezoneclassificationDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Landusezoneclassification) {
        var vm = this;

        vm.landusezoneclassification = entity;
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
            if (vm.landusezoneclassification.id !== null) {
                Landusezoneclassification.update(vm.landusezoneclassification, onSaveSuccess, onSaveError);
            } else {
                Landusezoneclassification.save(vm.landusezoneclassification, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:landusezoneclassificationUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
