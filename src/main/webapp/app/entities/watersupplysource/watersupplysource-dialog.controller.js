(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WatersupplysourceDialogController', WatersupplysourceDialogController);

    WatersupplysourceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Watersupplysource'];

    function WatersupplysourceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Watersupplysource) {
        var vm = this;

        vm.watersupplysource = entity;
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
            if (vm.watersupplysource.id !== null) {
                Watersupplysource.update(vm.watersupplysource, onSaveSuccess, onSaveError);
            } else {
                Watersupplysource.save(vm.watersupplysource, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:watersupplysourceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
