(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectservicedetailDetailController', ProjectservicedetailDetailController);

    ProjectservicedetailDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectservicedetail'];

    function ProjectservicedetailDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectservicedetail) {
        var vm = this;

        vm.projectservicedetail = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectservicedetailUpdate', function(event, result) {
            vm.projectservicedetail = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
