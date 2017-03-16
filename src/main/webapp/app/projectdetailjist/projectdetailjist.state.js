(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('projectdetailjist', {
            parent: 'app',
            url: '/projectdetailjist{id}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/projectdetailjist/projectdetailjist.html',
                    controller: 'projectdetailjistController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('projectdetailjist');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
