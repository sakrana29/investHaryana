(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentserviceDialogController', DepartmentserviceDialogController);

    DepartmentserviceDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Departmentservice'];

    function DepartmentserviceDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Departmentservice) {
        var vm = this;

        vm.departmentservice = entity;
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
            if (vm.departmentservice.id !== null) {
                Departmentservice.update(vm.departmentservice, onSaveSuccess, onSaveError);
            } else {
                Departmentservice.save(vm.departmentservice, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:departmentserviceUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
