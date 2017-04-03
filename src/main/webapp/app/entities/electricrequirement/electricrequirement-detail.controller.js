(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementDetailController', ElectricrequirementDetailController);

    ElectricrequirementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Electricrequirement'];

    function ElectricrequirementDetailController($scope, $rootScope, $stateParams, previousState, entity, Electricrequirement) {
        var vm = this;

        vm.electricrequirement = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:electricrequirementUpdate', function(event, result) {
            vm.electricrequirement = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
