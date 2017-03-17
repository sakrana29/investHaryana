(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailDetailController', ProjectsitedetailDetailController);

    ProjectsitedetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectsitedetail'];

    function ProjectsitedetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectsitedetail) {
        var vm = this;

        vm.projectsitedetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectsitedetailUpdate', function(event, result) {
            vm.projectsitedetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
