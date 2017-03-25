(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentoneDetailController', WwtreatmentoneDetailController);

    WwtreatmentoneDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Wwtreatmentone'];

    function WwtreatmentoneDetailController($scope, $rootScope, $stateParams, previousState, entity, Wwtreatmentone) {
        var vm = this;

        vm.wwtreatmentone = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:wwtreatmentoneUpdate', function(event, result) {
            vm.wwtreatmentone = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
