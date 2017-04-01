(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectproductDialogController', ProjectproductDialogController);

    ProjectproductDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectproduct'];

    function ProjectproductDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectproduct) {
        var vm = this;

        vm.projectproduct = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectproduct.id !== null) {
                Projectproduct.update(vm.projectproduct, onSaveSuccess, onSaveError);
            } else {
                Projectproduct.save(vm.projectproduct, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectproductUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createdate = false;
        vm.datePickerOpenStatus.updatedate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
