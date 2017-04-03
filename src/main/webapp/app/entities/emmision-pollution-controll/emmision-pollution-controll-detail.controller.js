(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Emmision_pollution_controllDetailController', Emmision_pollution_controllDetailController);

    Emmision_pollution_controllDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Emmision_pollution_controll'];

    function Emmision_pollution_controllDetailController($scope, $rootScope, $stateParams, previousState, entity, Emmision_pollution_controll) {
        var vm = this;

        vm.emmision_pollution_controll = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:emmision_pollution_controllUpdate', function(event, result) {
            vm.emmision_pollution_controll = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
