(function() {
    'use strict';

    angular
        .module('investhryApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('processapplication', {
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
        })
         .state('processapplication.payment', {
            parent: 'app',
            url: '/process',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                   templateUrl: 'app/processapplication/paymentservice.html',
                    controller: 'processapplicationController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg'
                }).result.then(function() {
                    $state.go('processapplication', null, { reload: 'processapplication' });
                }, function() {
                    $state.go('processapplication');
                });
            }]
        });
//        .state('processapplication.payment', {
//            parent: 'app',
//            url: '/process',
//            data: {
//                authorities: []
//            },
//             onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
//                $uibModal.open({
//                    templateUrl: 'app/processapplication/paymentservice.html',
//                    controller: 'processapplicationController',
//                    controllerAs: 'vm',
//                    backdrop: 'static',
//                    size: 'lg'
//                }).result.then(function() {
//                    $state.go('processapplication', null, { reload: 'processapplication' });
//                }, function() {
//                    $state.go('processapplication');
//                });
//            }]
//
//        });
    }
})();
