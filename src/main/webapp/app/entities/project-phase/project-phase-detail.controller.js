(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Project_phaseDetailController', Project_phaseDetailController);

    Project_phaseDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Project_phase'];

    function Project_phaseDetailController($scope, $rootScope, $stateParams, previousState, entity, Project_phase) {
        var vm = this;

        vm.project_phase = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:project_phaseUpdate', function(event, result) {
            vm.project_phase = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
