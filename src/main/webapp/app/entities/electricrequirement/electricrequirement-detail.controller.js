(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ElectricrequirementDetailController', ElectricrequirementDetailController);

    ElectricrequirementDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Electricrequirement'];

    function ElectricrequirementDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Electricrequirement) {
        var vm = this;

        vm.electricrequirement = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:electricrequirementUpdate', function(event, result) {
            vm.electricrequirement = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
