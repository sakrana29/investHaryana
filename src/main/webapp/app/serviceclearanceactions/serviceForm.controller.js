(function(){
    'use strict';

    angular
        .module('investhryApp')
        .controller('serviceFormController', serviceFormController);

    serviceFormController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'ServiceFormField'];
    function serviceFormController ($timeout, $scope, $stateParams, $uibModalInstance, ServiceFormField) {
        var vm = this;
        var srvid = $stateParams.serviceID; //getting fooVal
        var prjid = $stateParams.projectid; //getting barVal
        vm.getids=srvid;
        vm.getprojectid=prjid;

        vm.clear = clear;
//        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        ServiceFormField.query(function(result) {
            vm.serviceFormFields = result;
            console.log(vm.serviceFormFields);
            vm.searchQuery = null;
        });



    }

})();
