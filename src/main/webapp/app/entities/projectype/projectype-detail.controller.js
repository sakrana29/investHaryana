(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectypeDetailController', ProjectypeDetailController);

    ProjectypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectype'];

    function ProjectypeDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectype) {
        var vm = this;

        vm.projectype = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectypeUpdate', function(event, result) {
            vm.projectype = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
