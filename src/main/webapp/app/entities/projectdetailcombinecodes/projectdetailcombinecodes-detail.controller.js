(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectdetailcombinecodesDetailController', ProjectdetailcombinecodesDetailController);

    ProjectdetailcombinecodesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Projectdetailcombinecodes'];

    function ProjectdetailcombinecodesDetailController($scope, $rootScope, $stateParams, previousState, entity, Projectdetailcombinecodes) {
        var vm = this;

        vm.projectdetailcombinecodes = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('investhryApp:projectdetailcombinecodesUpdate', function(event, result) {
            vm.projectdetailcombinecodes = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
