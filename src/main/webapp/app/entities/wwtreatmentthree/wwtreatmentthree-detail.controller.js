(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WwtreatmentthreeDetailController', WwtreatmentthreeDetailController);

    WwtreatmentthreeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Wwtreatmentthree'];

    function WwtreatmentthreeDetailController($scope, $rootScope, $stateParams, previousState, entity, Wwtreatmentthree) {
        var vm = this;

        vm.wwtreatmentthree = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:wwtreatmentthreeUpdate', function(event, result) {
            vm.wwtreatmentthree = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
