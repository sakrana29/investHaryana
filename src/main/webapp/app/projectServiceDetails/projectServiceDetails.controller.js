(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('projectServiceDetailsController', projectServiceDetailsController);

    projectServiceDetailsController.$inject = [ '$http','$timeout', '$scope',  '$stateParams','$state', 'entity', 'projectservicedetailentity',  'DepartmentServiceProjectWise', 'Projectcompletedetail', 'Auth', 'Principal', 'DepartmentService', 'Projectservicedetail'];

    function projectServiceDetailsController ( $http, $timeout, $scope, $stateParams, $state, entity, projectservicedetailentity,  DepartmentServiceProjectWise, Projectcompletedetail, Auth, Principal, DepartmentService, Projectservicedetail) {
        var vm = this;
         vm.save=save;
         vm.clear = clear;
         vm.projectservicedetails = projectservicedetailentity;
         vm.DepartmentServiceProjectWise = {};
         vm.CompleteProjectDetail = entity;



        $http.get("/api/ProjectWiseServiceDetails/"+ vm.CompleteProjectDetail.projectdetailDTO.id).then(function(response) {
                 vm.DepartmentServiceProjectWise = response.data;
                console.log(vm.DepartmentServiceProjectWise);
               });

       Principal.identity().then(function(account) {
               vm.account = account;
               });

        function clear () {
            //$uibModalInstance.dismiss('cancel');
            $state.go('projectServiceDetails', null, { reload: 'projectServiceDetails' });
        }
        function save (serviceid) {

         vm.departmentservices = DepartmentService.get({id : serviceid});
         console.log(vm.departmentservices);

            vm.isSaving = true;
            vm.projectservicedetails.isrequired =true;
            vm.projectservicedetails.markrequiredby =vm.account.login;
            vm.projectservicedetails.markrequiredondate = new Date();
            vm.projectservicedetails.serviceid =serviceid;
            vm.projectservicedetails.servicename= vm.departmentservices.serviceName;
            vm.projectservicedetails.departmentname = vm.departmentservices.departmentname;
            vm.projectservicedetails.status = "Pending";
            console.log(vm.projectservicedetails);
            Projectservicedetail.save(vm.projectservicedetails,onSaveSuccess,onSaveError);

        }
        function onSaveSuccess (result) {
        alert('hello');
//            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
//            $uibModalInstance.close(result);
            vm.isSaving = false;
            $window.location.reload();
        }

        function onSaveError () {
        alert('error');
            vm.isSaving = false;
        }


    }
})();
