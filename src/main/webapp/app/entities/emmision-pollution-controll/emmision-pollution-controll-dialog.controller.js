(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_pollution_controllDialogController', Emmision_pollution_controllDialogController);

    Emmision_pollution_controllDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Emmision_pollution_controll'];

    function Emmision_pollution_controllDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Emmision_pollution_controll) {
        var vm = this;

        vm.emmision_pollution_controll = entity;
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
            if (vm.emmision_pollution_controll.id !== null) {
                Emmision_pollution_controll.update(vm.emmision_pollution_controll, onSaveSuccess, onSaveError);
            } else {
                Emmision_pollution_controll.save(vm.emmision_pollution_controll, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:emmision_pollution_controllUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
