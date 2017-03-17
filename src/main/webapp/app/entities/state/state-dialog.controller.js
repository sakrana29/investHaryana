(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('StateDialogController', StateDialogController);

    StateDialogController.$inject = ['$timeout', '$scope', '$state', 'entity', 'State'];

    function StateDialogController ($timeout, $scope, $state, entity, State) {
        var vm = this;

        vm.state = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
             $state.go('state', null, { reload: 'state' });
        }

        function save () {
            vm.isSaving = true;
            if (vm.state.id !== null) {
                State.update(vm.state, onSaveSuccess, onSaveError);
            } else {
                State.save(vm.state, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:stateUpdate', result);
            $state.go('state', null, { reload: 'state' });
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
