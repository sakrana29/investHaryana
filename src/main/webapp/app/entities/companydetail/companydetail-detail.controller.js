(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('CompanydetailDetailController', CompanydetailDetailController);

    CompanydetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Companydetail'];

    function CompanydetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Companydetail) {
        var vm = this;

        vm.companydetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:companydetailUpdate', function(event, result) {
            vm.companydetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
