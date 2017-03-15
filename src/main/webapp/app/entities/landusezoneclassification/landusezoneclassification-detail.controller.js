(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('LandusezoneclassificationDetailController', LandusezoneclassificationDetailController);

    LandusezoneclassificationDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Landusezoneclassification'];

    function LandusezoneclassificationDetailController($scope, $rootScope, $stateParams, previousState, entity, Landusezoneclassification) {
        var vm = this;

        vm.landusezoneclassification = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:landusezoneclassificationUpdate', function(event, result) {
            vm.landusezoneclassification = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
