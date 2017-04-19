(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceLogController', serviceLogController);

    serviceLogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'ProjectServicePaymentDetailsByProjectAndService','Projectservicedetail'];
    function serviceLogController ($timeout, $scope, $stateParams, $uibModalInstance,ProjectServicePaymentDetailsByProjectAndService,Projectservicedetail) {
        var vm = this;
        vm.clear = clear;
        vm.projectService=$stateParams.projectService;

//        vm.serviceLog = serviceLog;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        loadServiceLog();

        function loadServiceLog() {
//            vm.projectServicePaymentDetails=ProjectServicePaymentDetailsByProjectAndService.query({projectid: vm.projectService.projectid,serviceid: vm.projectService.serviceid});
//            console.log(vm.projectServicePaymentDetails);
        }

        function onUpdateProjectServiceDetailSuccess(result)
        {
            $scope.$emit('investhryApp:projectservicedetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }
        function onUpdateProjectServiceDetailError()
        {
            vm.isSaving = false;
        }
    }
})();
