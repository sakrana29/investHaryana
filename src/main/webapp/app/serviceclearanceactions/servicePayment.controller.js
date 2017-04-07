(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('servicePaymentController', servicePaymentController);

    servicePaymentController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Projectservicedetail'];
    function servicePaymentController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Projectservicedetail) {
        var vm = this;
        vm.projectservicedetail = entity;

        vm.clear = clear;
        vm.makePayment = makePayment;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function makePayment(){

        alert('makePayment');

        }


    }

})();
