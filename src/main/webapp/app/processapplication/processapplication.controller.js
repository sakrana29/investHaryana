(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('processapplicationController', processapplicationController);

    processapplicationController.$inject = ['$timeout', '$scope', '$stateParams','$state'];

    function processapplicationController ($timeout, $scope, $stateParams,$state) {
        var vm = this;

//        vm.block = entity;
        vm.clear = clear;
      //  vm.save = save;

//        $timeout(function (){
//            angular.element('.form-group:eq(1)>input').focus();
//        });

        function clear () {
            //$uibModalInstance.dismiss('cancel');
            $state.go('processapplication', null, { reload: 'processapplication' });
        }

//        function save () {
//            vm.isSaving = true;
//            if (vm.processapplication.id !== null) {
//                processapplication.update(vm.block, onSaveSuccess, onSaveError);
//            } else {
//                processapplication.save(vm.block, onSaveSuccess, onSaveError);
//            }
//        }
//
//        function onSaveSuccess (result) {
//            $scope.$emit('investhryApp:blockUpdate', result);
//            $uibModalInstance.close(result);
//            vm.isSaving = false;
//        }
//
//        function onSaveError () {
//            vm.isSaving = false;
//        }


    }
})();
