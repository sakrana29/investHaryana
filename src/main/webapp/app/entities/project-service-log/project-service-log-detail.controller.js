(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceLogDetailController', ProjectServiceLogDetailController);

    ProjectServiceLogDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProjectServiceLog'];

    function ProjectServiceLogDetailController($scope, $rootScope, $stateParams, previousState, entity, ProjectServiceLog) {
        var vm = this;

        vm.projectServiceLog = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectServiceLogUpdate', function(event, result) {
            vm.projectServiceLog = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
