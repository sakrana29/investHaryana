(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Modeofdisposalfor_dischargeDetailController', Modeofdisposalfor_dischargeDetailController);

    Modeofdisposalfor_dischargeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Modeofdisposalfor_discharge'];

    function Modeofdisposalfor_dischargeDetailController($scope, $rootScope, $stateParams, previousState, entity, Modeofdisposalfor_discharge) {
        var vm = this;

        vm.modeofdisposalfor_discharge = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:modeofdisposalfor_dischargeUpdate', function(event, result) {
            vm.modeofdisposalfor_discharge = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
