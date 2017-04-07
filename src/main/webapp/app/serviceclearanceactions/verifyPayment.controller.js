(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('verifyPaymentController', verifyPaymentController);

    verifyPaymentController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'ProjectServicePaymentDetails'];
    function verifyPaymentController ($timeout, $scope, $stateParams, $uibModalInstance, ProjectServicePaymentDetails) {
        var vm = this;
        vm.clear = clear;
        vm.verifyPayment = verifyPayment;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        loadAll();

        function loadAll() {
            ProjectServicePaymentDetails.query(function(result) {
                vm.projectServicePaymentDetails = result;
                vm.searchQuery = null;
            });
        }
        function verifyPayment(){
        alert('verifyPayment');
        }


    }

})();
