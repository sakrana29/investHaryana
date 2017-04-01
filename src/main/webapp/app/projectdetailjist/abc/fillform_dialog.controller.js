(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('fillformController', fillformController);

    fillformController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'ServiceFormField'];

    function fillformController ($timeout, $scope, $stateParams, $uibModalInstance, ServiceFormField) {
        var vm = this;
        var srvid = $stateParams.serviceID; //getting fooVal
        var prjid = $stateParams.projectid; //getting barVal
        vm.getids=srvid;
        vm.getprojectid=prjid;
        vm.clear = clear;
        vm.save = save;
//        vm.serviceFormField = entity;
//        console.log(entity);
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        ServiceFormField.query(function(result) {
                        vm.serviceFormFields = result;
                        vm.searchQuery = null;
                    });


         function save () {
                     vm.isSaving = true;
                     vm.projectserviceformfielddata.projectid = prjid;
                     vm.projectserviceformfielddata.serviceid= srvid
                     vm.projectserviceformfielddata.projectserviceformfieldvalue = vm.serviceFormFields.id
                     if (vm.projectserviceformfielddata.id !== null) {
                         Projectserviceformfielddata.update(vm.projectserviceformfielddata, onSaveSuccess, onSaveError);
                     } else {
                         Projectserviceformfielddata.save(vm.projectserviceformfielddata, onSaveSuccess, onSaveError);
                     }
                 }

                 function onSaveSuccess (result) {
                     $scope.$emit('investhryApp:projectserviceformfielddataUpdate', result);
                     $uibModalInstance.close(result);
                     vm.isSaving = false;
                 }

                 function onSaveError () {
                     vm.isSaving = false;
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
