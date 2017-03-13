(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectprocessflowstepsDetailController', ProjectprocessflowstepsDetailController);

    ProjectprocessflowstepsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectprocessflowsteps'];

    function ProjectprocessflowstepsDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectprocessflowsteps) {
        var vm = this;

        vm.projectprocessflowsteps = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectprocessflowstepsUpdate', function(event, result) {
            vm.projectprocessflowsteps = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
