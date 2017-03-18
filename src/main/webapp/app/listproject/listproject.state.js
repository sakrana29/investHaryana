(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('listproject', {
            parent: 'app',
            url: '/listproject',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/listproject/listproject.html',
                    controller: 'listprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('listproject');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
