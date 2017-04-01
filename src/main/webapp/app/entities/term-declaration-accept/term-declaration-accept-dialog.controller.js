(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Term_declaration_acceptDialogController', Term_declaration_acceptDialogController);

    Term_declaration_acceptDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Term_declaration_accept'];

    function Term_declaration_acceptDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Term_declaration_accept) {
        var vm = this;

        vm.term_declaration_accept = entity;
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
            if (vm.term_declaration_accept.id !== null) {
                Term_declaration_accept.update(vm.term_declaration_accept, onSaveSuccess, onSaveError);
            } else {
                Term_declaration_accept.save(vm.term_declaration_accept, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:term_declaration_acceptUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.applicationdate = false;
        vm.datePickerOpenStatus.createdate = false;
        vm.datePickerOpenStatus.updatedate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
