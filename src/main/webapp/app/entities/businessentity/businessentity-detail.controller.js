(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentityDetailController', BusinessentityDetailController);

    BusinessentityDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Businessentity'];

    function BusinessentityDetailController($scope, $rootScope, $stateParams, previousState, entity, Businessentity) {
        var vm = this;

        vm.businessentity = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:businessentityUpdate', function(event, result) {
            vm.businessentity = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
