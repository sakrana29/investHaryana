(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentServiceDialogController', DepartmentServiceDialogController);

    DepartmentServiceDialogController.$inject = ['$timeout', '$scope', '$state','entity', 'DepartmentService'];

    function DepartmentServiceDialogController ($timeout, $scope, $state, entity, DepartmentService) {
        var vm = this;

        vm.departmentService = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $state.go('department', {}, { reload: 'department' });
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
            $scope.$emit('investharyanaApp:departmentServiceUpdate', result);
            $state.go('department-service', {}, { reload: 'department-service' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
