(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailDetailController', ProjectdetailDetailController);

    ProjectdetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectdetail'];

    function ProjectdetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectdetail) {
        var vm = this;

        vm.projectdetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectdetailUpdate', function(event, result) {
            vm.projectdetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
