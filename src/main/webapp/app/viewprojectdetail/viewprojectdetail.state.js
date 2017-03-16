(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('viewprojectdetail', {
            parent: 'app',
            url: '/viewprojectdetail/{status}',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/viewprojectdetail/viewprojectdetail.html',
                    controller: 'viewprojectdetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('viewprojectdetail');
                    return $translate.refresh();
                }]
            }
        });
    }
})();

