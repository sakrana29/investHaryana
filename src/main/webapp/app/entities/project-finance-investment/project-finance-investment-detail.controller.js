(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_finance_investmentDetailController', Project_finance_investmentDetailController);

    Project_finance_investmentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Project_finance_investment'];

    function Project_finance_investmentDetailController($scope, $rootScope, $stateParams, previousState, entity, Project_finance_investment) {
        var vm = this;

        vm.project_finance_investment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:project_finance_investmentUpdate', function(event, result) {
            vm.project_finance_investment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
