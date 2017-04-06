(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServicePaymentDetailsDetailController', ProjectServicePaymentDetailsDetailController);

    ProjectServicePaymentDetailsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProjectServicePaymentDetails'];

    function ProjectServicePaymentDetailsDetailController($scope, $rootScope, $stateParams, previousState, entity, ProjectServicePaymentDetails) {
        var vm = this;

        vm.projectServicePaymentDetails = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectServicePaymentDetailsUpdate', function(event, result) {
            vm.projectServicePaymentDetails = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
