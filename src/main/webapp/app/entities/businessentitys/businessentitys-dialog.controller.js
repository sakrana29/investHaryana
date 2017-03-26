(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentitysDialogController', BusinessentitysDialogController);

    BusinessentitysDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Businessentitys'];

    function BusinessentitysDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Businessentitys) {
        var vm = this;

        vm.businessentitys = entity;
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
            if (vm.businessentitys.id !== null) {
                Businessentitys.update(vm.businessentitys, onSaveSuccess, onSaveError);
            } else {
                Businessentitys.save(vm.businessentitys, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:businessentitysUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
