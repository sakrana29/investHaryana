(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ServiceFormFieldDialogController', ServiceFormFieldDialogController);

    ServiceFormFieldDialogController.$inject = ['$timeout', '$scope', '$state', 'entity', 'ServiceFormField'];

    function ServiceFormFieldDialogController ($timeout, $scope, $state, entity, ServiceFormField) {
        var vm = this;

        vm.serviceFormField = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
           $state.go('service-form-field', {}, { reload: 'service-form-field' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.serviceFormField.id !== null) {
                ServiceFormField.update(vm.serviceFormField, onSaveSuccess, onSaveError);
            } else {
                ServiceFormField.save(vm.serviceFormField, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:serviceFormFieldUpdate', result);
            $state.go('service-form-field', {}, { reload: 'service-form-field' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
