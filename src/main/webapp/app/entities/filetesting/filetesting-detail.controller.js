(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('FiletestingDetailController', FiletestingDetailController);

    FiletestingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Filetesting'];

    function FiletestingDetailController($scope, $rootScope, $stateParams, previousState, entity, Filetesting) {
        var vm = this;

        vm.filetesting = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:filetestingUpdate', function(event, result) {
            vm.filetesting = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
