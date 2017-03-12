(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentDialogController', DepartmentDialogController);

    DepartmentDialogController.$inject = ['$timeout', '$scope', '$state','entity', 'Department'];

    function DepartmentDialogController ($timeout, $scope, $state, entity, Department) {
        var vm = this;

        vm.department = entity;
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
            if (vm.department.id !== null) {
                Department.update(vm.department, onSaveSuccess, onSaveError);
            } else {
                Department.save(vm.department, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investharyanaApp:departmentUpdate', result);
            $state.go('department', {}, { reload: 'department' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
