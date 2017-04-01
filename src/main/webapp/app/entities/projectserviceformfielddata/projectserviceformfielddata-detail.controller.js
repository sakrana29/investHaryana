(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectserviceformfielddataDetailController', ProjectserviceformfielddataDetailController);

    ProjectserviceformfielddataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectserviceformfielddata'];

    function ProjectserviceformfielddataDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectserviceformfielddata) {
        var vm = this;

        vm.projectserviceformfielddata = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectserviceformfielddataUpdate', function(event, result) {
            vm.projectserviceformfielddata = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
