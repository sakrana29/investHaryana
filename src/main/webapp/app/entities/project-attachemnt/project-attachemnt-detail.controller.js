(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectAttachemntDetailController', ProjectAttachemntDetailController);

    ProjectAttachemntDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProjectAttachemnt'];

    function ProjectAttachemntDetailController($scope, $rootScope, $stateParams, previousState, entity, ProjectAttachemnt) {
        var vm = this;

        vm.projectAttachemnt = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectAttachemntUpdate', function(event, result) {
            vm.projectAttachemnt = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
