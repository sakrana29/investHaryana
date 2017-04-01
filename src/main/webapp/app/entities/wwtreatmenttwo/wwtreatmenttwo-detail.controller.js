(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmenttwoDetailController', WwtreatmenttwoDetailController);

    WwtreatmenttwoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Wwtreatmenttwo'];

    function WwtreatmenttwoDetailController($scope, $rootScope, $stateParams, previousState, entity, Wwtreatmenttwo) {
        var vm = this;

        vm.wwtreatmenttwo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:wwtreatmenttwoUpdate', function(event, result) {
            vm.wwtreatmenttwo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
