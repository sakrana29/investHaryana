(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('payfeeController', payfeeController);

    payfeeController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance'];

    function payfeeController ($timeout, $scope, $stateParams, $uibModalInstance) {
        var vm = this;

      //  vm.block = entity;
        vm.clear = clear;
        //vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

//        function save () {
//            vm.isSaving = true;
//            if (vm.block.id !== null) {
//                Block.update(vm.block, onSaveSuccess, onSaveError);
//            } else {
//                Block.save(vm.block, onSaveSuccess, onSaveError);
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
