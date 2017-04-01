(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('BusinessentitysDetailController', BusinessentitysDetailController);

    BusinessentitysDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Businessentitys'];

    function BusinessentitysDetailController($scope, $rootScope, $stateParams, previousState, entity, Businessentitys) {
        var vm = this;

        vm.businessentitys = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:businessentitysUpdate', function(event, result) {
            vm.businessentitys = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
