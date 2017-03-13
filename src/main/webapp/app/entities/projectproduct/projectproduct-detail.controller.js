(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectproductDetailController', ProjectproductDetailController);

    ProjectproductDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectproduct'];

    function ProjectproductDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectproduct) {
        var vm = this;

        vm.projectproduct = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectproductUpdate', function(event, result) {
            vm.projectproduct = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
