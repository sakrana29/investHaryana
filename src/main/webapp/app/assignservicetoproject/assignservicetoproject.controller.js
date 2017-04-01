(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('assignservicetoprojectController', assignservicetoprojectController);

    assignservicetoprojectController.$inject = ['$timeout', '$scope',  '$stateParams','$state', 'DepartmentService', 'Department', 'Projectservicedetail', 'DepartmentServiceData' , 'entity', 'Projectcompletedetail'];

    function assignservicetoprojectController ($timeout, $scope, $stateParams, $state,  DepartmentService, Department, Projectservicedetail, DepartmentServiceData, entity, Projectcompletedetail) {
        var vm = this;

         vm.clear = clear;
         vm.CompleteProjectDetail= entity;
         DepartmentServiceData.query(function(result) {
                         vm.departmentServices = result;
//            console.log(result);
//                         vm.searchQuery = null;
                     });

        function clear () {
            //$uibModalInstance.dismiss('cancel');
            $state.go('assignservicetoproject', null, { reload: 'assignservicetoproject' });
        }

    }
})();
