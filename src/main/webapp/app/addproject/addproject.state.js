(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('addproject', {
            parent: 'app',
            url: '/addproject',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/addproject/addproject.html',
                    controller: 'addprojectController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('addproject');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
