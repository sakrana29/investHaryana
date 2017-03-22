(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentServiceDialogController', DepartmentServiceDialogController);

    DepartmentServiceDialogController.$inject = ['$timeout', '$scope', '$state','entity', 'DepartmentService', 'Department'];

    function DepartmentServiceDialogController ($timeout, $scope, $state, entity, DepartmentService, Department) {
        var vm = this;

        vm.departmentService = entity;
        vm.clear = clear;
        vm.save = save;

        Department.query(function(result) {
                        vm.departments = result;
//                        vm.searchQuery = null;
                    });

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $state.go('department-service', {}, { reload: 'department-service' });
        }

        function save () {
            vm.departmentService.departmentID=vm.deptservice.department.id;
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
