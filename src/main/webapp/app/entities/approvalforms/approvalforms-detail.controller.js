(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ApprovalformsDetailController', ApprovalformsDetailController);

    ApprovalformsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Approvalforms'];

    function ApprovalformsDetailController($scope, $rootScope, $stateParams, previousState, entity, Approvalforms) {
        var vm = this;

        vm.approvalforms = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:approvalformsUpdate', function(event, result) {
            vm.approvalforms = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
