(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ManufacturingdetailDetailController', ManufacturingdetailDetailController);

    ManufacturingdetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Manufacturingdetail'];

    function ManufacturingdetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Manufacturingdetail) {
        var vm = this;

        vm.manufacturingdetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:manufacturingdetailUpdate', function(event, result) {
            vm.manufacturingdetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
