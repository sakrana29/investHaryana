(function() {
    'use strict';
    angular
        .module('investhryApp')
        .controller('payfeeController', payfeeController);

    payfeeController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'Projectservicedetail'];

    function payfeeController ($timeout, $scope, $stateParams, $uibModalInstance, Projectservicedetail) {
        var vm = this;
        var srvid = $stateParams.serviceID; //getting fooVal
                var prjid = $stateParams.projectid; //getting barVal
                vm.getids=srvid;
                vm.getprojectid=prjid;
//                alert(srvid);
      //  vm.block = entity;
        vm.clear = clear;
        //vm.save = save;

        Projectservicedetail.query(function(result) {
            vm.projectservicedetails = result;
            vm.searchQuery = null;
        });

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
