(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('processapplication', {
            parent: 'app',
            url: '/processapplication{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/processapplication/processapplication.html',
                    controller: 'processapplicationController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('processapplication');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
