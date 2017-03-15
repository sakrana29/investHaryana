(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('viewcompletedproject', {
            parent: 'app',
            url: '/viewcompletedproject',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/viewcompletedproject/viewcompletedproject.html',
                    controller: 'viewcompletedprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('viewcompletedproject');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
