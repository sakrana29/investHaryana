(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('Term_declaration_acceptDetailController', Term_declaration_acceptDetailController);

    Term_declaration_acceptDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Term_declaration_accept'];

    function Term_declaration_acceptDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Term_declaration_accept) {
        var vm = this;

        vm.term_declaration_accept = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('investhryApp:term_declaration_acceptUpdate', function(event, result) {
            vm.term_declaration_accept = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
