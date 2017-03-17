(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementDialogController', ElectricrequirementDialogController);

    ElectricrequirementDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Electricrequirement'];

    function ElectricrequirementDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Electricrequirement) {
        var vm = this;

        vm.electricrequirement = entity;
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
            if (vm.electricrequirement.id !== null) {
                Electricrequirement.update(vm.electricrequirement, onSaveSuccess, onSaveError);
            } else {
                Electricrequirement.save(vm.electricrequirement, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:electricrequirementUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.temp_load_demand_date = false;
        vm.datePickerOpenStatus.regular_load_demand_date = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
