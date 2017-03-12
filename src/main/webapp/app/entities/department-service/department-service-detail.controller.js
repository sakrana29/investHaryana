(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentServiceDetailController', DepartmentServiceDetailController);

    DepartmentServiceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DepartmentService'];

    function DepartmentServiceDetailController($scope, $rootScope, $stateParams, previousState, entity, DepartmentService) {
        var vm = this;

        vm.departmentService = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:departmentServiceUpdate', function(event, result) {
            vm.departmentService = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
