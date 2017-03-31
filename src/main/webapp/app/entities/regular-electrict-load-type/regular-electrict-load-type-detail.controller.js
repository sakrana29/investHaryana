(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Regular_electrict_load_typeDetailController', Regular_electrict_load_typeDetailController);

    Regular_electrict_load_typeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Regular_electrict_load_type'];

    function Regular_electrict_load_typeDetailController($scope, $rootScope, $stateParams, previousState, entity, Regular_electrict_load_type) {
        var vm = this;

        vm.regular_electrict_load_type = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:regular_electrict_load_typeUpdate', function(event, result) {
            vm.regular_electrict_load_type = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
