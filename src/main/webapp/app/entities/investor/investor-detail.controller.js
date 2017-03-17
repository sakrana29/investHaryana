(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('InvestorDetailController', InvestorDetailController);

    InvestorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Investor'];

    function InvestorDetailController($scope, $rootScope, $stateParams, previousState, entity, Investor) {
        var vm = this;

        vm.investor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:investorUpdate', function(event, result) {
            vm.investor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
