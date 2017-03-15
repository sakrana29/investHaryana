(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('WastewaterdetailDetailController', WastewaterdetailDetailController);

    WastewaterdetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Wastewaterdetail'];

    function WastewaterdetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Wastewaterdetail) {
        var vm = this;

        vm.wastewaterdetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:wastewaterdetailUpdate', function(event, result) {
            vm.wastewaterdetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
