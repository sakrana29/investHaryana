(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('verifyPaymentController', verifyPaymentController);

    verifyPaymentController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'ProjectServicePaymentDetailsByProjectAndService','Projectservicedetail'];
    function verifyPaymentController ($timeout, $scope, $stateParams, $uibModalInstance,ProjectServicePaymentDetailsByProjectAndService,Projectservicedetail) {
        var vm = this;
        vm.clear = clear;
        vm.projectService=$stateParams.projectService;


        vm.verifyPayment = verifyPayment;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        loadAllPayments();

        function loadAllPayments() {
            vm.projectServicePaymentDetails=ProjectServicePaymentDetailsByProjectAndService.query({projectid: vm.projectService.projectid,serviceid: vm.projectService.serviceid});
        }

        function verifyPayment(){
            vm.projectService.isPaymentVerified=true;
            Projectservicedetail.update(vm.projectService,onUpdateProjectServiceDetailSuccess,onUpdateProjectServiceDetailError);
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
