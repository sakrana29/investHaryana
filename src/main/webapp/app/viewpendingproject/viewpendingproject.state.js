(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('viewpendingproject', {
            parent: 'app',
            url: '/viewpendingproject',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/viewpendingproject/viewpendingproject.html',
                    controller: 'viewpendingprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('viewpendingproject');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
