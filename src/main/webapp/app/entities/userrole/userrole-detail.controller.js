(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('UserroleDetailController', UserroleDetailController);

    UserroleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Userrole'];

    function UserroleDetailController($scope, $rootScope, $stateParams, previousState, entity, Userrole) {
        var vm = this;

        vm.userrole = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:userroleUpdate', function(event, result) {
            vm.userrole = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
