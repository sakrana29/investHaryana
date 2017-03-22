(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('acceptance', {
            parent: 'app',
            url: '/acceptance',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/acceptance/acceptance.html',
                    controller: 'acceptanceController',
                    controllerAs: 'vm'
                }
            }
        });
    }
})();
