(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentServiceDialogController', DepartmentServiceDialogController);

    DepartmentServiceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DepartmentService'];

    function DepartmentServiceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DepartmentService) {
        var vm = this;

        vm.departmentService = entity;
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
            if (vm.departmentService.id !== null) {
                DepartmentService.update(vm.departmentService, onSaveSuccess, onSaveError);
            } else {
                DepartmentService.save(vm.departmentService, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:departmentServiceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
