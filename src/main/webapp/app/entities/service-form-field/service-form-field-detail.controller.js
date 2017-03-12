(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ServiceFormFieldDetailController', ServiceFormFieldDetailController);

    ServiceFormFieldDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ServiceFormField'];

    function ServiceFormFieldDetailController($scope, $rootScope, $stateParams, previousState, entity, ServiceFormField) {
        var vm = this;

        vm.serviceFormField = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:serviceFormFieldUpdate', function(event, result) {
            vm.serviceFormField = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
