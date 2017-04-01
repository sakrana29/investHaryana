(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('paymentserviceController', paymentserviceController);

    paymentserviceController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DepartmentService' ,'Projectservicedetail', 'Auth', 'Principal'];

    function paymentserviceController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DepartmentService, Projectservicedetail, Auth, Principal) {
        var vm = this;
        var srvid = $stateParams.serviceid; //getting fooVal
            var prjid = $stateParams.projectid; //getting barVal

        vm.Projectservicedetail = entity;
        vm.clear = clear;
        vm.save = save;

        Principal.identity().then(function(account) {
                    vm.account = account;
                    });
        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }


        function save () {
            vm.isSaving = true;
            vm.Projectservicedetail.serviceid =srvid;
            vm.Projectservicedetail.projectid =prjid;
            vm.Projectservicedetail.servicefee = vm.Projectservicedetail.servicefee;
            vm.Projectservicedetail.remarks =vm.Projectservicedetail.remarks;
            vm.Projectservicedetail.userlogin = vm.account.login;
            vm.Projectservicedetail.assigndate = new Date();
            Projectservicedetail.save(vm.Projectservicedetail, onSaveSuccess, onSaveError);

        }

        function onSaveSuccess (result) {
//            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

    }
})();
