(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('projectServiceDetailsFeeController', projectServiceDetailsFeeController);

    projectServiceDetailsFeeController.$inject = ['$uibModalInstance', '$timeout', '$scope', '$stateParams', 'entity', 'projectservicedetailbyidentity', 'DepartmentService' , 'Auth', 'Principal', 'Projectservicedetail'];

    function projectServiceDetailsFeeController ($uibModalInstance, $timeout, $scope, $stateParams, entity, projectservicedetailbyidentity, DepartmentService, Auth, Principal, Projectservicedetail) {
        var vm = this;

        vm.serviceid = $stateParams.serviceid; //getting fooVal
        vm.projectid = $stateParams.projectid; //getting barVal

        vm.clear = clear;
        vm.save = save;
        vm.projectservicedetailData = projectservicedetailbyidentity;
        console.log(vm.projectservicedetailData);
        vm.projectservicedetails = entity;

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
                alert(vm.projectservicedetails.id);
                vm.projectservicedetails.id = vm.projectservicedetailData.id;
                vm.projectservicedetails.projectid = vm.projectservicedetailData.projectid;
                vm.projectservicedetails.serviceid = vm.projectservicedetailData.serviceid;
                vm.projectservicedetails.departmentname =vm.projectservicedetailData.departmentname;
                vm.projectservicedetails.servicename =vm.projectservicedetailData.servicename;
                vm.projectservicedetails.isrequired=vm.projectservicedetailData.isrequired;
                vm.projectservicedetails.markrequiredondate=vm.projectservicedetailData.markrequiredondate;
                vm.projectservicedetails.markrequiredby=vm.projectservicedetailData.markrequiredby;
                vm.projectservicedetails.isassigned =true;
                vm.projectservicedetails.markassignedby =vm.account.login;
                vm.projectservicedetails.feerequired = vm.Projectservicedetail.servicefee;
                vm.projectservicedetails.comment = vm.Projectservicedetail.remarks;
                vm.projectservicedetails.status = "Pending";

            if (vm.projectservicedetails.id !== null) {
            alert(vm.projectservicedetails.id);
                Projectservicedetail.update(vm.projectservicedetails, onSaveSuccess, onSaveError);
            }
        }
        function onSaveSuccess (result) {
//            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
//            $uibModalInstance.close(result);
            vm.isSaving = false;

        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
