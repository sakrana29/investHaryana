(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Environment_impactdetailDetailController', Environment_impactdetailDetailController);

    Environment_impactdetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Environment_impactdetail'];

    function Environment_impactdetailDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Environment_impactdetail) {
        var vm = this;

        vm.environment_impactdetail = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:environment_impactdetailUpdate', function(event, result) {
            vm.environment_impactdetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
