(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('DepartmentserviceDetailController', DepartmentserviceDetailController);

    DepartmentserviceDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Departmentservice'];

    function DepartmentserviceDetailController($scope, $rootScope, $stateParams, previousState, entity, Departmentservice) {
        var vm = this;

        vm.departmentservice = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:departmentserviceUpdate', function(event, result) {
            vm.departmentservice = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
