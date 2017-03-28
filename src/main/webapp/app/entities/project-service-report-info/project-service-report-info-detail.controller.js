(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectServiceReportInfoDetailController', ProjectServiceReportInfoDetailController);

    ProjectServiceReportInfoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'ProjectServiceReportInfo'];

    function ProjectServiceReportInfoDetailController($scope, $rootScope, $stateParams, previousState, entity, ProjectServiceReportInfo) {
        var vm = this;

        vm.projectServiceReportInfo = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectServiceReportInfoUpdate', function(event, result) {
            vm.projectServiceReportInfo = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
