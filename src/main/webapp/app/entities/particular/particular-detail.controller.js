(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ParticularDetailController', ParticularDetailController);

    ParticularDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Particular'];

    function ParticularDetailController($scope, $rootScope, $stateParams, previousState, entity, Particular) {
        var vm = this;

        vm.particular = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:particularUpdate', function(event, result) {
            vm.particular = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
